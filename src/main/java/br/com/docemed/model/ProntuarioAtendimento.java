package br.com.docemed.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "prontuarios_atendimento")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProntuarioAtendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "paciente_id", nullable = false)
    private Long pacienteId;

    @Column(name = "agendamento_id")
    private Long agendamentoId;

    @Column(name = "anamnese_id")
    private Long anamneseId;

    @Column(name = "medico_nome", length = 150, nullable = false)
    @Builder.Default
    private String medicoNome = "Dr. Vagner Domingos — Tricologista Responsável";

    @Column(name = "data_atendimento", nullable = false)
    @Builder.Default
    private LocalDateTime dataAtendimento = LocalDateTime.now();

    @Column(name = "diagnostico_clinico", columnDefinition = "TEXT")
    private String diagnosticoClinico;

    @Column(name = "tratamento_indicado", columnDefinition = "TEXT")
    private String tratamentoIndicado;

    @Column(name = "numero_sessoes")
    private Integer numeroSessoes;

    @Column(name = "receituario_medicamentos", columnDefinition = "TEXT")
    private String receituarioMedicamentos;

    @Column(name = "manutencao_home_care", columnDefinition = "TEXT")
    private String manutencaoHomeCare;

    @Column(name = "observacoes_medicas", columnDefinition = "TEXT")
    private String observacoesMedicas;

    @Column(name = "retorno_dias")
    private Integer retornoDias;
}
