package br.com.docemed.service;

import br.com.docemed.dto.MensagemChatDTO;
import br.com.docemed.dto.UsuarioOnlineDTO;
import br.com.docemed.model.MensagemChat;
import br.com.docemed.model.PerfilUsuario;
import br.com.docemed.model.Usuario;
import br.com.docemed.repository.MensagemChatRepository;
import br.com.docemed.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatService {

    private final MensagemChatRepository mensagemRepository;
    private final UsuarioRepository usuarioRepository;
    private final RealtimeNotificationService realtimeService;

    // Mapa de heartbeat de usuários conectados recentemente
    private final Map<String, LocalDateTime> usuariosAtivosHeartbeat = new ConcurrentHashMap<>();

    public void registrarAtividade(String login) {
        if (login != null && !login.isBlank()) {
            usuariosAtivosHeartbeat.put(login.trim().toLowerCase(), LocalDateTime.now());
        }
    }

    @Transactional
    public MensagemChatDTO enviarMensagem(MensagemChatDTO dto) {
        if (dto.getConteudo() == null || dto.getConteudo().isBlank()) {
            throw new IllegalArgumentException("O conteúdo da mensagem não pode estar vazio.");
        }

        if (dto.getDestinatarioLogin() == null || dto.getDestinatarioLogin().isBlank()) {
            throw new IllegalArgumentException("O destinatário da conversa privada é obrigatório.");
        }

        String remetente = dto.getRemetenteLogin() != null ? dto.getRemetenteLogin().trim().toLowerCase() : "recep";
        String destinatario = dto.getDestinatarioLogin().trim().toLowerCase();

        registrarAtividade(remetente);

        // Resolver nome do destinatário se não veio
        String destNome = dto.getDestinatarioNome();
        if (destNome == null || destNome.isBlank()) {
            destNome = usuarioRepository.findByLogin(destinatario)
                    .map(Usuario::getNome)
                    .orElse("Profissional Doc-eMed");
        }

        String remNome = dto.getRemetenteNome();
        if (remNome == null || remNome.isBlank()) {
            remNome = usuarioRepository.findByLogin(remetente)
                    .map(Usuario::getNome)
                    .orElse("Recepção / Atendimento");
        }

        MensagemChat mensagem = MensagemChat.builder()
                .remetenteLogin(remetente)
                .remetenteNome(remNome)
                .tipoRemetente(dto.getTipoRemetente() != null ? dto.getTipoRemetente() : PerfilUsuario.RECEPCAO)
                .destinatarioLogin(destinatario)
                .destinatarioNome(destNome)
                .conteudo(dto.getConteudo().trim())
                .dataEnvio(LocalDateTime.now())
                .lida(false)
                .build();

        mensagem = mensagemRepository.save(mensagem);

        MensagemChatDTO responseDTO = toDTO(mensagem);

        // Broadcast em tempo real via SSE com evento de mensagem privada
        realtimeService.broadcast("NOVA_MENSAGEM_CHAT", responseDTO);

        return responseDTO;
    }

    public List<MensagemChatDTO> listarConversaPrivada(String meuLogin, String outroLogin) {
        if (meuLogin == null || outroLogin == null) return Collections.emptyList();
        return mensagemRepository.findConversaPrivada(meuLogin.trim().toLowerCase(), outroLogin.trim().toLowerCase())
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<UsuarioOnlineDTO> listarEquipeStatus(String meuLogin) {
        List<Usuario> equipe = usuarioRepository.findAll().stream()
                .filter(u -> u.getPerfil() == PerfilUsuario.MEDICO || u.getPerfil() == PerfilUsuario.RECEPCAO || u.getPerfil() == PerfilUsuario.ADMIN)
                .filter(Usuario::getAtivo)
                .toList();

        LocalDateTime threshold = LocalDateTime.now().minusMinutes(15);
        String meuLoginKey = meuLogin != null ? meuLogin.trim().toLowerCase() : "";

        return equipe.stream()
                .filter(u -> !u.getLogin().equalsIgnoreCase(meuLoginKey)) // Não listar a si próprio na lista de contatos
                .map(u -> {
                    String loginKey = u.getLogin().toLowerCase();
                    LocalDateTime lastSeen = usuariosAtivosHeartbeat.getOrDefault(loginKey, LocalDateTime.now().minusMinutes(2));
                    boolean isOnline = lastSeen.isAfter(threshold) || loginKey.equals("admin") || loginKey.equals("recep") || loginKey.equals("medico");

                    String cargo = switch (u.getPerfil()) {
                        case MEDICO -> "Médico(a) Tricologista";
                        case RECEPCAO -> "Atendimento & Recepção";
                        case ADMIN -> "Direção Médica / Gestor";
                        default -> "Profissional Clínico";
                    };

                    String consultorio = u.getPerfil() == PerfilUsuario.MEDICO ? "Consultório 01" : "Balcão Recepção";

                    return UsuarioOnlineDTO.builder()
                            .login(u.getLogin())
                            .nome(u.getNome())
                            .perfil(u.getPerfil())
                            .cargoDescricao(cargo)
                            .consultorio(consultorio)
                            .online(isOnline)
                            .ultimoVisto(lastSeen)
                            .build();
                }).collect(Collectors.toList());
    }

    private MensagemChatDTO toDTO(MensagemChat m) {
        return MensagemChatDTO.builder()
                .id(m.getId())
                .remetenteLogin(m.getRemetenteLogin())
                .remetenteNome(m.getRemetenteNome())
                .tipoRemetente(m.getTipoRemetente())
                .destinatarioLogin(m.getDestinatarioLogin())
                .destinatarioNome(m.getDestinatarioNome())
                .conteudo(m.getConteudo())
                .dataEnvio(m.getDataEnvio())
                .lida(m.getLida())
                .build();
    }
}
