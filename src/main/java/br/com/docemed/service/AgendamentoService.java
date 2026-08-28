package br.com.docemed.service;

import br.com.docemed.dto.AgendamentoRequestDTO;
import br.com.docemed.dto.ReagendamentoDTO;
import br.com.docemed.model.Agendamento;
import br.com.docemed.model.Paciente;
import br.com.docemed.model.StatusAgendamento;
import br.com.docemed.repository.AgendamentoRepository;
import br.com.docemed.repository.AnamneseRepository;
import br.com.docemed.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final PacienteRepository pacienteRepository;
    private final AnamneseRepository anamneseRepository;
    private final RealtimeNotificationService realtimeService;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy 'às' HH:mm");

    @Transactional
    public Agendamento solicitarAgendamento(AgendamentoRequestDTO dto) {
        Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
                .orElseThrow(() -> new IllegalArgumentException("Paciente não encontrado com ID: " + dto.getPacienteId()));

        // Validação da regra de negócio: Anamnese obrigatória
        boolean possuiAnamnese = anamneseRepository.findFirstByPacienteIdOrderByDataPreenchimentoDesc(dto.getPacienteId()).isPresent();
        if (!possuiAnamnese && dto.getAnamneseId() == null) {
            throw new IllegalStateException("Para solicitar o agendamento, é obrigatório preencher a Ficha de Anamnese Tricológica primeiro.");
        }

        LocalDateTime dataHora = parseDateTime(dto.getDataHora());

        Agendamento agendamento = Agendamento.builder()
                .pacienteId(paciente.getId())
                .dataHora(dataHora)
                .status(StatusAgendamento.PENDENTE)
                .anamneseId(dto.getAnamneseId())
                .medicoResponsavel(dto.getMedicoResponsavel() != null ? dto.getMedicoResponsavel() : "Dr. Especialista em Tricologia")
                .dataSolicitacao(LocalDateTime.now())
                .build();

        agendamento = agendamentoRepository.save(agendamento);

        // Notificar médico em tempo real
        realtimeService.emitGlobalEvent("NOVO_AGENDAMENTO", Map.of(
                "agendamentoId", agendamento.getId(),
                "pacienteId", paciente.getId(),
                "pacienteNome", paciente.getNome(),
                "pacienteTelefone", paciente.getCelularWhatsapp() != null ? paciente.getCelularWhatsapp() : paciente.getTelefone(),
                "dataHoraFormatada", dataHora.format(FORMATTER),
                "status", agendamento.getStatus().name()
        ));

        return agendamento;
    }

    @Transactional
    public Agendamento confirmarAgendamento(Long agendamentoId) {
        Agendamento agendamento = agendamentoRepository.findById(agendamentoId)
                .orElseThrow(() -> new IllegalArgumentException("Agendamento não encontrado com ID: " + agendamentoId));

        agendamento.setStatus(StatusAgendamento.CONFIRMADO);
        agendamento.setDataAtualizacao(LocalDateTime.now());
        agendamento = agendamentoRepository.save(agendamento);

        Paciente paciente = pacienteRepository.findById(agendamento.getPacienteId()).orElse(null);
        String nomePaciente = paciente != null ? paciente.getNome() : "Paciente";

        // Notifica o paciente em tempo real
        realtimeService.emitPacienteEvent(agendamento.getPacienteId(), "AGENDAMENTO_CONFIRMADO", Map.of(
                "agendamentoId", agendamento.getId(),
                "pacienteId", agendamento.getPacienteId(),
                "pacienteNome", nomePaciente,
                "dataHoraFormatada", agendamento.getDataHora().format(FORMATTER),
                "mensagem", "Sua consulta com " + agendamento.getMedicoResponsavel() + " foi confirmada para " + agendamento.getDataHora().format(FORMATTER) + "!"
        ));

        return agendamento;
    }

    @Transactional
    public Agendamento reagendarMedico(ReagendamentoDTO dto) {
        Agendamento agendamento = agendamentoRepository.findById(dto.getAgendamentoId())
                .orElseThrow(() -> new IllegalArgumentException("Agendamento não encontrado com ID: " + dto.getAgendamentoId()));

        LocalDateTime novaDataHora = parseDateTime(dto.getNovaDataHora());
        agendamento.setDataHora(novaDataHora);
        agendamento.setStatus(StatusAgendamento.REAGENDADO_MEDICO);
        agendamento.setMotivoAlteracao(dto.getMotivo());
        agendamento.setDataAtualizacao(LocalDateTime.now());
        agendamento = agendamentoRepository.save(agendamento);

        Paciente paciente = pacienteRepository.findById(agendamento.getPacienteId()).orElse(null);
        String nomePaciente = paciente != null ? paciente.getNome() : "Paciente";

        // Texto humanizado e acolhedor para o paciente
        String mensagemAviso = "Prezado(a) " + nomePaciente + ", devido a uma necessidade de ajuste clínico e organização da agenda cirúrgica/ambulatorial, o " + agendamento.getMedicoResponsavel() + " sugeriu a alteração do seu horário para: " + novaDataHora.format(FORMATTER) + ". Motivo informado: " + dto.getMotivo() + ". Por favor, confirme se o novo horário é adequado para você.";

        // Notifica o paciente em tempo real
        realtimeService.emitPacienteEvent(agendamento.getPacienteId(), "REAGENDAMENTO_SOLICITADO", Map.of(
                "agendamentoId", agendamento.getId(),
                "pacienteId", agendamento.getPacienteId(),
                "pacienteNome", nomePaciente,
                "novaDataHoraFormatada", novaDataHora.format(FORMATTER),
                "motivo", dto.getMotivo(),
                "mensagemHumanizada", mensagemAviso
        ));

        return agendamento;
    }

    @Transactional
    public Agendamento aceitarReagendamento(Long agendamentoId) {
        Agendamento agendamento = agendamentoRepository.findById(agendamentoId)
                .orElseThrow(() -> new IllegalArgumentException("Agendamento não encontrado com ID: " + agendamentoId));

        agendamento.setStatus(StatusAgendamento.CONFIRMADO);
        agendamento.setDataAtualizacao(LocalDateTime.now());
        agendamento = agendamentoRepository.save(agendamento);

        Paciente paciente = pacienteRepository.findById(agendamento.getPacienteId()).orElse(null);
        String nomePaciente = paciente != null ? paciente.getNome() : "Paciente";

        // Notifica médico da confirmação do paciente
        realtimeService.emitGlobalEvent("REAGENDAMENTO_ACEITO", Map.of(
                "agendamentoId", agendamento.getId(),
                "pacienteId", agendamento.getPacienteId(),
                "pacienteNome", nomePaciente,
                "dataHoraFormatada", agendamento.getDataHora().format(FORMATTER),
                "mensagem", "O paciente " + nomePaciente + " confirmou a nova data/horário da consulta: " + agendamento.getDataHora().format(FORMATTER)
        ));

        return agendamento;
    }

    public List<Agendamento> listarTodos() {
        return agendamentoRepository.findAll();
    }

    public List<Agendamento> listarPorPaciente(Long pacienteId) {
        return agendamentoRepository.findByPacienteIdOrderByDataHoraDesc(pacienteId);
    }

    public Agendamento buscarRecentePorPaciente(Long pacienteId) {
        return agendamentoRepository.findTopByPacienteIdOrderByDataHoraDesc(pacienteId).orElse(null);
    }

    private LocalDateTime parseDateTime(String text) {
        if (text == null || text.isBlank()) {
            return LocalDateTime.now().plusDays(1).withHour(14).withMinute(0);
        }
        try {
            if (text.contains("T")) {
                return LocalDateTime.parse(text);
            }
            return LocalDateTime.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } catch (Exception e) {
            return LocalDateTime.now().plusDays(1).withHour(14).withMinute(0);
        }
    }
}
