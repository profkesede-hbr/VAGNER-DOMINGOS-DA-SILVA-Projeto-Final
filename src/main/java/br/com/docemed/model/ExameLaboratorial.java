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
    @Column(name = "exame_hemograma", length = 50)
    private String hemograma;

    @Column(name = "exame_acido_folico", length = 50)
    private String acidoFolico;

    @Column(name = "exame_ferritina", length = 50)
    private String ferritina;

    @Column(name = "exame_vitamina_d", length = 50)
    private String vitaminaD;

    @Column(name = "exame_biotina", length = 50)
    private String biotina;

    @Column(name = "exame_vit_b12", length = 50)
    private String vitB12;

    @Column(name = "exame_vit_b6", length = 50)
    private String vitB6;

    // ─── COLUNA 2 ────────────────────────────────────────────────────────────
    @Column(name = "exame_zinco", length = 50)
    private String zinco;

    @Column(name = "exame_cobre", length = 50)
    private String cobre;

    @Column(name = "exame_pcr", length = 50)
    private String pcr;

    @Column(name = "exame_vhs", length = 50)
    private String vhs;

    @Column(name = "exame_fan", length = 50)
    private String fan;

    @Column(name = "exame_vit_a", length = 50)
    private String vitA;

    @Column(name = "exame_vit_c", length = 50)
    private String vitC;

    @Column(name = "exame_selenio", length = 50)
    private String selenio;

    // ─── COLUNA 3 ────────────────────────────────────────────────────────────
    @Column(name = "exame_dht", length = 50)
    private String dht;

    @Column(name = "exame_tsh", length = 50)
    private String tsh;

    @Column(name = "exame_t4_livre", length = 50)
    private String t4Livre;

    @Column(name = "exame_anti_tpo", length = 50)
    private String antiTpo;

    @Column(name = "exame_vit_e", length = 50)
    private String vitE;

    @Column(name = "exame_testosterona", length = 50)
    private String testosterona;

    @Column(name = "exame_outros", columnDefinition = "TEXT")
    private String outrosExames;
}
