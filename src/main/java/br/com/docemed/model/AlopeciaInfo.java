package br.com.docemed.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Objeto embarcado representando a seção completa de Alopecias da Ficha de Avaliação Capilar.
 *
 * Inclui:
 *   - Alopecia Androgenética (híbrido: checkbox + classificação textual + scan)
 *   - Alopecia Areata        (híbrido: checkbox + classificação textual + scan)
 *   - Eflúvio Telógeno       (checkbox + causas específicas)
 *   - Eflúvio Anágeno        (checkbox + causas específicas)
 *   - Alopecia Cicatricial   (checkbox + subtipos)
 */
@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlopeciaInfo {

    // =========================================================================
    // ALOPECIA ANDROGENÉTICA
    // =========================================================================
    @Column(name = "alopecia_androgenetica_presente")
    private Boolean alopeciaAndrogeneticaPresente = false;

    /** Classificação textual: ex. "Hamilton IV" ou "Ludwig II" */
    @Column(name = "alopecia_androgenetica_classificacao", length = 100)
    private String alopeciaAndrogeneticaClassificacao;

    /** Caminho/URL do arquivo de imagem escaneada do formulário físico */
    @Column(name = "alopecia_androgenetica_scan", length = 500)
    private String alopeciaAndrogeneticaScanPath;

    // =========================================================================
    // ALOPECIA AREATA
    // =========================================================================
    @Column(name = "alopecia_areata_presente")
    private Boolean alopeciaAreataPresente = false;

    /** Classificação textual: ex. "Ofiásica", "Totalis", "Em placas" */
    @Column(name = "alopecia_areata_classificacao", length = 100)
    private String alopeciaAreataClassificacao;

    /** Caminho/URL do arquivo de imagem escaneada do formulário físico */
    @Column(name = "alopecia_areata_scan", length = 500)
    private String alopeciaAreataScanPath;

    // =========================================================================
    // EFLÚVIO TELÓGENO
    // Queda difusa de cabelo por fases do ciclo capilar — causas variadas
    // =========================================================================
    @Column(name = "efluvia_telogeno_presente")
    private Boolean efluvioTelogeno = false;

    @Column(name = "et_puerperio")
    private Boolean etPuerperio = false;          // Puerpério (pós-parto)

    @Column(name = "et_emagrecimento")
    private Boolean etEmagrecimento = false;       // Emagrecimento

    @Column(name = "et_deficiencia_nutricional")
    private Boolean etDeficienciaNutricional = false; // Deficiência de ferro, zinco, vit D, B12

    @Column(name = "et_estresse_prolongado")
    private Boolean etEstresseProlongado = false;  // Estados estressantes prolongados

    @Column(name = "et_doencas_sistemicas")
    private Boolean etDoencasSistemica = false;    // Lúpus, anemia, tireoide, hepatites, diabetes

    @Column(name = "et_medicamentos")
    private Boolean etMedicamentos = false;        // Contraceptivos, amiodarona, anticoagulantes, etc.

    @Column(name = "et_outros", columnDefinition = "TEXT")
    private String etOutros;                       // Campo aberto para outras causas

    // =========================================================================
    // EFLÚVIO ANÁGENO
    // Queda na fase de crescimento — causas mais agressivas
    // =========================================================================
    @Column(name = "efluvia_anageno_presente")
    private Boolean efluvioAnageno = false;

    @Column(name = "ea_quimioterapia")
    private Boolean eaQuimioterapia = false;       // Quimioterapia

    @Column(name = "ea_infeccoes")
    private Boolean eaInfeccoes = false;           // Sífilis ou Kerion

    @Column(name = "ea_toxicos")
    private Boolean eaToxicos = false;             // Tálio, Arsênio, Cádmio, Mercúrio, Bismuto

    @Column(name = "ea_radiacao")
    private Boolean eaRadiacao = false;            // Radiação ou Radioterapia

    @Column(name = "ea_doencas_autoimunes")
    private Boolean eaDoencasAutoimunes = false;   // Alopecia Areata Incognita, Lúpus, Pênfigo Vulgar

    // =========================================================================
    // ALOPECIA CICATRICIAL
    // Destruição permanente do folículo — subtipos clínicos
    // =========================================================================
    @Column(name = "alopecia_cicatricial_presente")
    private Boolean alopecia_cicatricial = false;

    @Column(name = "ac_foliculite_queloidiana")
    private Boolean acFoliculiteQueloidiana = false;

    @Column(name = "ac_foliculite_decalvante")
    private Boolean acFoliculiteDecalvante = false;

    @Column(name = "ac_fibrosante_frontal")
    private Boolean acFibrosanteFrontal = false;   // Alopecia Fibrosante Frontal

    @Column(name = "ac_liquen_plano_pilar")
    private Boolean acLiquenPlanoPilar = false;

    @Column(name = "ac_lupus_discoide")
    private Boolean acLupusDiscoide = false;       // Lúpus Eritematoso Discoide

    // ─── Campo de texto livre para outros tipos não listados ─────────────────
    @Column(name = "outras_alopecias", length = 300)
    private String outrasAlopecias;
}
