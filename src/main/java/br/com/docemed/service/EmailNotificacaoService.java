package br.com.docemed.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Slf4j
public class EmailNotificacaoService {

    public void enviarEmailBoasVindasPresencial(String destinatarioEmail, String pacienteNome, String cpfLogin, String senhaProvisoria) {
        String dataHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy 'às' HH:mm"));
        
        String corpoEmail = """
            ========================================================================================
            📧 [SIMULAÇÃO DE DISPARO DE E-MAIL - DOC-EMED NOTIFICAÇÃO OFICIAL]
            Para: %s
            Assunto: Bem-vindo(a) ao Doc-eMed — Seus Dados de Acesso ao Prontuário Eletrônico
            Data de Emissão: %s
            ----------------------------------------------------------------------------------------
            Olá, %s!
            
            Seu cadastro presencial foi realizado com sucesso na recepção da nossa clínica.
            Você já pode acessar seu prontuário digital, acompanhar seus receituários médicos,
            histórico de tratamentos capilares e agendamentos de qualquer dispositivo.
            
            🔐 SEUS DADOS DE ACESSO PROVISÓRIOS:
               • Link de Acesso: https://publicly-chem-nursery-chapter.trycloudflare.com/paciente/login
               • Usuário / Login: %s (Seu CPF)
               • Senha Provisória: %s
            
            ⚠️ IMPORTANTE:
            Ao realizar seu primeiro login, recomendamos que altere sua senha de acesso para sua
            segurança e privacidade.
            
            Atenciosamente,
            Equipe de Atendimento & Recepção — Doc-eMed
            Instituto Hardware BR em conjunto com IFSP (2025.2)
            ========================================================================================
            """.formatted(destinatarioEmail, dataHora, pacienteNome, cpfLogin, senhaProvisoria);

        log.info("\n{}", corpoEmail);
        System.out.println(corpoEmail);
    }

    public void enviarEmailResetSenha(String destinatarioEmail, String pacienteNome, String cpfLogin, String novaSenha) {
        String dataHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy 'às' HH:mm"));

        String corpoEmail = """
            ========================================================================================
            📧 [DOC-EMED NOTIFICAÇÃO - REDEFINIÇÃO DE SENHA]
            Para: %s
            Assunto: Doc-eMed — Sua Nova Senha de Acesso ao Prontuário
            Data: %s
            ----------------------------------------------------------------------------------------
            Olá, %s!
            
            Conforme solicitado na recepção da clínica, sua senha de acesso foi redefinida com sucesso.
            
            🔐 SUAS NOVAS CREDENCIAIS:
               • Link de Acesso: https://publicly-chem-nursery-chapter.trycloudflare.com/paciente/login
               • Usuário / Login: %s
               • Nova Senha: %s
            
            Atenciosamente,
            Equipe Doc-eMed
            ========================================================================================
            """.formatted(destinatarioEmail, dataHora, pacienteNome, cpfLogin, novaSenha);

        log.info("\n{}", corpoEmail);
        System.out.println(corpoEmail);
    }
}
