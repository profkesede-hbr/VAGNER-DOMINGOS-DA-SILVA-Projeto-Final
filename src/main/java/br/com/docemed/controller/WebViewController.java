package br.com.docemed.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebViewController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/paciente/acesso")
    public String pacienteAcesso() {
        return "paciente/login-cadastro";
    }

    @GetMapping("/paciente/login")
    public String pacienteLogin() {
        return "paciente/login";
    }

    @GetMapping("/paciente/portal")
    public String pacientePortal() {
        return "paciente/portal";
    }

    @GetMapping("/paciente/real-cadastro")
    public String pacienteRealCadastro() {
        return "paciente/real-cadastro";
    }

    @GetMapping("/paciente/real-portal")
    public String pacienteRealPortal() {
        return "paciente/real-portal";
    }

    @GetMapping("/medico/login")
    public String medicoLogin() {
        return "medico/login";
    }

    @GetMapping("/medico/portal")
    public String medicoPortal() {
        return "medico/portal";
    }

    @GetMapping("/recepcao/login")
    public String recepcaoLogin() {
        return "recepcao/login";
    }

    @GetMapping("/recepcao/portal")
    public String recepcaoPortal() {
        return "recepcao/portal";
    }

    @GetMapping("/painel-chamada")
    public String painelChamada() {
        return "tv/painel-chamada";
    }
}
