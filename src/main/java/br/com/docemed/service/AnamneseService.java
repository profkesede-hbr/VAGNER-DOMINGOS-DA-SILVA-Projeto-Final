package br.com.docemed.service;

import br.com.docemed.dto.AnamneseRequestDTO;
import br.com.docemed.dto.AnamneseResponseDTO;
import br.com.docemed.exception.RecursoNaoEncontradoException;
import br.com.docemed.model.AlopeciaInfo;
import br.com.docemed.model.Anamnese;
import br.com.docemed.model.ExameLaboratorial;
import br.com.docemed.model.PerguntaAnamnese;
import br.com.docemed.model.RespostaAnamnese;
import br.com.docemed.model.TricoscopiaInfo;
import br.com.docemed.repository.AnamneseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AnamneseService {

    private final AnamneseRepository anamneseRepository;
    private final PerguntaAnamneseService perguntaService;

    @Transactional
    public AnamneseResponseDTO registrar(AnamneseRequestDTO dto) {
        if (dto.pacienteId() == null) {
            throw new IllegalArgumentException("O ID do paciente é obrigatório.");
        }

        Anamnese a = new Anamnese();
        a.setPacienteId(dto.pacienteId());
        a.setDataPreenchimento(LocalDateTime.now());
        a.setQueixaPrincipal(dto.queixaPrincipal());

        // Seção 1: Tricologia
        a.setTipoCabelo(dto.tipoCabelo());
        a.setPigmentacaoResidual(dto.pigmentacaoResidual());
        a.setCaracteristicaCouroCabeludo(dto.caracteristicaCouroCabeludo());

        // Seção 2: Alimentação
        a.setIngereFrutas(dto.ingereFrutas());
        a.setIngereLegumes(dto.ingereLegumes());
        a.setIngereVerduras(dto.ingereVerduras());
        a.setCoposAguaDiarios(dto.coposAguaDiarios());
        a.setConsomeAlimentosGordurosos(bool(dto.consomeAlimentosGordurosos()));
        a.setConsomeGluten(bool(dto.consomeGluten()));
        a.setConsomeLactose(bool(dto.consomeLactose()));
        a.setDescricaoHabitosAlimentares(dto.descricaoHabitosAlimentares());

        // Seção 3: Histórico de Saúde
        a.setProblemaCoracao(bool(dto.problemaCoracao()));
        a.setDetalheProblemaCoracao(dto.detalheProblemaCoracao());
        a.setDiabetesDescompensada(bool(dto.diabetesDescompensada()));
        a.setCancer(bool(dto.cancer()));
        a.setPossuiAlergia(bool(dto.possuiAlergia()));
        a.setDetalheAlergia(dto.detalheAlergia());
        a.setPressaoAltaDescompensada(bool(dto.pressaoAltaDescompensada()));
        a.setProblemasNeurologicos(bool(dto.problemasNeurologicos()));
        a.setDetalheNeurologico(dto.detalheNeurologico());
        a.setCirurgiaUltimos6Meses(bool(dto.cirurgiaUltimos6Meses()));
        a.setDetalheCirurgia(dto.detalheCirurgia());
        a.setEstresse(bool(dto.estresse()));
        a.setProblemasTireoide(bool(dto.problemasTireoide()));
        a.setDetalheTireoide(dto.detalheTireoide());
        a.setCovid19(bool(dto.covid19()));
        a.setProblemaRenalHepatico(bool(dto.problemaRenalHepatico()));
        a.setDetalheRenalHepatico(dto.detalheRenalHepatico());
        a.setGestante(bool(dto.gestante()));
        a.setLactante(bool(dto.lactante()));
        a.setOsteoporose(bool(dto.osteoporose()));
        a.setDepressao(bool(dto.depressao()));
        a.setAlcoolismo(bool(dto.alcoolismo()));
        a.setTabagismo(bool(dto.tabagismo()));
        a.setPossuiPinos(bool(dto.possuiPinos()));
        a.setLocalPinos(dto.localPinos());
        a.setProteseDentaria(bool(dto.proteseDentaria()));
        a.setMarcaPasso(bool(dto.marcaPasso()));
        a.setSindromePanico(bool(dto.sindromePanico()));
        a.setDoencaAutoimune(bool(dto.doencaAutoimune()));
        a.setDetalheDoencaAutoimune(dto.detalheDoencaAutoimune());
        a.setDengue(bool(dto.dengue()));
        a.setChikungunya(bool(dto.chikungunya()));
        a.setMaCirculacaoTrombose(bool(dto.maCirculacaoTrombose()));

        // Seção 4: Medicamentos, Ginecológico & Queixa Capilar
        a.setUsoMedicamentoContinuo(bool(dto.usoMedicamentoContinuo()));
        a.setDetalheMedicamentos(dto.detalheMedicamentos());
        a.setUsoAnticoncepcional(bool(dto.usoAnticoncepcional()));
        a.setDetalheAnticoncepcional(dto.detalheAnticoncepcional());
        a.setOvarioPolicistico(bool(dto.ovarioPolicistico()));
        a.setProblemaGinecologico(bool(dto.problemaGinecologico()));
        a.setDetalheGinecologico(dto.detalheGinecologico());
        a.setCicloMenstrual(dto.cicloMenstrual());
        a.setProblemaGastrointestinal(dto.problemaGastrointestinal());
        // Pergunta 32 — Escala de Bristol
        a.setVaiBanheiroRegularmente(bool(dto.vaiBanheiroRegularmente()));
        a.setTipoIntestinalBristol(dto.tipoIntestinalBristol());
        a.setTempoQuedaCapilar(dto.tempoQuedaCapilar());
        a.setQuedaAposPeriodoMarcante(bool(dto.quedaAposPeriodoMarcante()));
        a.setNascimentoNovosFios(bool(dto.nascimentoNovosFios()));
        a.setPerdaPelosCorpo(bool(dto.perdaPelosCorpo()));
        a.setPerdaDensidadeCabelo(bool(dto.perdaDensidadeCabelo()));

        // Seção 5: Aspecto do Cabelo & Química
        a.setQuimicaUltimos12Meses(bool(dto.quimicaUltimos12Meses()));
        a.setDetalhesQuimica(dto.detalhesQuimica());
        a.setCondicaoFio(dto.condicaoFio());

        // Seção 6: Dados Clínicos do Couro Cabeludo
        a.setImplanteCapilar(bool(dto.implanteCapilar()));
        a.setTesteTracao(dto.testeTracao());
        a.setQuedaNaFamilia(bool(dto.quedaNaFamilia()));
        a.setCaspaSeca(bool(dto.caspaSeca()));
        a.setCaspaUmida(bool(dto.caspaUmida()));
        a.setDermatiteSeborreica(bool(dto.dermatiteSeborreica()));
        a.setExposicaoSolarExcessiva(bool(dto.exposicaoSolarExcessiva()));
        a.setPsoriasie(bool(dto.psoriasie()));
        a.setFeridasInflamacao(bool(dto.feridasInflamacao()));
        a.setPediculose(bool(dto.pediculose()));
        a.setTricotilomania(bool(dto.tricotilomania()));
        a.setDorCouroCabeludo(bool(dto.dorCouroCabeludo()));
        a.setDorCouroCabeludoLocal(dto.dorCouroCabeludoLocal());
        a.setFoliculite(bool(dto.foliculite()));
        a.setFoliculiteLocal(dto.foliculiteLocal());
        a.setLesoesSinais(bool(dto.lesoesSinais()));
        a.setLesoesSinaisLocal(dto.lesoesSinaisLocal());
        a.setVerrugas(bool(dto.verrugas()));
        a.setVerrugasLocal(dto.verrugasLocal());
        a.setFalhaBarba(bool(dto.falhaBarba()));
        a.setArdencia(bool(dto.ardencia()));
        a.setPresencaCicatrizes(bool(dto.presencaCicatrizes()));
        a.setPresencaCicatrizesLocal(dto.presencaCicatrizesLocal());
        a.setVermelhidao(bool(dto.vermelhidao()));
        a.setRegioesRarefacao(dto.regioesRarefacao());

        // Seção 6.1: Tricoscopia (Haste / Couro Cabeludo)
        if (dto.tricoscopia() != null) {
            TricoscopiaInfo tr = new TricoscopiaInfo();
            tr.setDiversidadeDiametros(bool(dto.tricoscopia().diversidadeDiametros()));
            tr.setDiversidadeDiametrosLocal(dto.tricoscopia().diversidadeDiametrosLocal());
            tr.setFiosVellus(bool(dto.tricoscopia().fiosVellus()));
            tr.setFiosVellusLocal(dto.tricoscopia().fiosVellusLocal());
            tr.setDistanciaUnidadesFoliculares(dto.tricoscopia().distanciaUnidadesFoliculares());
            tr.setDistanciaUnidadesFolicularesLocal(dto.tricoscopia().distanciaUnidadesFolicularesLocal());
            tr.setFiosEmTufos(bool(dto.tricoscopia().fiosEmTufos()));
            tr.setFiosEmTufosLocal(dto.tricoscopia().fiosEmTufosLocal());
            tr.setPontosPretos(bool(dto.tricoscopia().pontosPretos()));
            tr.setPontosPretosLocal(dto.tricoscopia().pontosPretosLocal());
            tr.setPontosAmarelos(bool(dto.tricoscopia().pontosAmarelos()));
            tr.setPontosAmarelosLocal(dto.tricoscopia().pontosAmarelosLocal());
            tr.setPontosBrancos(dto.tricoscopia().pontosBrancos());
            tr.setPontosBrancosLocal(dto.tricoscopia().pontosBrancosLocal());
            tr.setPontosVermelhos(bool(dto.tricoscopia().pontosVermelhos()));
            tr.setPontosVermelhosLocal(dto.tricoscopia().pontosVermelhosLocal());
            tr.setSinalPeripilar(bool(dto.tricoscopia().sinalPeripilar()));
            tr.setSinalPeripilarLocal(dto.tricoscopia().sinalPeripilarLocal());
            tr.setRegiaoSemFoliculos(bool(dto.tricoscopia().regiaoSemFoliculos()));
            tr.setRegiaoSemFoliculosLocal(dto.tricoscopia().regiaoSemFoliculosLocal());
            tr.setVasosArborizantes(bool(dto.tricoscopia().vasosArborizantes()));
            tr.setVasosArborizantesLocal(dto.tricoscopia().vasosArborizantesLocal());
            tr.setDescamacao(bool(dto.tricoscopia().descamacao()));
            tr.setDescamacaoLocal(dto.tricoscopia().descamacaoLocal());
            a.setTricoscopia(tr);
        }

        // Seção 7: Hábitos & Cuidados
        a.setLavaCabeloTodosDias(bool(dto.lavaCabeloTodosDias()));
        a.setDiasLavagemSemana(dto.diasLavagemSemana());
        a.setUsaSecadorPrancha(bool(dto.usaSecadorPrancha()));
        a.setFrequenciaSecadorPrancha(dto.frequenciaSecadorPrancha());
        a.setUsaProtetorTermico(bool(dto.usaProtetorTermico()));
        a.setUsaCabeloPreso(bool(dto.usaCabeloPreso()));
        a.setPraticaAtividadeFisica(bool(dto.praticaAtividadeFisica()));
        a.setFazendoDieta(bool(dto.fazendoDieta()));
        a.setUsaGel(bool(dto.usaGel()));
        a.setUsaBone(bool(dto.usaBone()));
        a.setUsaCapacete(bool(dto.usaCapacete()));
        a.setTratamentosCapilaresAnteriores(dto.tratamentosCapilaresAnteriores());

        // Seção 8: Histórico Familiar & Alopecias
        a.setCalvicieFamilia(bool(dto.calvicieFamilia()));
        a.setAlopeciaAreataFamilia(bool(dto.alopeciaAreataFamilia()));

        // Seção 9: Alopecias Não Cicatriciais
        if (dto.alopecias() != null) {
            AlopeciaInfo al = new AlopeciaInfo();
            al.setAlopeciaAndrogeneticaPresente(bool(dto.alopecias().androgeneticaPresente()));
            al.setAlopeciaAndrogeneticaClassificacao(dto.alopecias().androgeneticaClassificacao());
            al.setAlopeciaAndrogeneticaScanPath(dto.alopecias().androgeneticaScanPath());
            al.setAlopeciaAreataPresente(bool(dto.alopecias().areataPresente()));
            al.setAlopeciaAreataClassificacao(dto.alopecias().areataClassificacao());
            al.setAlopeciaAreataScanPath(dto.alopecias().areataScanPath());
            // Eflúvio Telógeno
            al.setEfluvioTelogeno(bool(dto.alopecias().efluvioTelogeno()));
            al.setEtPuerperio(bool(dto.alopecias().etPuerperio()));
            al.setEtEmagrecimento(bool(dto.alopecias().etEmagrecimento()));
            al.setEtDeficienciaNutricional(bool(dto.alopecias().etDeficienciaNutricional()));
            al.setEtEstresseProlongado(bool(dto.alopecias().etEstresseProlongado()));
            al.setEtDoencasSistemica(bool(dto.alopecias().etDoencasSistemica()));
            al.setEtMedicamentos(bool(dto.alopecias().etMedicamentos()));
            al.setEtOutros(dto.alopecias().etOutros());
            // Eflúvio Anágeno
            al.setEfluvioAnageno(bool(dto.alopecias().efluvioAnageno()));
            al.setEaQuimioterapia(bool(dto.alopecias().eaQuimioterapia()));
            al.setEaInfeccoes(bool(dto.alopecias().eaInfeccoes()));
            al.setEaToxicos(bool(dto.alopecias().eaToxicos()));
            al.setEaRadiacao(bool(dto.alopecias().eaRadiacao()));
            al.setEaDoencasAutoimunes(bool(dto.alopecias().eaDoencasAutoimunes()));
            // Alopecia Cicatricial
            al.setAlopecia_cicatricial(bool(dto.alopecias().alopecia_cicatricial()));
            al.setAcFoliculiteQueloidiana(bool(dto.alopecias().acFoliculiteQueloidiana()));
            al.setAcFoliculiteDecalvante(bool(dto.alopecias().acFoliculiteDecalvante()));
            al.setAcFibrosanteFrontal(bool(dto.alopecias().acFibrosanteFrontal()));
            al.setAcLiquenPlanoPilar(bool(dto.alopecias().acLiquenPlanoPilar()));
            al.setAcLupusDiscoide(bool(dto.alopecias().acLupusDiscoide()));
            al.setOutrasAlopecias(dto.alopecias().outrasAlopecias());
            a.setAlopecias(al);
        }

        // Seção 9: Exames Laboratoriais
        if (dto.exames() != null) {
            ExameLaboratorial ex = new ExameLaboratorial();
            ex.setPossuiExames(bool(dto.exames().possuiExames()));
            ex.setHemograma(dto.exames().hemograma());
            ex.setAcidoFolico(dto.exames().acidoFolico());
            ex.setFerritina(dto.exames().ferritina());
            ex.setVitaminaD(dto.exames().vitaminaD());
            ex.setBiotina(dto.exames().biotina());
            ex.setVitB12(dto.exames().vitB12());
            ex.setVitB6(dto.exames().vitB6());
            ex.setZinco(dto.exames().zinco());
            ex.setCobre(dto.exames().cobre());
            ex.setPcr(dto.exames().pcr());
            ex.setVhs(dto.exames().vhs());
            ex.setFan(dto.exames().fan());
            ex.setVitA(dto.exames().vitA());
            ex.setVitC(dto.exames().vitC());
            ex.setSelenio(dto.exames().selenio());
            ex.setDht(dto.exames().dht());
            ex.setTsh(dto.exames().tsh());
            ex.setT4Livre(dto.exames().t4Livre());
            ex.setAntiTpo(dto.exames().antiTpo());
            ex.setVitE(dto.exames().vitE());
            ex.setTestosterona(dto.exames().testosterona());
            ex.setOutrosExames(dto.exames().outrosExames());
            a.setExames(ex);
        }

        // Seção 10: Indicação do Terapeuta
        a.setAspectoCabeloCouroCabeludo(dto.aspectoCabeloCouroCabeludo());
        a.setTratamentoIndicado(dto.tratamentoIndicado());
        a.setManutencaoEmCasa(dto.manutencaoEmCasa());
        a.setAceiteTermoResponsabilidade(bool(dto.aceiteTermoResponsabilidade()));
        a.setObservacoesGerais(dto.observacoesGerais());

        // Respostas dinâmicas extras
        List<RespostaAnamnese> respostas = new ArrayList<>();
        if (dto.respostasDinamicas() != null) {
            for (Map.Entry<Long, String> entry : dto.respostasDinamicas().entrySet()) {
                PerguntaAnamnese pergunta = perguntaService.buscarEntidade(entry.getKey());
                RespostaAnamnese resposta = new RespostaAnamnese();
                resposta.setAnamnese(a);
                resposta.setPergunta(pergunta);
                resposta.setResposta(entry.getValue());
                respostas.add(resposta);
            }
        }
        a.setRespostas(respostas);

        Anamnese salva = anamneseRepository.save(a);
        return toDTO(salva);
    }

    public AnamneseResponseDTO buscarPorId(Long id) {
        Anamnese a = anamneseRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Anamnese não encontrada. ID: " + id));
        return toDTO(a);
    }

    public List<AnamneseResponseDTO> listarPorPaciente(Long pacienteId) {
        return anamneseRepository
                .findAllByPacienteIdOrderByDataPreenchimentoDesc(pacienteId)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public AnamneseResponseDTO buscarMaisRecentePorPaciente(Long pacienteId) {
        Anamnese a = anamneseRepository
                .findTopByPacienteIdOrderByDataPreenchimentoDesc(pacienteId)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Nenhuma anamnese encontrada para o paciente ID: " + pacienteId));
        return toDTO(a);
    }

    @Transactional
    public void excluir(Long id) {
        if (!anamneseRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Anamnese não encontrada. ID: " + id);
        }
        anamneseRepository.deleteById(id);
    }

    private Boolean bool(Boolean valor) {
        return valor != null ? valor : false;
    }

    private AnamneseResponseDTO toDTO(Anamnese a) {
        List<AnamneseResponseDTO.RespostaAnamneseDTO> respostasDTOs = a.getRespostas()
                .stream()
                .map(r -> new AnamneseResponseDTO.RespostaAnamneseDTO(
                        r.getPergunta().getId(),
                        r.getPergunta().getEnunciado(),
                        r.getResposta()
                ))
                .toList();

        return new AnamneseResponseDTO(
                a.getId(),
                a.getPacienteId(),
                a.getDataPreenchimento(),
                a.getQueixaPrincipal(),
                a.getTipoCabelo(),
                a.getPigmentacaoResidual(),
                a.getCaracteristicaCouroCabeludo(),
                a.getIngereFrutas(),
                a.getIngereLegumes(),
                a.getIngereVerduras(),
                a.getCoposAguaDiarios(),
                a.getConsomeAlimentosGordurosos(),
                a.getConsomeGluten(),
                a.getConsomeLactose(),
                a.getDescricaoHabitosAlimentares(),
                a.getProblemaCoracao(),
                a.getDetalheProblemaCoracao(),
                a.getDiabetesDescompensada(),
                a.getCancer(),
                a.getPossuiAlergia(),
                a.getDetalheAlergia(),
                a.getPressaoAltaDescompensada(),
                a.getProblemasNeurologicos(),
                a.getDetalheNeurologico(),
                a.getCirurgiaUltimos6Meses(),
                a.getDetalheCirurgia(),
                a.getEstresse(),
                a.getProblemasTireoide(),
                a.getDetalheTireoide(),
                a.getCovid19(),
                a.getProblemaRenalHepatico(),
                a.getDetalheRenalHepatico(),
                a.getGestante(),
                a.getLactante(),
                a.getOsteoporose(),
                a.getDepressao(),
                a.getAlcoolismo(),
                a.getTabagismo(),
                a.getPossuiPinos(),
                a.getLocalPinos(),
                a.getProteseDentaria(),
                a.getMarcaPasso(),
                a.getSindromePanico(),
                a.getDoencaAutoimune(),
                a.getDetalheDoencaAutoimune(),
                a.getDengue(),
                a.getChikungunya(),
                a.getMaCirculacaoTrombose(),
                a.getUsoMedicamentoContinuo(),
                a.getDetalheMedicamentos(),
                a.getUsoAnticoncepcional(),
                a.getDetalheAnticoncepcional(),
                a.getOvarioPolicistico(),
                a.getProblemaGinecologico(),
                a.getDetalheGinecologico(),
                a.getCicloMenstrual(),
                a.getProblemaGastrointestinal(),
                a.getVaiBanheiroRegularmente(),
                a.getTipoIntestinalBristol(),
                a.getTempoQuedaCapilar(),
                a.getQuedaAposPeriodoMarcante(),
                a.getNascimentoNovosFios(),
                a.getPerdaPelosCorpo(),
                a.getPerdaDensidadeCabelo(),
                a.getQuimicaUltimos12Meses(),
                a.getDetalhesQuimica(),
                a.getCondicaoFio(),
                a.getImplanteCapilar(),
                a.getTesteTracao(),
                a.getQuedaNaFamilia(),
                a.getCaspaSeca(),
                a.getCaspaUmida(),
                a.getDermatiteSeborreica(),
                a.getExposicaoSolarExcessiva(),
                a.getPsoriasie(),
                a.getFeridasInflamacao(),
                a.getPediculose(),
                a.getTricotilomania(),
                a.getDorCouroCabeludo(),
                a.getDorCouroCabeludoLocal(),
                a.getFoliculite(),
                a.getFoliculiteLocal(),
                a.getLesoesSinais(),
                a.getLesoesSinaisLocal(),
                a.getVerrugas(),
                a.getVerrugasLocal(),
                a.getFalhaBarba(),
                a.getArdencia(),
                a.getPresencaCicatrizes(),
                a.getPresencaCicatrizesLocal(),
                a.getVermelhidao(),
                a.getRegioesRarefacao(),
                // Tricoscopia
                a.getTricoscopia() != null ? new AnamneseResponseDTO.TricoscopiaDTO(
                        a.getTricoscopia().getDiversidadeDiametros(),
                        a.getTricoscopia().getDiversidadeDiametrosLocal(),
                        a.getTricoscopia().getFiosVellus(),
                        a.getTricoscopia().getFiosVellusLocal(),
                        a.getTricoscopia().getDistanciaUnidadesFoliculares(),
                        a.getTricoscopia().getDistanciaUnidadesFolicularesLocal(),
                        a.getTricoscopia().getFiosEmTufos(),
                        a.getTricoscopia().getFiosEmTufosLocal(),
                        a.getTricoscopia().getPontosPretos(),
                        a.getTricoscopia().getPontosPretosLocal(),
                        a.getTricoscopia().getPontosAmarelos(),
                        a.getTricoscopia().getPontosAmarelosLocal(),
                        a.getTricoscopia().getPontosBrancos(),
                        a.getTricoscopia().getPontosBrancosLocal(),
                        a.getTricoscopia().getPontosVermelhos(),
                        a.getTricoscopia().getPontosVermelhosLocal(),
                        a.getTricoscopia().getSinalPeripilar(),
                        a.getTricoscopia().getSinalPeripilarLocal(),
                        a.getTricoscopia().getRegiaoSemFoliculos(),
                        a.getTricoscopia().getRegiaoSemFoliculosLocal(),
                        a.getTricoscopia().getVasosArborizantes(),
                        a.getTricoscopia().getVasosArborizantesLocal(),
                        a.getTricoscopia().getDescamacao(),
                        a.getTricoscopia().getDescamacaoLocal()
                ) : null,
                a.getLavaCabeloTodosDias(),
                a.getDiasLavagemSemana(),
                a.getUsaSecadorPrancha(),
                a.getFrequenciaSecadorPrancha(),
                a.getUsaProtetorTermico(),
                a.getUsaCabeloPreso(),
                a.getPraticaAtividadeFisica(),
                a.getFazendoDieta(),
                a.getUsaGel(),
                a.getUsaBone(),
                a.getUsaCapacete(),
                a.getTratamentosCapilaresAnteriores(),
                a.getCalvicieFamilia(),
                a.getAlopeciaAreataFamilia(),
                // Alopecias Não Cicatriciais
                a.getAlopecias() != null ? new AnamneseResponseDTO.AlopeciaDTO(
                        a.getAlopecias().getAlopeciaAndrogeneticaPresente(),
                        a.getAlopecias().getAlopeciaAndrogeneticaClassificacao(),
                        a.getAlopecias().getAlopeciaAndrogeneticaScanPath(),
                        a.getAlopecias().getAlopeciaAreataPresente(),
                        a.getAlopecias().getAlopeciaAreataClassificacao(),
                        a.getAlopecias().getAlopeciaAreataScanPath(),
                        // Eflúvio Telógeno
                        a.getAlopecias().getEfluvioTelogeno(),
                        a.getAlopecias().getEtPuerperio(),
                        a.getAlopecias().getEtEmagrecimento(),
                        a.getAlopecias().getEtDeficienciaNutricional(),
                        a.getAlopecias().getEtEstresseProlongado(),
                        a.getAlopecias().getEtDoencasSistemica(),
                        a.getAlopecias().getEtMedicamentos(),
                        a.getAlopecias().getEtOutros(),
                        // Eflúvio Anágeno
                        a.getAlopecias().getEfluvioAnageno(),
                        a.getAlopecias().getEaQuimioterapia(),
                        a.getAlopecias().getEaInfeccoes(),
                        a.getAlopecias().getEaToxicos(),
                        a.getAlopecias().getEaRadiacao(),
                        a.getAlopecias().getEaDoencasAutoimunes(),
                        // Alopecia Cicatricial
                        a.getAlopecias().getAlopecia_cicatricial(),
                        a.getAlopecias().getAcFoliculiteQueloidiana(),
                        a.getAlopecias().getAcFoliculiteDecalvante(),
                        a.getAlopecias().getAcFibrosanteFrontal(),
                        a.getAlopecias().getAcLiquenPlanoPilar(),
                        a.getAlopecias().getAcLupusDiscoide(),
                        a.getAlopecias().getOutrasAlopecias()
                ) : null,
                // Exames laboratoriais como objeto aninhado
                a.getExames() != null ? new AnamneseResponseDTO.ExamesDTO(
                        a.getExames().getPossuiExames(),
                        a.getExames().getHemograma(),     a.getExames().getAcidoFolico(),  a.getExames().getFerritina(),
                        a.getExames().getVitaminaD(),     a.getExames().getBiotina(),      a.getExames().getVitB12(),
                        a.getExames().getVitB6(),         a.getExames().getZinco(),        a.getExames().getCobre(),
                        a.getExames().getPcr(),           a.getExames().getVhs(),          a.getExames().getFan(),
                        a.getExames().getVitA(),          a.getExames().getVitC(),         a.getExames().getSelenio(),
                        a.getExames().getDht(),           a.getExames().getTsh(),          a.getExames().getT4Livre(),
                        a.getExames().getAntiTpo(),       a.getExames().getVitE(),         a.getExames().getTestosterona(),
                        a.getExames().getOutrosExames()
                ) : null,
                a.getAspectoCabeloCouroCabeludo(),
                a.getTratamentoIndicado(),
                a.getManutencaoEmCasa(),
                a.getAceiteTermoResponsabilidade(),
                a.getObservacoesGerais(),
                respostasDTOs
        );
    }
}
