package br.com.docemed.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Entidade que representa a Anamnese e Avaliação Capilar / Estética do Paciente (Doc-eMed).
 */
@Entity
@Table(name = "anamneses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Anamnese {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "paciente_id", nullable = false)
    private Long pacienteId;

    @Column(name = "data_preenchimento", nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime dataPreenchimento = LocalDateTime.now();

    @Column(name = "queixa_principal", length = 1000)
    private String queixaPrincipal;

    // =========================================================
    // SEÇÃO 1 — TRICOLOGIA (INTRODUÇÃO)
    // =========================================================
    @Column(name = "tipo_cabelo")
    private String tipoCabelo; // Liso, Ondulado, Cacheado, Crespo

    @Column(name = "pigmentacao_residual")
    private String pigmentacaoResidual; // Pigmentado, Difuso, Semi pigmentado

    @Column(name = "caracteristica_couro_cabeludo")
    private String caracteristicaCouroCabeludo; // Normal, Seco, Oleoso, Misto

    // =========================================================
    // SEÇÃO 2 — ALIMENTAÇÃO
    // =========================================================
    @Column(name = "ingere_frutas")
    private String ingereFrutas; // Sim, Nao, Pouco

    @Column(name = "ingere_legumes")
    private String ingereLegumes; // Sim, Nao, Pouco

    @Column(name = "ingere_verduras")
    private String ingereVerduras; // Sim, Nao, Pouco

    @Column(name = "copos_agua_diarios")
    private String coposAguaDiarios;

    @Column(name = "consome_alimentos_gordurosos")
    private Boolean consomeAlimentosGordurosos = false;

    @Column(name = "consome_gluten")
    private Boolean consomeGluten = false;

    @Column(name = "consome_lactose")
    private Boolean consomeLactose = false;

    @Column(name = "descricao_habitos_alimentares", length = 1000)
    private String descricaoHabitosAlimentares;

    // =========================================================
    // SEÇÃO 3 — HISTÓRICO DE SAÚDE (25 PERGUNTAS)
    // =========================================================
    private Boolean problemaCoracao = false;
    private String detalheProblemaCoracao;

    private Boolean diabetesDescompensada = false;
    private Boolean cancer = false;

    private Boolean possuiAlergia = false;
    private String detalheAlergia;

    private Boolean pressaoAltaDescompensada = false;

    private Boolean problemasNeurologicos = false;
    private String detalheNeurologico;

    private Boolean cirurgiaUltimos6Meses = false;
    private String detalheCirurgia;

    private Boolean estresse = false;

    private Boolean problemasTireoide = false;
    private String detalheTireoide;

    private Boolean covid19 = false;

    private Boolean problemaRenalHepatico = false;
    private String detalheRenalHepatico;

    private Boolean gestante = false;
    private Boolean lactante = false;
    private Boolean osteoporose = false;
    private Boolean depressao = false;
    private Boolean alcoolismo = false;
    private Boolean tabagismo = false;

    private Boolean possuiPinos = false;
    private String localPinos;

    private Boolean proteseDentaria = false;
    private Boolean marcaPasso = false;
    private Boolean sindromePanico = false;

    private Boolean doencaAutoimune = false;
    private String detalheDoencaAutoimune;

    private Boolean dengue = false;
    private Boolean chikungunya = false;
    private Boolean maCirculacaoTrombose = false;

    // =========================================================
    // SEÇÃO 4 — MEDICAMENTOS, GINECOLÓGICO & QUEIXA CAPILAR
    // =========================================================
    private Boolean usoMedicamentoContinuo = false;
    private String detalheMedicamentos;

    private Boolean usoAnticoncepcional = false;
    private String detalheAnticoncepcional;

    private Boolean ovarioPolicistico = false;
    private Boolean problemaGinecologico = false;
    private String detalheGinecologico;

    private String cicloMenstrual; // regular, irregular, fluxo normal, fluxo intenso
    private String problemaGastrointestinal; // gastrite, ulceras, queimação, má digestão

    // Pergunta 32 — Escala de Bristol
    private Boolean vaiBanheiroRegularmente = false;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_intestinal_bristol")
    private TipoIntestinalBristol tipoIntestinalBristol;

    private String tempoQuedaCapilar;
    private Boolean quedaAposPeriodoMarcante = false;
    private Boolean nascimentoNovosFios = false;
    private Boolean perdaPelosCorpo = false;
    private Boolean perdaDensidadeCabelo = false;

    // =========================================================
    // SEÇÃO 5 — ASPECTO DO CABELO & QUÍMICA (ÚLTIMOS 12 MESES)
    // =========================================================
    private Boolean quimicaUltimos12Meses = false;
    private String detalhesQuimica; // Permanente, Alisamento, Progressiva, Coloração, Luzes, etc.
    private String condicaoFio; // Tricoptilose, Triconodose, Normal, Íntegro, Frizz, Quebradiço, Poroso, etc.

    // =========================================================
    // SEÇÃO 6 — DADOS CLÍNICOS DO COURO CABELUDO
    // =========================================================
    private Boolean implanteCapilar = false;
    private String testeTracao; // Positivo, Negativo
    private Boolean quedaNaFamilia = false;
    private Boolean caspaSeca = false;
    private Boolean caspaUmida = false;
    private Boolean dermatiteSeborreica = false;
    private Boolean exposicaoSolarExcessiva = false;
    private Boolean psoriasie = false;
    private Boolean feridasInflamacao = false;
    private Boolean pediculose = false;
    private Boolean tricotilomania = false;
    private Boolean dorCouroCabeludo = false;
    private String dorCouroCabeludoLocal;
    private Boolean foliculite = false;
    private String foliculiteLocal;
    private Boolean lesoesSinais = false;
    private String lesoesSinaisLocal;
    private Boolean verrugas = false;
    private String verrugasLocal;
    private Boolean falhaBarba = false;
    private Boolean ardencia = false;
    private Boolean presencaCicatrizes = false;
    private String presencaCicatrizesLocal;
    private Boolean vermelhidao = false;
    private String regioesRarefacao; // Difusa, Entradas, Coroa, Risca central, Nuca, Franja

    // =========================================================
    // SEÇÃO 6.1 — TRICOSCOPIA (HASTE / COURO CABELUDO)
    // =========================================================
    @Embedded
    private TricoscopiaInfo tricoscopia = new TricoscopiaInfo();

    // =========================================================
    // SEÇÃO 7 — HÁBITOS DE VIDA & CUIDADOS
    // =========================================================
    private Boolean lavaCabeloTodosDias = false;
    private Integer diasLavagemSemana;
    private Boolean usaSecadorPrancha = false;
    private String frequenciaSecadorPrancha;
    private Boolean usaProtetorTermico = false;
    private Boolean usaCabeloPreso = false;
    private Boolean praticaAtividadeFisica = false;
    private Boolean fazendoDieta = false;
    private Boolean usaGel = false;
    private Boolean usaBone = false;
    private Boolean usaCapacete = false;
    private String tratamentosCapilaresAnteriores;

    // =========================================================
    // SEÇÃO 8 — HISTÓRICO FAMILIAR & ALOPECIAS
    // =========================================================
    // =========================================================
    // SEÇÃO 8 — HISTÓRICO FAMILIAR & ALOPECIAS
    // =========================================================
    private Boolean calvicieFamilia = false;
    private Boolean alopeciaAreataFamilia = false;

    // =========================================================
    // SEÇÃO 9 — ALOPECIAS NÃO CICATRICIAIS (híbrido: checkbox + classificação + scan)
    // =========================================================
    @Embedded
    private AlopeciaInfo alopecias = new AlopeciaInfo();

    // =========================================================
    // SEÇÃO 9 — EXAMES LABORATORIAIS (embutido na mesma tabela)
    // =========================================================
    @Embedded
    private ExameLaboratorial exames = new ExameLaboratorial();

    // =========================================================
    // SEÇÃO 10 — INDICAÇÃO DO TERAPEUTA
    // =========================================================

    /** 1. Aspecto do cabelo e couro cabeludo observado pelo terapeuta */
    @Column(name = "aspecto_cabelo_couro_cabeludo", length = 1000)
    private String aspectoCabeloCouroCabeludo;

    /** 2. Tratamento indicado + número de aplicações */
    @Column(name = "tratamento_indicado", length = 1000)
    private String tratamentoIndicado;

    /** 3. Manutenção em casa indicada pelo terapeuta */
    @Column(name = "manutencao_em_casa", length = 1000)
    private String manutencaoEmCasa;

    /** Termo de Responsabilidade — assinado pelo paciente (data/hora do aceite digital) */
    @Column(name = "aceite_termo_responsabilidade")
    private Boolean aceiteTermoResponsabilidade = false;

    @Column(length = 500)
    private String observacoesGerais;


    // =========================================================
    // RESPOSTAS DINÂMICAS (Perguntas extras criadas pelo Admin)
    // =========================================================
    @OneToMany(mappedBy = "anamnese", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RespostaAnamnese> respostas = new ArrayList<>();
}
