package br.com.docemed.service;

import br.com.docemed.dto.FilaItemDTO;
import br.com.docemed.model.*;
import br.com.docemed.repository.AnamneseRepository;
import br.com.docemed.repository.FilaAtendimentoRepository;
import br.com.docemed.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FilaService {

    private final FilaAtendimentoRepository filaRepository;
    private final PacienteRepository pacienteRepository;
    private final AnamneseRepository anamneseRepository;
    private final RealtimeNotificationService realtimeService;

    @Transactional
    public FilaAtendimento adicionarAFila(Long pacienteId, Long agendamentoId, String sala, String medicoNome) {
        // Verifica se já está na fila ativa
        List<StatusFila> ativas = List.of(StatusFila.AGUARDANDO, StatusFila.CHAMADO, StatusFila.EM_ATENDIMENTO);
        if (filaRepository.existsByPacienteIdAndStatusIn(pacienteId, ativas)) {
            return filaRepository.findTopByPacienteIdAndStatusInOrderByHorarioEntradaDesc(pacienteId, ativas).orElseThrow();
        }

        List<FilaAtendimento> aguardando = filaRepository.findByStatusOrderByPosicaoAsc(StatusFila.AGUARDANDO);
        int proximaPosicao = aguardando.isEmpty() ? 1 : aguardando.get(aguardando.size() - 1).getPosicao() + 1;

        FilaAtendimento item = FilaAtendimento.builder()
                .pacienteId(pacienteId)
                .agendamentoId(agendamentoId)
                .posicao(proximaPosicao)
                .status(StatusFila.AGUARDANDO)
                .sala(sala != null && !sala.isBlank() ? sala : "Consultório 01")
                .medicoNome(medicoNome != null && !medicoNome.isBlank() ? medicoNome : "Dr. Especialista Tricologista")
                .horarioEntrada(LocalDateTime.now())
                .build();

        item = filaRepository.save(item);

        Paciente paciente = pacienteRepository.findById(pacienteId).orElse(null);
        String nome = paciente != null ? paciente.getNome() : "Paciente";

        // Broadcast de fila atualizada
        realtimeService.emitGlobalEvent("FILA_ATUALIZADA", Map.of(
                "tipo", "NOVO_PACIENTE_FILA",
                "pacienteNome", nome,
                "posicao", proximaPosicao,
                "sala", item.getSala()
        ));

        return item;
    }

    @Transactional
    public void reordenarFila(List<Long> idsOrdenados) {
        for (int i = 0; i < idsOrdenados.size(); i++) {
            Long id = idsOrdenados.get(i);
            Optional<FilaAtendimento> opt = filaRepository.findById(id);
            if (opt.isPresent()) {
                FilaAtendimento item = opt.get();
                item.setPosicao(i + 1);
                filaRepository.save(item);
            }
        }

        realtimeService.emitGlobalEvent("FILA_ATUALIZADA", Map.of("tipo", "REORDENACAO_FILA"));
    }

    @Transactional
    public FilaAtendimento chamarPaciente(Long filaId, String sala, String medicoNome) {
        FilaAtendimento item = filaRepository.findById(filaId)
                .orElseThrow(() -> new IllegalArgumentException("Item da fila não encontrado com ID: " + filaId));

        item.setStatus(StatusFila.CHAMADO);
        item.setHorarioChamada(LocalDateTime.now());
        if (sala != null && !sala.isBlank()) item.setSala(sala);
        if (medicoNome != null && !medicoNome.isBlank()) item.setMedicoNome(medicoNome);
        item = filaRepository.save(item);

        Paciente paciente = pacienteRepository.findById(item.getPacienteId()).orElse(null);
        String nomePaciente = paciente != null ? paciente.getNome() : "Paciente";

        // Mensagem de chamada para exibição
        String mensagemChamada = "Atenção: Paciente " + nomePaciente + ", por favor dirija-se ao " + item.getSala() + " com " + item.getMedicoNome() + ".";

        // Emite alerta específico para a tela do paciente e também para o telão público
        realtimeService.emitPacienteEvent(item.getPacienteId(), "PACIENTE_CHAMADO", Map.of(
                "filaId", item.getId(),
                "pacienteId", item.getPacienteId(),
                "pacienteNome", nomePaciente,
                "sala", item.getSala(),
                "medicoNome", item.getMedicoNome(),
                "mensagem", mensagemChamada
        ));

        return item;
    }

    @Transactional
    public FilaAtendimento atualizarStatus(Long filaId, StatusFila novoStatus) {
        FilaAtendimento item = filaRepository.findById(filaId)
                .orElseThrow(() -> new IllegalArgumentException("Item da fila não encontrado com ID: " + filaId));

        item.setStatus(novoStatus);
        item = filaRepository.save(item);

        Paciente paciente = pacienteRepository.findById(item.getPacienteId()).orElse(null);
        String nomePaciente = paciente != null ? paciente.getNome() : "Paciente";

        realtimeService.emitPacienteEvent(item.getPacienteId(), "STATUS_FILA_ALTERADO", Map.of(
                "filaId", item.getId(),
                "pacienteId", item.getPacienteId(),
                "pacienteNome", nomePaciente,
                "novoStatus", novoStatus.name()
        ));

        return item;
    }

    public List<FilaItemDTO> listarFilaAtiva() {
        List<StatusFila> ativas = List.of(StatusFila.CHAMADO, StatusFila.AGUARDANDO, StatusFila.EM_ATENDIMENTO);
        List<FilaAtendimento> lista = filaRepository.findByStatusInOrderByPosicaoAsc(ativas);
        List<FilaItemDTO> dtos = new ArrayList<>();

        for (FilaAtendimento item : lista) {
            Paciente p = pacienteRepository.findById(item.getPacienteId()).orElse(null);
            Optional<Anamnese> a = anamneseRepository.findFirstByPacienteIdOrderByDataPreenchimentoDesc(item.getPacienteId());

            dtos.add(FilaItemDTO.builder()
                    .id(item.getId())
                    .pacienteId(item.getPacienteId())
                    .pacienteNome(p != null ? p.getNome() : "Paciente Desconhecido")
                    .pacienteTelefone(p != null ? (p.getCelularWhatsapp() != null ? p.getCelularWhatsapp() : p.getTelefone()) : "")
                    .agendamentoId(item.getAgendamentoId())
                    .posicao(item.getPosicao())
                    .status(item.getStatus())
                    .statusDescricao(traduzirStatus(item.getStatus()))
                    .sala(item.getSala())
                    .medicoNome(item.getMedicoNome())
                    .horarioEntrada(item.getHorarioEntrada())
                    .horarioChamada(item.getHorarioChamada())
                    .possuiAnamnese(a.isPresent())
                    .queixaPrincipal(a.map(Anamnese::getQueixaPrincipal).orElse("Não preenchida"))
                    .build());
        }

        return dtos;
    }

    public Optional<FilaItemDTO> buscarStatusPaciente(Long pacienteId) {
        List<StatusFila> ativas = List.of(StatusFila.CHAMADO, StatusFila.AGUARDANDO, StatusFila.EM_ATENDIMENTO);
        return filaRepository.findTopByPacienteIdAndStatusInOrderByHorarioEntradaDesc(pacienteId, ativas)
                .map(item -> {
                    Paciente p = pacienteRepository.findById(item.getPacienteId()).orElse(null);
                    return FilaItemDTO.builder()
                            .id(item.getId())
                            .pacienteId(item.getPacienteId())
                            .pacienteNome(p != null ? p.getNome() : "")
                            .posicao(item.getPosicao())
                            .status(item.getStatus())
                            .statusDescricao(traduzirStatus(item.getStatus()))
                            .sala(item.getSala())
                            .medicoNome(item.getMedicoNome())
                            .horarioChamada(item.getHorarioChamada())
                            .build();
                });
    }

    private String traduzirStatus(StatusFila status) {
        return switch (status) {
            case AGUARDANDO -> "Aguarde ser chamado";
            case CHAMADO -> "CHAMADO — Dirija-se à Sala";
            case EM_ATENDIMENTO -> "Em Atendimento Clínico";
            case FINALIZADO -> "Atendimento Concluído";
            case AUSENTE -> "Cancelado por Não Comparecimento";
        };
    }
}
