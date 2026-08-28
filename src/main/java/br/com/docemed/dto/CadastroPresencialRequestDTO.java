package br.com.docemed.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "DTO de Cadastro Presencial de Paciente pela Recepção")
public class CadastroPresencialRequestDTO {

    @NotBlank(message = "O nome completo é obrigatório.")
    @Schema(description = "Nome completo do paciente", example = "Carlos Eduardo Souza")
    private String nome;

    @NotBlank(message = "O CPF é obrigatório para geração automática do login.")
    @Schema(description = "CPF do paciente (usado como login oficial)", example = "123.456.789-00")
    private String cpf;

    @Schema(description = "RG do paciente", example = "12.345.678-9")
    private String rg;

    @Schema(description = "Data de nascimento", example = "1988-05-15")
    private String dataNascimento;

    @Schema(description = "Sexo", example = "Masculino")
    private String sexo;

    @Schema(description = "Estado Civil", example = "Casado(a)")
    private String estadoCivil;

    @Schema(description = "Profissão", example = "Analista de Sistemas")
    private String profissao;

    @Schema(description = "Telefone fixo", example = "(11) 3333-2222")
    private String telefone;

    @Schema(description = "Celular ou WhatsApp do paciente ou responsável", example = "(11) 98765-4321")
    private String celularWhatsapp;

    @NotBlank(message = "O e-mail é obrigatório para envio do login e senha provisória.")
    @Schema(description = "E-mail para recebimento das credenciais de acesso", example = "carlos.souza@gmail.com")
    private String email;

    @Schema(description = "Nome do contato de emergência", example = "Ana Souza (Esposa)")
    private String contatoEmergenciaNome;

    @Schema(description = "Telefone do contato de emergência", example = "(11) 99999-8888")
    private String contatoEmergenciaTelefone;

    @Schema(description = "CEP", example = "01310-100")
    private String cep;

    @Schema(description = "Endereço / Logradouro", example = "Av. Paulista")
    private String endereco;

    @Schema(description = "Número", example = "1500")
    private String numero;

    @Schema(description = "Complemento", example = "Apto 82")
    private String complemento;

    @Schema(description = "Bairro", example = "Bela Vista")
    private String bairro;

    @Schema(description = "Cidade", example = "São Paulo")
    private String cidade;

    @Schema(description = "Estado (UF)", example = "SP")
    private String estado;

    @Schema(description = "Incluir imediatamente na fila do dia após o cadastro?", example = "true")
    @Builder.Default
    private Boolean incluirImediatamenteNaFila = true;
}
