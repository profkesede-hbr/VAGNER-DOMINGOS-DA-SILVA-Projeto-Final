package br.com.docemed.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Entidade que representa o Cadastro do Cliente/Paciente.
 * Baseado na seção "DADOS DO CLIENTE" da Ficha de Avaliação Capilar (Doc-eMed).
 */
@Entity
@Table(name = "pacientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ─── DADOS PESSOAIS ───────────────────────────────────────────────────────

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(length = 20)
    private String cpf;

    @Column(length = 20)
    private String rg;

    @Column(length = 30)
    private String sexo; // Feminino, Masculino, Outro, Não informado

    @Column(length = 30)
    private String estadoCivil;

    @Column(length = 150)
    private String profissao;

    @Column(name = "indicado_por", length = 150)
    private String indicadoPor;

    // ─── CONTATO ──────────────────────────────────────────────────────────────

    @Column(length = 20)
    private String telefone;

    @Column(length = 20)
    private String celularWhatsapp;

    @Column(length = 150)
    private String email;

    @Column(name = "contato_emergencia_nome", length = 150)
    private String contatoEmergenciaNome;

    @Column(name = "contato_emergencia_telefone", length = 30)
    private String contatoEmergenciaTelefone;

    // ─── ENDEREÇO ─────────────────────────────────────────────────────────────

    @Column(length = 250)
    private String endereco;

    @Column(length = 20)
    private String numero;

    @Column(length = 100)
    private String complemento;

    @Column(length = 100)
    private String bairro;

    @Column(length = 100)
    private String cidade;

    @Column(length = 10)
    private String estado;

    @Column(length = 10)
    private String cep;

    // ─── CONVÊNIO / PLANO ─────────────────────────────────────────────────────

    @Column(length = 100)
    private String convenio;

    // ─── CONTROLE INTERNO ─────────────────────────────────────────────────────

    @Column(name = "data_cadastro", nullable = false, updatable = false, columnDefinition = "DATETIME")
    private LocalDateTime dataCadastro;

    @PrePersist
    protected void aoSalvar() {
        this.dataCadastro = LocalDateTime.now();
    }

    @Column(nullable = false)
    private Boolean ativo = true;
}
