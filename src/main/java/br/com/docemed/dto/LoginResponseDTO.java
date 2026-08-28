package br.com.docemed.dto;

import br.com.docemed.model.PerfilUsuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponseDTO {
    private Long id;
    private String login;
    private String nome;
    private PerfilUsuario perfil;
    private Long pacienteId;
    private String token;
    private String redirectUrl;
}
