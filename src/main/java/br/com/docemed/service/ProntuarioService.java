package br.com.docemed.service;

import br.com.docemed.dto.ProntuarioPacienteResumoDTO;
import br.com.docemed.dto.ProntuarioRequestDTO;
import br.com.docemed.model.Paciente;
import br.com.docemed.model.ProntuarioAtendimento;
import br.com.docemed.repository.PacienteRepository;
import br.com.docemed.repository.ProntuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProntuarioService {

    private final ProntuarioRepository prontuarioRepository;
    private final PacienteRepository pacienteRepository;
    private final RealtimeNotificationService realtimeService;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy 'às' HH:mm");

    @Transactional
    public ProntuarioAtendimento registrarAtendimento(ProntuarioRequestDTO dto) {
        Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
                .orElseThrow(() -> new IllegalArgumentException("Paciente não encontrado com ID: " + dto.getPacienteId()));

        ProntuarioAtendimento prontuario = ProntuarioAtendimento.builder()
                .pacienteId(dto.getPacienteId())
                .agendamentoId(dto.getAgendamentoId())
                .anamneseId(dto.getAnamneseId())
                .medicoNome(dto.getMedicoNome() != null && !dto.getMedicoNome().isBlank() ? dto.getMedicoNome() : "Dr. Vagner Domingos — Tricologista Responsável")
                .dataAtendimento(LocalDateTime.now())
                .diagnosticoClinico(dto.getDiagnosticoClinico())
                .tratamentoIndicado(dto.getTratamentoIndicado())
                .numeroSessoes(dto.getNumeroSessoes() != null ? dto.getNumeroSessoes() : 1)
                .receituarioMedicamentos(dto.getReceituarioMedicamentos())
                .manutencaoHomeCare(dto.getManutencaoHomeCare())
                .observacoesMedicas(dto.getObservacoesMedicas())
                .retornoDias(dto.getRetornoDias() != null ? dto.getRetornoDias() : 30)
                .build();

        prontuario = prontuarioRepository.save(prontuario);

        // Notifica o paciente em tempo real que sua receita e plano de tratamento estão disponíveis
        realtimeService.emitPacienteEvent(dto.getPacienteId(), "NOVO_RECEITUARIO_DISPONIVEL", Map.of(
                "prontuarioId", prontuario.getId(),
                "pacienteId", paciente.getId(),
                "pacienteNome", paciente.getNome(),
                "medicoNome", prontuario.getMedicoNome(),
                "dataAtendimentoFormatada", prontuario.getDataAtendimento().format(FORMATTER),
                "tratamentoIndicado", prontuario.getTratamentoIndicado() != null ? prontuario.getTratamentoIndicado() : "",
                "mensagem", "Seu receituário digital e plano de tratamento foram emitidos pelo " + prontuario.getMedicoNome() + "."
        ));

        return prontuario;
    }

    public List<ProntuarioAtendimento> listarHistoricoPaciente(Long pacienteId) {
        return prontuarioRepository.findByPacienteIdOrderByDataAtendimentoDesc(pacienteId);
    }

    public Optional<ProntuarioPacienteResumoDTO> obterResumoPaciente(Long pacienteId) {
        return prontuarioRepository.findTopByPacienteIdOrderByDataAtendimentoDesc(pacienteId)
                .map(p -> {
                    Paciente paciente = pacienteRepository.findById(p.getPacienteId()).orElse(null);
                    return ProntuarioPacienteResumoDTO.builder()
                            .id(p.getId())
                            .pacienteId(p.getPacienteId())
                            .pacienteNome(paciente != null ? paciente.getNome() : "")
                            .medicoNome(p.getMedicoNome())
                            .dataAtendimento(p.getDataAtendimento())
                            .dataAtendimentoFormatada(p.getDataAtendimento().format(FORMATTER))
                            .diagnosticoResumo(p.getDiagnosticoClinico())
                            .tratamentoIndicado(p.getTratamentoIndicado())
                            .numeroSessoes(p.getNumeroSessoes())
                            .receituarioMedicamentos(p.getReceituarioMedicamentos())
                            .manutencaoHomeCare(p.getManutencaoHomeCare())
                            .retornoDias(p.getRetornoDias())
                            .build();
                });
    }

    public Optional<ProntuarioAtendimento> buscarPorId(Long id) {
        return prontuarioRepository.findById(id);
    }
}
