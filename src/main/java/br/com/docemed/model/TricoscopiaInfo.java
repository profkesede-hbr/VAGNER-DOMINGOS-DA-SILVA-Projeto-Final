package br.com.docemed.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Objeto embarcado representando a seção HASTE / COURO CABELUDO (Exame de Tricoscopia).
 * Baseado na Página 6 da Ficha de Avaliação Capilar.
 */
@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TricoscopiaInfo {

    // ─── DIVERSIDADE DE DIÂMETROS ─────────────────────────────────────────────
    @Column(name = "trico_diversidade_diametros")
    private Boolean diversidadeDiametros = false;

    @Column(name = "trico_diversidade_diametros_local", length = 100)
    private String diversidadeDiametrosLocal;

    // ─── FIOS VELLUS ──────────────────────────────────────────────────────────
    @Column(name = "trico_fios_vellus")
    private Boolean fiosVellus = false;

    @Column(name = "trico_fios_vellus_local", length = 100)
    private String fiosVellusLocal;

    // ─── DISTÂNCIA ENTRE UNIDADES FOLICULARES (normal / alterado) ─────────────
    @Column(name = "trico_distancia_unidades_foliculares", length = 50)
    private String distanciaUnidadesFoliculares;

    @Column(name = "trico_distancia_unidades_local", length = 100)
    private String distanciaUnidadesFolicularesLocal;

    // ─── FIOS EM TUFOS ────────────────────────────────────────────────────────
    @Column(name = "trico_fios_em_tufos")
    private Boolean fiosEmTufos = false;

    @Column(name = "trico_fios_em_tufos_local", length = 100)
    private String fiosEmTufosLocal;

    // ─── PONTOS PRETOS ────────────────────────────────────────────────────────
    @Column(name = "trico_pontos_pretos")
    private Boolean pontosPretos = false;

    @Column(name = "trico_pontos_pretos_local", length = 100)
    private String pontosPretosLocal;

    // ─── PONTOS AMARELOS ──────────────────────────────────────────────────────
    @Column(name = "trico_pontos_amarelos")
    private Boolean pontosAmarelos = false;

    @Column(name = "trico_pontos_amarelos_local", length = 100)
    private String pontosAmarelosLocal;

    // ─── PONTOS BRANCOS (regulares / irregulares) ─────────────────────────────
    @Column(name = "trico_pontos_brancos", length = 50)
    private String pontosBrancos;

    @Column(name = "trico_pontos_brancos_local", length = 100)
    private String pontosBrancosLocal;

    // ─── PONTOS VERMELHOS ─────────────────────────────────────────────────────
    @Column(name = "trico_pontos_vermelhos")
    private Boolean pontosVermelhos = false;

    @Column(name = "trico_pontos_vermelhos_local", length = 100)
    private String pontosVermelhosLocal;

    // ─── SINAL PERIPILAR ──────────────────────────────────────────────────────
    @Column(name = "trico_sinal_peripilar")
    private Boolean sinalPeripilar = false;

    @Column(name = "trico_sinal_peripilar_local", length = 100)
    private String sinalPeripilarLocal;

    // ─── REGIÃO SEM FOLÍCULOS ─────────────────────────────────────────────────
    @Column(name = "trico_regiao_sem_foliculos")
    private Boolean regiaoSemFoliculos = false;

    @Column(name = "trico_regiao_sem_foliculos_local", length = 100)
    private String regiaoSemFoliculosLocal;

    // ─── VASOS ARBORIZANTES ───────────────────────────────────────────────────
    @Column(name = "trico_vasos_arborizantes")
    private Boolean vasosArborizantes = false;

    @Column(name = "trico_vasos_arborizantes_local", length = 100)
    private String vasosArborizantesLocal;

    // ─── DESCAMAÇÃO ───────────────────────────────────────────────────────────
    @Column(name = "trico_descamacao")
    private Boolean descamacao = false;

    @Column(name = "trico_descamacao_local", length = 100)
    private String descamacaoLocal;
}
