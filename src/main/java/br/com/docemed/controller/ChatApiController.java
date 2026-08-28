package br.com.docemed.controller;

import br.com.docemed.dto.MensagemChatDTO;
import br.com.docemed.dto.UsuarioOnlineDTO;
import br.com.docemed.service.ChatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
@Tag(name = "Chat Interno Clínico (Recepção ↔ Médicos)", description = "Comunicação privada 1-para-1 em tempo real entre médicos e recepcionistas com status online")
public class ChatApiController {

    private final ChatService chatService;

    @PostMapping("/enviar")
    @Operation(summary = "Enviar Mensagem Privada", description = "Registra a mensagem e dispara notificação SSE instantânea para o destinatário.")
    public ResponseEntity<MensagemChatDTO> enviarMensagem(@Valid @RequestBody MensagemChatDTO dto) {
        return ResponseEntity.ok(chatService.enviarMensagem(dto));
    }

    @GetMapping("/conversa")
    @Operation(summary = "Histórico de Conversa Privada", description = "Retorna histórico de mensagens trocadas entre dois usuários.")
    public ResponseEntity<List<MensagemChatDTO>> listarConversa(@RequestParam String meuLogin, @RequestParam String outroLogin) {
        return ResponseEntity.ok(chatService.listarConversaPrivada(meuLogin, outroLogin));
    }

    @GetMapping("/equipe")
    @Operation(summary = "Listar Equipe e Status Online", description = "Retorna a lista de médicos e atendentes da recepção com status online/offline.")
    public ResponseEntity<List<UsuarioOnlineDTO>> listarEquipe(@RequestParam(required = false) String meuLogin) {
        return ResponseEntity.ok(chatService.listarEquipeStatus(meuLogin));
    }

    @PostMapping("/heartbeat")
    @Operation(summary = "Registrar Atividade Online", description = "Atualiza o timestamp de atividade do usuário para sinalizar que está online.")
    public ResponseEntity<?> registrarHeartbeat(@RequestParam String login) {
        chatService.registrarAtividade(login);
        return ResponseEntity.ok(Map.of("status", "ok"));
    }
}
