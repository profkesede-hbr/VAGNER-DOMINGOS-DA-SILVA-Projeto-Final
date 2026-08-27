package br.com.docemed.model;

/**
 * Escala de Bristol — Classificação médica do funcionamento intestinal.
 * Utilizada na Pergunta 32 da Ficha de Avaliação Capilar / Doc-eMed.
 */
public enum TipoIntestinalBristol {

    TIPO_1("Tipo 1 — Pedaços separados, duros como amendoim (constipação severa)"),
    TIPO_2("Tipo 2 — Forma de salsicha, mas segmentada (constipação leve)"),
    TIPO_3("Tipo 3 — Forma de salsicha, com fendas na superfície (normal)"),
    TIPO_4("Tipo 4 — Forma de salsicha ou cobra, lisa e mole (ideal)"),
    TIPO_5("Tipo 5 — Pedaços moles, mas com contornos nítidos (falta de fibra)"),
    TIPO_6("Tipo 6 — Pedaços aerados, contornos esgarçados (diarreia leve)"),
    TIPO_7("Tipo 7 — Aquosa, sem peças sólidas (diarreia severa)");

    private final String descricao;

    TipoIntestinalBristol(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
