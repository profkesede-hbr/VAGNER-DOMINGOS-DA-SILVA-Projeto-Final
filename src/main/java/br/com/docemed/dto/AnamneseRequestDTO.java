package br.com.docemed.dto;

import br.com.docemed.model.TipoIntestinalBristol;
import java.util.Map;

/**
 * DTO para preenchimento da Anamnese e Avaliação Capilar / Estética (Doc-eMed).
 */
public record AnamneseRequestDTO(

        Long pacienteId,
        String queixaPrincipal,

        // ─── SEÇÃO 1: TRICOLOGIA ─────────────────────────────────────────────
        String tipoCabelo, // Liso, Ondulado, Cacheado, Crespo
        String pigmentacaoResidual, // Pigmentado, Difuso, Semi pigmentado
        String caracteristicaCouroCabeludo, // Normal, Seco, Oleoso, Misto

        // ─── SEÇÃO 2: ALIMENTAÇÃO ────────────────────────────────────────────
        String ingereFrutas, // Sim, Nao, Pouco
        String ingereLegumes, // Sim, Nao, Pouco
        String ingereVerduras, // Sim, Nao, Pouco
        String coposAguaDiarios,
        Boolean consomeAlimentosGordurosos,
        Boolean consomeGluten,
        Boolean consomeLactose,
        String descricaoHabitosAlimentares,

        // ─── SEÇÃO 3: HISTÓRICO DE SAÚDE (25 PERGUNTAS) ──────────────────────
        Boolean problemaCoracao,
        String detalheProblemaCoracao,
        Boolean diabetesDescompensada,
        Boolean cancer,
        Boolean possuiAlergia,
        String detalheAlergia,
        Boolean pressaoAltaDescompensada,
        Boolean problemasNeurologicos,
        String detalheNeurologico,
        Boolean cirurgiaUltimos6Meses,
        String detalheCirurgia,
        Boolean estresse,
        Boolean problemasTireoide,
        String detalheTireoide,
        Boolean covid19,
        Boolean problemaRenalHepatico,
        String detalheRenalHepatico,
        Boolean gestante,
        Boolean lactante,
        Boolean osteoporose,
        Boolean depressao,
        Boolean alcoolismo,
        Boolean tabagismo,
        Boolean possuiPinos,
        String localPinos,
        Boolean proteseDentaria,
        Boolean marcaPasso,
        Boolean sindromePanico,
        Boolean doencaAutoimune,
        String detalheDoencaAutoimune,
        Boolean dengue,
        Boolean chikungunya,
        Boolean maCirculacaoTrombose,

        // ─── SEÇÃO 4: MEDICAMENTOS, GINECOLÓGICO & QUEIXA CAPILAR ────────────
        Boolean usoMedicamentoContinuo,
        String detalheMedicamentos,
        Boolean usoAnticoncepcional,
        String detalheAnticoncepcional,
        Boolean ovarioPolicistico,
        Boolean problemaGinecologico,
        String detalheGinecologico,
        String cicloMenstrual,
        String problemaGastrointestinal,

        // Pergunta 32 — Escala de Bristol
        Boolean vaiBanheiroRegularmente,
        TipoIntestinalBristol tipoIntestinalBristol,
        String tempoQuedaCapilar,
        Boolean quedaAposPeriodoMarcante,
        Boolean nascimentoNovosFios,
        Boolean perdaPelosCorpo,
        Boolean perdaDensidadeCabelo,

        // ─── SEÇÃO 5: ASPECTO DO CABELO & QUÍMICA ────────────────────────────
        Boolean quimicaUltimos12Meses,
        String detalhesQuimica,
        String condicaoFio,

        // ─── SEÇÃO 6: DADOS CLÍNICOS DO COURO CABELUDO ───────────────────────
        Boolean implanteCapilar,
        String testeTracao,
        Boolean quedaNaFamilia,
        Boolean caspaSeca,
        Boolean caspaUmida,
        Boolean dermatiteSeborreica,
        Boolean exposicaoSolarExcessiva,
        Boolean psoriasie,
        Boolean feridasInflamacao,
        Boolean pediculose,
        Boolean tricotilomania,
        Boolean dorCouroCabeludo,
        String dorCouroCabeludoLocal,
        Boolean foliculite,
        String foliculiteLocal,
        Boolean lesoesSinais,
        String lesoesSinaisLocal,
        Boolean verrugas,
        String verrugasLocal,
        Boolean falhaBarba,
        Boolean ardencia,
        Boolean presencaCicatrizes,
        String presencaCicatrizesLocal,
        Boolean vermelhidao,
        String regioesRarefacao,

        // ─── SEÇÃO 6.1: TRICOSCOPIA (HASTE / COURO CABELUDO) ──────────────────
        TricoscopiaDTO tricoscopia,

        // ─── SEÇÃO 7: HÁBITOS DE VIDA & CUIDADOS ──────────────────────────────
        Boolean lavaCabeloTodosDias,
        Integer diasLavagemSemana,
        Boolean usaSecadorPrancha,
        String frequenciaSecadorPrancha,
        Boolean usaProtetorTermico,
        Boolean usaCabeloPreso,
        Boolean praticaAtividadeFisica,
        Boolean fazendoDieta,
        Boolean usaGel,
        Boolean usaBone,
        Boolean usaCapacete,
        String tratamentosCapilaresAnteriores,

        // ─── SEÇÃO 8: HISTÓRICO FAMILIAR & EXAMES ────────────────────────────
        Boolean calvicieFamilia,
        Boolean alopeciaAreataFamilia,

        // ─── SEÇÃO 9: ALOPECIAS NÃO CICATRICIAIS ──────────────────────────
        AlopeciaDTO alopecias,

        // ─── SEÇÃO 10: EXAMES LABORATORIAIS (dentro do contexto da Anamnese) ────────
        ExamesDTO exames,

        // ─── SEÇÃO 10: INDICAÇÃO DO TERAPEUTA ─────────────────────────
        String aspectoCabeloCouroCabeludo,
        String tratamentoIndicado,
        String manutencaoEmCasa,
        Boolean aceiteTermoResponsabilidade,
        String observacoesGerais,

        // ─── RESPOSTAS DINÂMICAS EXTRAS (Map: perguntaId -> resposta) ────────
        Map<Long, String> respostasDinamicas
) {
    /**
     * Sub-record com os resultados dos Exames Laboratoriais da Ficha.
     * Enviado como objeto JSON aninhado dentro da Anamnese.
     */
    public record ExamesDTO(
            Boolean possuiExames,
            String hemograma,     String acidoFolico,  String ferritina,
            String vitaminaD,     String biotina,      String vitB12,
            String vitB6,         String zinco,        String cobre,
            String pcr,           String vhs,          String fan,
            String vitA,          String vitC,         String selenio,
            String dht,           String tsh,          String t4Livre,
            String antiTpo,       String vitE,         String testosterona,
            String outrosExames
    ) {}

    /**
     * Sub-record para Alopecias Não Cicatriciais.
     * classificacao: ex. "Hamilton IV", "Ludwig II", "Ofiásica"
     * scanPath: caminho/URL da imagem escaneada do formulário físico
     */
    public record AlopeciaDTO(
            // Androgenética
            Boolean androgeneticaPresente,
            String  androgeneticaClassificacao,
            String  androgeneticaScanPath,
            // Areata
            Boolean areataPresente,
            String  areataClassificacao,
            String  areataScanPath,
            // Eflúvio Telógeno
            Boolean efluvioTelogeno,
            Boolean etPuerperio,
            Boolean etEmagrecimento,
            Boolean etDeficienciaNutricional,
            Boolean etEstresseProlongado,
            Boolean etDoencasSistemica,
            Boolean etMedicamentos,
            String  etOutros,
            // Eflúvio Anágeno
            Boolean efluvioAnageno,
            Boolean eaQuimioterapia,
            Boolean eaInfeccoes,
            Boolean eaToxicos,
            Boolean eaRadiacao,
            Boolean eaDoencasAutoimunes,
            // Alopecia Cicatricial
            Boolean alopecia_cicatricial,
            Boolean acFoliculiteQueloidiana,
            Boolean acFoliculiteDecalvante,
            Boolean acFibrosanteFrontal,
            Boolean acLiquenPlanoPilar,
            Boolean acLupusDiscoide,
            // Outros
            String  outrasAlopecias
    ) {}

    /**
     * Sub-record para a seção HASTE / COURO CABELUDO (Tricoscopia).
     */
    public record TricoscopiaDTO(
            Boolean diversidadeDiametros,
            String  diversidadeDiametrosLocal,
            Boolean fiosVellus,
            String  fiosVellusLocal,
            String  distanciaUnidadesFoliculares,
            String  distanciaUnidadesFolicularesLocal,
            Boolean fiosEmTufos,
            String  fiosEmTufosLocal,
            Boolean pontosPretos,
            String  pontosPretosLocal,
            Boolean pontosAmarelos,
            String  pontosAmarelosLocal,
            String  pontosBrancos,
            String  pontosBrancosLocal,
            Boolean pontosVermelhos,
            String  pontosVermelhosLocal,
            Boolean sinalPeripilar,
            String  sinalPeripilarLocal,
            Boolean regiaoSemFoliculos,
            String  regiaoSemFoliculosLocal,
            Boolean vasosArborizantes,
            String  vasosArborizantesLocal,
            Boolean descamacao,
            String  descamacaoLocal
    ) {}
}
