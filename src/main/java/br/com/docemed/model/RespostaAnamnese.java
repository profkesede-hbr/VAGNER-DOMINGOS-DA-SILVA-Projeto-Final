package br.com.docemed.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidade que representa a resposta de um paciente a uma pergunta dinâmica da Anamnese.
 */
@Entity
@Table(name = "respostas_anamnese")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespostaAnamnese {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "anamnese_id", nullable = false)
    private Anamnese anamnese;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pergunta_id", nullable = false)
    private PerguntaAnamnese pergunta;

    @Column(name = "resposta", length = 1000)
    private String resposta;
}
