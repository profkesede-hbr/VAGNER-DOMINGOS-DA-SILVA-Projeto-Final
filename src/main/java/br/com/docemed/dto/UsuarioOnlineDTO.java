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
public class UsuarioOnlineDTO {
    private String login;
    private String nome;
    private PerfilUsuario perfil;
    private String cargoDescricao;
    private String consultorio;
    private Boolean online;
    private LocalDateTime ultimoVisto;
}
