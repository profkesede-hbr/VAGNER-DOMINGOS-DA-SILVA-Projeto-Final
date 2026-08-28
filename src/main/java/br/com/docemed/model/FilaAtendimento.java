package br.com.docemed.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "fila_atendimento")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilaAtendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "paciente_id", nullable = false)
    private Long pacienteId;

    @Column(name = "agendamento_id")
    private Long agendamentoId;

    @Column(nullable = false)
    private Integer posicao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    @Builder.Default
    private StatusFila status = StatusFila.AGUARDANDO;

    @Column(length = 50)
    @Builder.Default
    private String sala = "Consultório 01";

    @Column(name = "medico_nome", length = 150)
    @Builder.Default
    private String medicoNome = "Dr(a). Especialista Tricologista";

    @Column(name = "horario_entrada", nullable = false)
    @Builder.Default
    private LocalDateTime horarioEntrada = LocalDateTime.now();

    @Column(name = "horario_chamada")
    private LocalDateTime horarioChamada;
}
