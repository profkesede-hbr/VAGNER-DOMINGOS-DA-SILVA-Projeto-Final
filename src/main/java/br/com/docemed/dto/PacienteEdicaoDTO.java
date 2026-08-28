package br.com.docemed.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "DTO para Edição Cadastral do Paciente pela Recepção")
public class PacienteEdicaoDTO {
    private String nome;
    private String cpf;
    private String rg;
    private String dataNascimento;
    private String sexo;
    private String estadoCivil;
    private String profissao;
    private String telefone;
    private String celularWhatsapp;
    private String email;
    private String contatoEmergenciaNome;
    private String contatoEmergenciaTelefone;
    private String cep;
    private String endereco;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
}
