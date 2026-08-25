package br.com.docemed.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Objeto embarcado representando a seção de Exames Laboratoriais da Ficha de Avaliação Capilar.
 * Os campos são armazenados diretamente na tabela `anamneses` (sem tabela separada).
 */
@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExameLaboratorial {

    @Column(name = "possui_exames")
    private Boolean possuiExames = false;

    // ─── COLUNA 1 ────────────────────────────────────────────────────────────
    @Column(name = "exame_hemograma")
    private String hemograma;

    @Column(name = "exame_acido_folico")
    private String acidoFolico;

    @Column(name = "exame_ferritina")
    private String ferritina;

    @Column(name = "exame_vitamina_d")
    private String vitaminaD;

    @Column(name = "exame_biotina")
    private String biotina;

    @Column(name = "exame_vit_b12")
    private String vitB12;

    @Column(name = "exame_vit_b6")
    private String vitB6;

    // ─── COLUNA 2 ────────────────────────────────────────────────────────────
    @Column(name = "exame_zinco")
    private String zinco;

    @Column(name = "exame_cobre")
    private String cobre;

    @Column(name = "exame_pcr")
    private String pcr;

    @Column(name = "exame_vhs")
    private String vhs;

    @Column(name = "exame_fan")
    private String fan;

    @Column(name = "exame_vit_a")
    private String vitA;

    @Column(name = "exame_vit_c")
    private String vitC;

    @Column(name = "exame_selenio")
    private String selenio;

    // ─── COLUNA 3 ────────────────────────────────────────────────────────────
    @Column(name = "exame_dht")
    private String dht;

    @Column(name = "exame_tsh")
    private String tsh;

    @Column(name = "exame_t4_livre")
    private String t4Livre;

    @Column(name = "exame_anti_tpo")
    private String antiTpo;

    @Column(name = "exame_vit_e")
    private String vitE;

    @Column(name = "exame_testosterona")
    private String testosterona;

    @Column(name = "exame_outros", length = 500)
    private String outrosExames;
}
