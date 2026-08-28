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

    @GetMapping("/paciente/portal")
    public String pacientePortal() {
        return "paciente/portal";
    }

    @GetMapping("/medico/login")
    public String medicoLogin() {
        return "medico/login";
    }

    @GetMapping("/medico/portal")
    public String medicoPortal() {
        return "medico/portal";
    }

    @GetMapping("/painel-chamada")
    public String painelChamada() {
        return "tv/painel-chamada";
    }
}
