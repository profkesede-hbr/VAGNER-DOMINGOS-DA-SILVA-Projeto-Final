package br.com.docemed.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "mensagens_chat")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MensagemChat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String remetenteLogin;

    @Column(nullable = false)
    private String remetenteNome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PerfilUsuario tipoRemetente; // RECEPCAO, MEDICO, ADMIN

    @Column(nullable = false)
    private String destinatarioLogin; // ex: admin, recep, medico

    @Column(nullable = false)
    private String destinatarioNome;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String conteudo;

    @Column(nullable = false)
    private LocalDateTime dataEnvio;

    @Column(nullable = false)
    @Builder.Default
    private Boolean lida = false;
}
