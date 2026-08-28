package br.com.docemed.config;

import br.com.docemed.model.PerguntaAnamnese;
import br.com.docemed.repository.PerguntaAnamneseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Carga inicial COMPLETA com todas as perguntas e seções da Ficha de Avaliação Capilar (Doc-eMed).
 * Cobre 100% das páginas 1 a 8 do PDF (SPA Brasil Cursos).
 */
@Configuration
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final PerguntaAnamneseRepository perguntaRepository;
    private final br.com.docemed.repository.UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) {
        // ─── USUÁRIOS DEFAULT (MÉDICO / ADMIN) ──────────────────────────────
        if (!usuarioRepository.existsByLogin("admin")) {
            usuarioRepository.save(br.com.docemed.model.Usuario.builder()
                    .login("admin")
                    .senha("admin123")
                    .nome("Dr. Vagner Domingos — Tricologista Responsável")
                    .perfil(br.com.docemed.model.PerfilUsuario.MEDICO)
                    .telefoneWhatsapp("(11) 98888-7777")
                    .ativo(true)
                    .build());
        }
        if (!usuarioRepository.existsByLogin("medico")) {
            usuarioRepository.save(br.com.docemed.model.Usuario.builder()
                    .login("medico")
                    .senha("medico123")
                    .nome("Dra. Equipe Clínica Doc-eMed")
                    .perfil(br.com.docemed.model.PerfilUsuario.MEDICO)
                    .telefoneWhatsapp("(11) 99999-8888")
                    .ativo(true)
                    .build());
        }
        if (!usuarioRepository.existsByLogin("recep")) {
            usuarioRepository.save(br.com.docemed.model.Usuario.builder()
                    .login("recep")
                    .senha("recep123")
                    .nome("Atendimento & Recepção Geral")
                    .perfil(br.com.docemed.model.PerfilUsuario.RECEPCAO)
                    .telefoneWhatsapp("(11) 3333-5555")
                    .ativo(true)
                    .build());
        }

        if (perguntaRepository.count() < 130) {
            perguntaRepository.deleteAll();

            List<PerguntaAnamnese> perguntas = new ArrayList<>();
            int ordem = 1;

            // ─── 1. TRICOLOGIA & DADOS INICIAIS ───────────────────────────────────
            perguntas.add(criar("Tipo de cabelo: [Liso, Ondulado, Cacheado, Crespo]", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("Pigmentação residual: [Pigmentado, Difuso, Semi pigmentado]", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("Característica do couro cabeludo: [Normal, Seco, Oleoso, Misto]", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("Queixa principal do cliente", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));

            // ─── 2. ALIMENTAÇÃO ───────────────────────────────────────────────────
            perguntas.add(criar("1. Ingere frutas? [Sim, Não, Pouco]", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("2. Ingere legumes? [Sim, Não, Pouco]", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("3. Ingere verduras? [Sim, Não, Pouco]", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("4. Quantos copos de água / líquidos ingere diariamente?", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("5. Consome alimentos gordurosos, com glúten ou lactose?", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("Como é sua alimentação no dia a dia? (Descreva seus hábitos, o que come e os horários que come)", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));

            // ─── 3. HISTÓRICO DE SAÚDE ───────────────────────────────────────────
            perguntas.add(criar("1. Problemas no coração: [Sim, Não] Qual?", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("2. Diabete descompensada: [Sim, Não]", PerguntaAnamnese.TipoResposta.SIM_NAO, ordem++));
            perguntas.add(criar("3. Câncer: [Sim, Não]", PerguntaAnamnese.TipoResposta.SIM_NAO, ordem++));
            perguntas.add(criar("4. Alergia: [Sim, Não] Qual?", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("5. Pressão alta descompensada: [Sim, Não]", PerguntaAnamnese.TipoResposta.SIM_NAO, ordem++));
            perguntas.add(criar("6. Neurológicos: [Sim, Não] Qual?", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("7. Cirurgia nos últimos 6 meses: [Sim, Não] Qual?", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("8. Estresse: [Sim, Não]", PerguntaAnamnese.TipoResposta.SIM_NAO, ordem++));
            perguntas.add(criar("9. Problemas na tireoide: [Sim, Não] Qual?", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("10. Covid-19: [Sim, Não]", PerguntaAnamnese.TipoResposta.SIM_NAO, ordem++));
            perguntas.add(criar("11. Renal grave/Hepático: [Sim, Não] Qual?", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("12. Gestante: [Sim, Não]", PerguntaAnamnese.TipoResposta.SIM_NAO, ordem++));
            perguntas.add(criar("13. Lactante: [Sim, Não]", PerguntaAnamnese.TipoResposta.SIM_NAO, ordem++));
            perguntas.add(criar("14. Osteoporose: [Sim, Não]", PerguntaAnamnese.TipoResposta.SIM_NAO, ordem++));
            perguntas.add(criar("15. Depressão: [Sim, Não]", PerguntaAnamnese.TipoResposta.SIM_NAO, ordem++));
            perguntas.add(criar("16. Alcoolismo: [Sim, Não]", PerguntaAnamnese.TipoResposta.SIM_NAO, ordem++));
            perguntas.add(criar("17. Tabagismo: [Sim, Não]", PerguntaAnamnese.TipoResposta.SIM_NAO, ordem++));
            perguntas.add(criar("18. Pinos: [Sim, Não] Local:", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("19. Prótese dentária: [Sim, Não]", PerguntaAnamnese.TipoResposta.SIM_NAO, ordem++));
            perguntas.add(criar("20. Marca-passo: [Sim, Não]", PerguntaAnamnese.TipoResposta.SIM_NAO, ordem++));
            perguntas.add(criar("21. Síndrome do pânico: [Sim, Não]", PerguntaAnamnese.TipoResposta.SIM_NAO, ordem++));
            perguntas.add(criar("22. Outro problema de saúde / doença autoimune: [Sim, Não] Qual?", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("23. Dengue: [Sim, Não]", PerguntaAnamnese.TipoResposta.SIM_NAO, ordem++));
            perguntas.add(criar("24. Chikungunya: [Sim, Não]", PerguntaAnamnese.TipoResposta.SIM_NAO, ordem++));
            perguntas.add(criar("25. Circulação? (Má circulação, trombose, embolia) [Sim, Não]", PerguntaAnamnese.TipoResposta.SIM_NAO, ordem++));

            // ─── 4. MEDICAMENTOS & HISTÓRICO GINECOLÓGICO ────────────────────────
            perguntas.add(criar("26. Está fazendo uso de algum medicamento no momento ou de uso contínuo? [Sim, Não] Qual?", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("27. Faz uso de anticoncepcional? [Sim, Não] Qual?", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("28. Síndrome do ovário policístico: [Sim, Não]", PerguntaAnamnese.TipoResposta.SIM_NAO, ordem++));
            perguntas.add(criar("29. Problema Ginecológico: [Sim, Não] Qual?", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("30. Menstruação: [Regular, Irregular, Fluxo normal, Fluxo intenso]", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("31. Problema gastrointestinal? [Gastrite, Úlceras, Queimação, Má digestão]", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("32. Vai ao banheiro regularmente? Como funciona o seu intestino (Escala de Bristol Tipo 1 a 7)?", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));

            // ─── 5. HISTÓRICO DA QUEDA CAPILAR ───────────────────────────────────
            perguntas.add(criar("33. Queda capilar acentuada? Quanto tempo? Quando começou?", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("34. Essa queda aconteceu após um período marcante para você? [Sim, Não]", PerguntaAnamnese.TipoResposta.SIM_NAO, ordem++));
            perguntas.add(criar("35. Onde os fios caem, você nota nascimento de outro fio no local? [Sim, Não]", PerguntaAnamnese.TipoResposta.SIM_NAO, ordem++));
            perguntas.add(criar("36. Notou perda de pelos em alguma parte do corpo? [Sim, Não]", PerguntaAnamnese.TipoResposta.SIM_NAO, ordem++));
            perguntas.add(criar("37. Notou perda de densidade do cabelo? [Sim, Não]", PerguntaAnamnese.TipoResposta.SIM_NAO, ordem++));

            // ─── 6. ASPECTO DO CABELO & QUÍMICA ──────────────────────────────────
            perguntas.add(criar("Química nos últimos 12 meses? [Sim, Não] (Permanente, Alisamento, Progressivas, Coloração, Reflexo/Luzes, Tonalizantes, Outros)", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("Condição das pontas e nós: [Tricoptilose (pontas duplas), Triconodose (nós no fio)]", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("Condição da estrutura dos fios: [Normal, Íntegro, Elástico, Frizz, Quebradiço, Poroso, Desidratado, Piedra Branca]", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("Alterações gerais dos fios: [Alterações na cor, Afinamento do cabelo, Aparecimento precoce de fios brancos]", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));

            // ─── 7. DADOS CLÍNICOS DO COURO CABELUDO ─────────────────────────────
            perguntas.add(criar("1. Implante/transplante de cabelo? [Sim, Não] Quando?", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("2. Teste de tração: [Positivo, Negativo] (*Positivo quando desprendimento > 10% dos fios)", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("3. Queda na família? [Sim, Não]", PerguntaAnamnese.TipoResposta.SIM_NAO, ordem++));
            perguntas.add(criar("4. Caspa seca? [Sim, Não] Local:", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("5. Caspa úmida? [Sim, Não] Local:", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("6. Dermatite Seborreica? [Sim, Não] Local:", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("7. Expõe-se muito ao sol? [Sim, Não]", PerguntaAnamnese.TipoResposta.SIM_NAO, ordem++));
            perguntas.add(criar("8. Psoríase? [Sim, Não] Local:", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("9. Feridas/inflamação? [Sim, Não] Local:", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("10. Pediculose? [Sim, Não]", PerguntaAnamnese.TipoResposta.SIM_NAO, ordem++));
            perguntas.add(criar("11. Tricotilomania? [Sim, Não]", PerguntaAnamnese.TipoResposta.SIM_NAO, ordem++));
            perguntas.add(criar("12. Dor no couro cabeludo? [Sim, Não] Local:", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("13. Presença de foliculite? [Sim, Não] Local:", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("14. Alguma lesão (sinais/pintas) no couro cabeludo? [Sim, Não] Local:", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("15. Alguma verruga no couro cabeludo? [Sim, Não] Local:", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("16. Falha na barba? [Sim, Não]", PerguntaAnamnese.TipoResposta.SIM_NAO, ordem++));
            perguntas.add(criar("17. Ardência no couro cabeludo? [Sim, Não]", PerguntaAnamnese.TipoResposta.SIM_NAO, ordem++));
            perguntas.add(criar("18. Presença de cicatrizes? [Sim, Não] Local:", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("19. Vermelhidão no couro cabeludo? [Sim, Não]", PerguntaAnamnese.TipoResposta.SIM_NAO, ordem++));
            perguntas.add(criar("Regiões com falhas (rarefação): [Difusa, Entrada, Coroa da cabeça, Risca central, Nuca, Franja]", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));

            // ─── 8. HÁBITOS (ESTILO DE VIDA) ──────────────────────────────────────
            perguntas.add(criar("Lava o cabelo todos os dias? [Sim, Não] Quantos dias na semana?", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("Usa prancha ou secador? [Sim, Não] Frequência?", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("Usa protetor térmico (leave-in)? [Sim, Não]", PerguntaAnamnese.TipoResposta.SIM_NAO, ordem++));
            perguntas.add(criar("Usa cabelo preso (rabo de cavalo)? [Sim, Não] Frequência?", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("Pratica atividades físicas? [Sim, Não]", PerguntaAnamnese.TipoResposta.SIM_NAO, ordem++));
            perguntas.add(criar("Está fazendo alguma dieta? [Sim, Não]", PerguntaAnamnese.TipoResposta.SIM_NAO, ordem++));
            perguntas.add(criar("Usa gel de cabelo? [Sim, Não]", PerguntaAnamnese.TipoResposta.SIM_NAO, ordem++));
            perguntas.add(criar("Usa boné? [Sim, Não] Frequência?", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("Usa capacete? [Sim, Não]", PerguntaAnamnese.TipoResposta.SIM_NAO, ordem++));
            perguntas.add(criar("Já realizou algum tratamento Capilar? O que utilizou (procedimentos, medicamentos)?", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));

            // ─── 9. HISTÓRICO NA FAMÍLIA ──────────────────────────────────────────
            perguntas.add(criar("Calvície na família? [Sim, Não]", PerguntaAnamnese.TipoResposta.SIM_NAO, ordem++));
            perguntas.add(criar("Casos de Alopecia Areata na família? [Sim, Não]", PerguntaAnamnese.TipoResposta.SIM_NAO, ordem++));

            // ─── 10. HASTE / COURO CABELUDO (TRICOSCOPIA) ─────────────────────────
            perguntas.add(criar("Tricoscopia - Diversidade de diâmetros: [Sim, Não] Local:", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("Tricoscopia - Fios vellus: [Sim, Não] Local:", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("Tricoscopia - Distância entre unidades foliculares: [Normal, Alterado] Local:", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("Tricoscopia - Fios em tufos: [Sim, Não] Local:", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("Tricoscopia - Pontos pretos: [Sim, Não] Local:", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("Tricoscopia - Pontos amarelos: [Sim, Não] Local:", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("Tricoscopia - Pontos brancos: [Regulares, Irregulares] Local:", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("Tricoscopia - Pontos Vermelhos: [Sim, Não] Local:", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("Tricoscopia - Sinal peripilar: [Sim, Não] Local:", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("Tricoscopia - Região aparente sem presença de folículos: [Sim, Não] Local:", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("Tricoscopia - Vasos arborizantes: [Sim, Não] Local:", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("Tricoscopia - Descamação: [Sim, Não] Local:", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));

            // ─── 11. EXAMES LABORATORIAIS ─────────────────────────────────────────
            perguntas.add(criar("Possui exames laboratoriais? [Sim, Não]", PerguntaAnamnese.TipoResposta.SIM_NAO, ordem++));
            perguntas.add(criar("Exame: Hemograma (Resultado)", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("Exame: Ácido fólico (Resultado)", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("Exame: Ferritina (Resultado)", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("Exame: Vitamina D (Resultado)", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("Exame: Biotina (Resultado)", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("Exame: Vitamina B12 (Resultado)", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("Exame: Vitamina B6 (Resultado)", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("Exame: Zinco (Resultado)", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("Exame: Cobre (Resultado)", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("Exame: PCR (Resultado)", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("Exame: VHS (Resultado)", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("Exame: FAN (Resultado)", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("Exame: Vitamina A (Resultado)", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("Exame: Vitamina C (Resultado)", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("Exame: Selênio (Resultado)", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("Exame: DHT (Resultado)", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("Exame: TSH (Resultado)", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("Exame: T4 Livre (Resultado)", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("Exame: Anti-TPO (Resultado)", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("Exame: Vitamina E (Resultado)", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("Exame: Testosterona (Resultado)", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("Outros exames laboratoriais realizados", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));

            // ─── 12. ALOPECIAS NÃO CICATRICIAIS ───────────────────────────────────
            perguntas.add(criar("Alopecia Androgenética presente? Classificação / Grau (Hamilton ou Ludwig):", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("Alopecia Areata presente? Padrão (Placas, Ofiásica, Difusa, Totalis, Universalis):", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("Eflúvio Telógeno presente? [Sim, Não]", PerguntaAnamnese.TipoResposta.SIM_NAO, ordem++));
            perguntas.add(criar("Eflúvio Telógeno - Causa: Puerpério (pós-parto)? [Sim, Não]", PerguntaAnamnese.TipoResposta.SIM_NAO, ordem++));
            perguntas.add(criar("Eflúvio Telógeno - Causa: Emagrecimento? [Sim, Não]", PerguntaAnamnese.TipoResposta.SIM_NAO, ordem++));
            perguntas.add(criar("Eflúvio Telógeno - Causa: Deficiência de ferro, zinco, vit D ou vit B12? [Sim, Não]", PerguntaAnamnese.TipoResposta.SIM_NAO, ordem++));
            perguntas.add(criar("Eflúvio Telógeno - Causa: Estados estressantes prolongados? [Sim, Não]", PerguntaAnamnese.TipoResposta.SIM_NAO, ordem++));
            perguntas.add(criar("Eflúvio Telógeno - Causa: Doenças sistêmicas (lúpus, anemia, tireoide, hepatites, diabetes)? [Sim, Não]", PerguntaAnamnese.TipoResposta.SIM_NAO, ordem++));
            perguntas.add(criar("Eflúvio Telógeno - Causa: Uso de medicamentos (contraceptivos, amiodarona, anticoagulantes, estatinas)? [Sim, Não]", PerguntaAnamnese.TipoResposta.SIM_NAO, ordem++));
            perguntas.add(criar("Eflúvio Telógeno - Outras causas identificadas:", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("Eflúvio Anágeno presente? [Sim, Não]", PerguntaAnamnese.TipoResposta.SIM_NAO, ordem++));
            perguntas.add(criar("Eflúvio Anágeno - Causa: Quimioterapia? [Sim, Não]", PerguntaAnamnese.TipoResposta.SIM_NAO, ordem++));
            perguntas.add(criar("Eflúvio Anágeno - Causa: Infecções (Sífilis ou Kerion)? [Sim, Não]", PerguntaAnamnese.TipoResposta.SIM_NAO, ordem++));
            perguntas.add(criar("Eflúvio Anágeno - Causa: Tóxicos (Tálio, Arsênio, Cádmio, Mercúrio, Bismuto)? [Sim, Não]", PerguntaAnamnese.TipoResposta.SIM_NAO, ordem++));
            perguntas.add(criar("Eflúvio Anágeno - Causa: Radiação ou Radioterapia? [Sim, Não]", PerguntaAnamnese.TipoResposta.SIM_NAO, ordem++));
            perguntas.add(criar("Eflúvio Anágeno - Causa: Doenças autoimunes (Alopecia Areata Incognita, Lúpus, Pênfigo Vulgar)? [Sim, Não]", PerguntaAnamnese.TipoResposta.SIM_NAO, ordem++));

            // ─── 13. ALOPECIA CICATRICIAL ─────────────────────────────────────────
            perguntas.add(criar("Alopecia Cicatricial presente? [Sim, Não]", PerguntaAnamnese.TipoResposta.SIM_NAO, ordem++));
            perguntas.add(criar("Alopecia Cicatricial - Subtipo: Foliculite Queloidiana? [Sim, Não]", PerguntaAnamnese.TipoResposta.SIM_NAO, ordem++));
            perguntas.add(criar("Alopecia Cicatricial - Subtipo: Foliculite Decalvante? [Sim, Não]", PerguntaAnamnese.TipoResposta.SIM_NAO, ordem++));
            perguntas.add(criar("Alopecia Cicatricial - Subtipo: Alopecia Fibrosante Frontal? [Sim, Não]", PerguntaAnamnese.TipoResposta.SIM_NAO, ordem++));
            perguntas.add(criar("Alopecia Cicatricial - Subtipo: Líquen Plano Pilar? [Sim, Não]", PerguntaAnamnese.TipoResposta.SIM_NAO, ordem++));
            perguntas.add(criar("Alopecia Cicatricial - Subtipo: Lúpus Eritematoso Discoide? [Sim, Não]", PerguntaAnamnese.TipoResposta.SIM_NAO, ordem++));

            // ─── 14. INDICAÇÃO DO TERAPEUTA & RESPONSABILIDADE ────────────────────
            perguntas.add(criar("1. Aspecto do cabelo e couro cabeludo observado pelo Terapeuta", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("2. Tratamento indicado pelo Terapeuta Capilar e n.º de aplicações", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("3. Manutenção do cliente em casa indicado pelo Terapeuta Capilar", PerguntaAnamnese.TipoResposta.TEXTO, ordem++));
            perguntas.add(criar("Termo de Responsabilidade: As declarações acima são a expressão da verdade [Aceito pelo cliente]", PerguntaAnamnese.TipoResposta.SIM_NAO, ordem++));

            perguntaRepository.saveAll(perguntas);
            System.out.println(">>> [Doc-eMed] Todas as " + perguntas.size() + " perguntas da ficha completa (páginas 1 a 8) foram carregadas com sucesso!");
        }
    }

    private PerguntaAnamnese criar(String enunciado, PerguntaAnamnese.TipoResposta tipo, int ordem) {
        PerguntaAnamnese p = new PerguntaAnamnese();
        p.setEnunciado(enunciado);
        p.setTipoResposta(tipo);
        p.setAtiva(true);
        p.setOrdemExibicao(ordem);
        return p;
    }
}
