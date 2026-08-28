package br.com.docemed.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "agendamentos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "paciente_id", nullable = false)
    private Long pacienteId;

    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    @Builder.Default
    private StatusAgendamento status = StatusAgendamento.PENDENTE;

    @Column(name = "motivo_alteracao", columnDefinition = "TEXT")
    private String motivoAlteracao;

    @Column(name = "anamnese_id")
    private Long anamneseId;

    @Column(name = "medico_responsavel", length = 150)
    @Builder.Default
    private String medicoResponsavel = "Dr(a). Especialista em Tricologia";

    @Column(name = "data_solicitacao", nullable = false)
    @Builder.Default
    private LocalDateTime dataSolicitacao = LocalDateTime.now();

    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;
}
