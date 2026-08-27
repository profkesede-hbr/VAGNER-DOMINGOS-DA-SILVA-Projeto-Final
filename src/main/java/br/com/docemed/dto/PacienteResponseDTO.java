package br.com.docemed.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * DTO de resposta com os dados cadastrais do Paciente.
 */
public record PacienteResponseDTO(
        Long id,

        // ─── DADOS PESSOAIS ───────────────────────────────────────────────────
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
        String convenio,

        // ─── CONTROLE INTERNO ─────────────────────────────────────────────────
        LocalDateTime dataCadastro,
        Boolean ativo
) {}
