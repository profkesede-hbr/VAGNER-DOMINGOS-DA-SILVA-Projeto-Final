package br.com.docemed.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

/**
 * DTO para cadastro e atualização de Paciente.
 * Baseado nos campos "DADOS DO CLIENTE" da Ficha de Avaliação Capilar (Doc-eMed).
 */
public record PacienteRequestDTO(

        // ─── DADOS PESSOAIS ───────────────────────────────────────────────────
        @NotBlank(message = "O nome do paciente é obrigatório.")
        String nome,

        LocalDate dataNascimento,
        String cpf,
        String rg,
        String sexo,
        String profissao,
        String indicadoPor,

        // ─── CONTATO ──────────────────────────────────────────────────────────
        String telefone,
        String celularWhatsapp,
        String email,

        // ─── ENDEREÇO ─────────────────────────────────────────────────────────
        String endereco,
        String bairro,
        String cidade,
        String cep,

        // ─── CONVÊNIO ─────────────────────────────────────────────────────────
        String convenio
) {}
