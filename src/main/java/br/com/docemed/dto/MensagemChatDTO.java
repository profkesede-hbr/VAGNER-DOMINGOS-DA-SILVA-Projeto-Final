package br.com.docemed.dto;

import br.com.docemed.model.PerfilUsuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MensagemChatDTO {
    private Long id;
    private String remetenteLogin;
    private String remetenteNome;
    private PerfilUsuario tipoRemetente;
    private String destinatarioLogin;
    private String destinatarioNome;
    private String conteudo;
    private LocalDateTime dataEnvio;
    private Boolean lida;
}
