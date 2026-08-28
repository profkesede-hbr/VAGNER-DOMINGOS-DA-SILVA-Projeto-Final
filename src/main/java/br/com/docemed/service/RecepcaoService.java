package br.com.docemed.service;

import br.com.docemed.dto.*;
import br.com.docemed.model.*;
import br.com.docemed.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecepcaoService {

    private final PacienteRepository pacienteRepository;
    private final UsuarioRepository usuarioRepository;
    private final FilaAtendimentoRepository filaRepository;
    private final AgendamentoRepository agendamentoRepository;
    private final ProntuarioRepository prontuarioRepository;
    private final RealtimeNotificationService realtimeService;
    private final EmailNotificacaoService emailService;

    @Transactional
    public LoginResponseDTO cadastrarPacientePresencial(CadastroPresencialRequestDTO dto) {
        String cpfLimpo = dto.getCpf().replaceAll("[^0-9]", "");
        String loginGerado = cpfLimpo.isBlank() ? dto.getCpf().trim().toLowerCase() : cpfLimpo;

        if (usuarioRepository.existsByLogin(loginGerado)) {
            throw new IllegalArgumentException("Já existe um paciente cadastrado com o CPF / Login: " + dto.getCpf());
        }

        // 1. Criar Paciente
        Paciente paciente = new Paciente();
        paciente.setNome(dto.getNome().trim());
        paciente.setCpf(dto.getCpf().trim());
        paciente.setRg(dto.getRg());
        paciente.setSexo(dto.getSexo() != null && !dto.getSexo().isBlank() ? dto.getSexo().trim() : "Feminino");
        paciente.setEstadoCivil(dto.getEstadoCivil());
        paciente.setProfissao(dto.getProfissao());
        paciente.setTelefone(dto.getTelefone());
        paciente.setCelularWhatsapp(dto.getCelularWhatsapp() != null ? dto.getCelularWhatsapp().trim() : null);
        paciente.setEmail(dto.getEmail().trim());
        paciente.setCep(dto.getCep());
        paciente.setEndereco(dto.getEndereco());
        paciente.setNumero(dto.getNumero());
        paciente.setComplemento(dto.getComplemento());
        paciente.setBairro(dto.getBairro());
        paciente.setCidade(dto.getCidade() != null ? dto.getCidade() : "São Paulo");
        paciente.setEstado(dto.getEstado() != null ? dto.getEstado() : "SP");
        paciente.setContatoEmergenciaNome(dto.getContatoEmergenciaNome());
        paciente.setContatoEmergenciaTelefone(dto.getContatoEmergenciaTelefone());
        paciente.setAtivo(true);
        paciente.setDataCadastro(LocalDateTime.now());

        if (dto.getDataNascimento() != null && !dto.getDataNascimento().isBlank()) {
            try {
                paciente.setDataNascimento(LocalDate.parse(dto.getDataNascimento()));
            } catch (Exception ignored) {
                paciente.setDataNascimento(LocalDate.of(1995, 1, 1));
            }
        }

        paciente = pacienteRepository.save(paciente);

        // 2. Gerar senha provisória segura (ex: Doc@ + 4 dígitos)
        String senhaProvisoria = "Doc@" + (1000 + new SecureRandom().nextInt(9000));

        // 3. Criar Usuário com Login = CPF
        Usuario usuario = Usuario.builder()
                .login(loginGerado)
                .senha(senhaProvisoria)
                .nome(dto.getNome().trim())
                .perfil(PerfilUsuario.PACIENTE)
                .pacienteId(paciente.getId())
                .telefoneWhatsapp(dto.getCelularWhatsapp())
                .ativo(true)
                .dataCadastro(LocalDateTime.now())
                .build();

        usuario = usuarioRepository.save(usuario);

        // 4. Enviar E-mail oficial com dados de acesso e link para alterar senha
        try {
            emailService.enviarEmailBoasVindasPresencial(dto.getEmail().trim(), dto.getNome().trim(), loginGerado, senhaProvisoria);
        } catch (Exception e) {
            log.error("Erro ao simular envio de e-mail de boas-vindas: {}", e.getMessage());
        }

        // 5. Se solicitado, incluir diretamente na fila do dia
        if (Boolean.TRUE.equals(dto.getIncluirImediatamenteNaFila())) {
            incluirPacienteNaFila(paciente.getId(), "Consultório 01", "Dr. Vagner Domingos — Tricologia Integrada");
        }

        return LoginResponseDTO.builder()
                .id(usuario.getId())
                .login(usuario.getLogin())
                .nome(usuario.getNome())
                .perfil(usuario.getPerfil())
                .pacienteId(paciente.getId())
                .token("TOKEN_PRESENCIAL_" + usuario.getId())
                .redirectUrl("/paciente/real-portal")
                .build();
    }

    public List<Paciente> listarPacientes(String query) {
        if (query == null || query.isBlank()) {
            return pacienteRepository.findAll();
        }
        String q = query.trim().toLowerCase();
        return pacienteRepository.findAll().stream()
                .filter(p -> (p.getNome() != null && p.getNome().toLowerCase().contains(q)) ||
                             (p.getCpf() != null && p.getCpf().contains(q)) ||
                             (p.getEmail() != null && p.getEmail().toLowerCase().contains(q)))
                .collect(Collectors.toList());
    }

    @Transactional
    public Paciente atualizarPaciente(Long id, PacienteEdicaoDTO dto) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Paciente não encontrado com ID: " + id));

        if (dto.getNome() != null && !dto.getNome().isBlank()) paciente.setNome(dto.getNome().trim());
        if (dto.getRg() != null) paciente.setRg(dto.getRg());
        if (dto.getTelefone() != null) paciente.setTelefone(dto.getTelefone());
        if (dto.getCelularWhatsapp() != null) paciente.setCelularWhatsapp(dto.getCelularWhatsapp());
        if (dto.getEmail() != null) paciente.setEmail(dto.getEmail());
        if (dto.getProfissao() != null) paciente.setProfissao(dto.getProfissao());
        if (dto.getEstadoCivil() != null) paciente.setEstadoCivil(dto.getEstadoCivil());
        if (dto.getContatoEmergenciaNome() != null) paciente.setContatoEmergenciaNome(dto.getContatoEmergenciaNome());
        if (dto.getContatoEmergenciaTelefone() != null) paciente.setContatoEmergenciaTelefone(dto.getContatoEmergenciaTelefone());
        if (dto.getCep() != null) paciente.setCep(dto.getCep());
        if (dto.getEndereco() != null) paciente.setEndereco(dto.getEndereco());
        if (dto.getNumero() != null) paciente.setNumero(dto.getNumero());
        if (dto.getComplemento() != null) paciente.setComplemento(dto.getComplemento());
        if (dto.getBairro() != null) paciente.setBairro(dto.getBairro());
        if (dto.getCidade() != null) paciente.setCidade(dto.getCidade());
        if (dto.getEstado() != null) paciente.setEstado(dto.getEstado());

        return pacienteRepository.save(paciente);
    }

    @Transactional
    public void resetarSenhaPaciente(Long pacienteId, ResetSenhaRequestDTO dto) {
        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new IllegalArgumentException("Paciente não encontrado com ID: " + pacienteId));

        Usuario usuario = usuarioRepository.findByPacienteId(pacienteId)
                .orElseThrow(() -> new IllegalArgumentException("Conta de acesso não encontrada para este paciente."));

        String novaSenha = dto.getNovaSenha() != null && !dto.getNovaSenha().isBlank() 
                ? dto.getNovaSenha().trim() 
                : "Doc@" + (1000 + new SecureRandom().nextInt(9000));

        usuario.setSenha(novaSenha);
        usuarioRepository.save(usuario);

        if (Boolean.TRUE.equals(dto.getEnviarEmail()) && paciente.getEmail() != null && !paciente.getEmail().isBlank()) {
            emailService.enviarEmailResetSenha(paciente.getEmail(), paciente.getNome(), usuario.getLogin(), novaSenha);
        }
    }

    @Transactional
    public FilaAtendimento incluirPacienteNaFila(Long pacienteId, String consultorio, String medicoNome) {
        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new IllegalArgumentException("Paciente não encontrado com ID: " + pacienteId));

        Integer maxPosicao = filaRepository.findMaxPosicao();
        int proximaPosicao = (maxPosicao != null ? maxPosicao : 0) + 1;

        FilaAtendimento fila = FilaAtendimento.builder()
                .pacienteId(paciente.getId())
                .posicao(proximaPosicao)
                .status(StatusFila.AGUARDANDO)
                .sala(consultorio != null ? consultorio : "Consultório 01")
                .medicoNome(medicoNome != null ? medicoNome : "Dr. Vagner Domingos — Tricologia Integrada")
                .horarioEntrada(LocalDateTime.now())
                .build();

        fila = filaRepository.save(fila);
        realtimeService.broadcast("FILA_ATUALIZADA", fila);
        return fila;
    }

    @Transactional
    public void removerPacienteDaFila(Long filaId) {
        FilaAtendimento fila = filaRepository.findById(filaId)
                .orElseThrow(() -> new IllegalArgumentException("Registro de fila não encontrado."));

        fila.setStatus(StatusFila.CANCELADO);
        filaRepository.save(fila);
        realtimeService.broadcast("FILA_ATUALIZADA", fila);
    }

    @Transactional
    public FilaAtendimento rechamarPaciente(Long filaId) {
        FilaAtendimento fila = filaRepository.findById(filaId)
                .orElseThrow(() -> new IllegalArgumentException("Registro de fila não encontrado."));

        fila.setStatus(StatusFila.CHAMADO);
        fila.setHorarioChamada(LocalDateTime.now());
        fila = filaRepository.save(fila);

        // Disparo de notificação sonora e visual no telão da recepção e portais
        realtimeService.broadcast("PACIENTE_CHAMADO", fila);
        realtimeService.broadcast("FILA_ATUALIZADA", fila);
        return fila;
    }

    public List<Agendamento> listarAgendamentosHoje() {
        LocalDateTime inicioDia = LocalDate.now().atStartOfDay();
        LocalDateTime fimDia = LocalDate.now().atTime(23, 59, 59);
        return agendamentoRepository.findByDataHoraBetweenOrderByDataHoraAsc(inicioDia, fimDia);
    }

    @Transactional
    public Agendamento agendarConsultaPresencial(Long pacienteId, LocalDateTime dataHora, String medicoNome) {
        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new IllegalArgumentException("Paciente não encontrado com ID: " + pacienteId));

        Agendamento agendamento = Agendamento.builder()
                .pacienteId(paciente.getId())
                .dataHora(dataHora != null ? dataHora : LocalDateTime.now().plusHours(1))
                .status(StatusAgendamento.CONFIRMADO)
                .medicoResponsavel(medicoNome != null ? medicoNome : "Dr. Vagner Domingos — Tricologia Integrada")
                .motivoAlteracao("Agendamento presencial realizado pela Recepção")
                .dataSolicitacao(LocalDateTime.now())
                .build();

        agendamento = agendamentoRepository.save(agendamento);
        realtimeService.broadcast("NOVO_AGENDAMENTO", agendamento);
        return agendamento;
    }

    public List<ProntuarioAtendimento> consultarReceituariosPaciente(Long pacienteId) {
        return prontuarioRepository.findByPacienteIdOrderByDataAtendimentoDesc(pacienteId);
    }
}
