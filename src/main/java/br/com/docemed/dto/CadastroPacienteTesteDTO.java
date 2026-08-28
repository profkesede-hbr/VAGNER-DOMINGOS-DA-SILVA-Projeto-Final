package br.com.docemed.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CadastroPacienteTesteDTO {

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotBlank(message = "O telefone / WhatsApp é obrigatório")
    private String telefoneWhatsapp;

    @NotBlank(message = "O login de acesso é obrigatório")
    private String login;

    @NotBlank(message = "A senha de acesso é obrigatória")
    private String senha;

    private String email;
    private String cpf;
    private String dataNascimento;
    private String sexo;
    private String cidade;
}
