package br.com.docemed.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String login;

    @Column(nullable = false, length = 255)
    private String senha;

    @Column(nullable = false, length = 150)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private PerfilUsuario perfil;

    @Column(name = "paciente_id")
    private Long pacienteId;

    @Column(name = "telefone_whatsapp", length = 30)
    private String telefoneWhatsapp;

    @Column(nullable = false)
    @Builder.Default
    private Boolean ativo = true;

    @Column(name = "data_cadastro", nullable = false)
    @Builder.Default
    private LocalDateTime dataCadastro = LocalDateTime.now();
}
