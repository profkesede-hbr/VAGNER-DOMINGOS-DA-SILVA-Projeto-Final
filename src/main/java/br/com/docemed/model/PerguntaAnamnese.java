package br.com.docemed.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidade que representa uma Pergunta dinâmica da Anamnese.
 * O Administrador cria e gerencia as perguntas.
 */
@Entity
@Table(name = "perguntas_anamnese")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerguntaAnamnese {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 500)
    private String enunciado;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_resposta", nullable = false)
    private TipoResposta tipoResposta = TipoResposta.TEXTO;

    private Boolean ativa = true;

    @Column(name = "ordem_exibicao")
    private Integer ordemExibicao = 0;

    public enum TipoResposta {
        TEXTO,
        SIM_NAO,
        NUMERO
    }
}
