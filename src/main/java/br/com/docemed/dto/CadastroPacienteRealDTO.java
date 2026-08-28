package br.com.docemed.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CadastroPacienteRealDTO {

    @NotBlank(message = "O nome completo é obrigatório")
    private String nome;

    @NotBlank(message = "O CPF é obrigatório")
    private String cpf;

    private String rg;

    @NotBlank(message = "A data de nascimento é obrigatória")
    private String dataNascimento;

    private String sexo;
    private String estadoCivil;
    private String profissao;
    private String indicadoPor;

    private String telefone;

    @NotBlank(message = "O celular / WhatsApp é obrigatório")
    private String celularWhatsapp;

    @NotBlank(message = "O e-mail é obrigatório")
    private String email;

    private String cep;
    private String endereco;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;

    private String contatoEmergenciaNome;
    private String contatoEmergenciaTelefone;
    private String convenio;

    @NotBlank(message = "O login de acesso é obrigatório")
    private String login;

    @NotBlank(message = "A senha de acesso é obrigatória")
    private String senha;
}
