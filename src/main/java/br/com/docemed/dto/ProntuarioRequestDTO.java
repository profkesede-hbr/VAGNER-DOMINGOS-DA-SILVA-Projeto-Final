package br.com.docemed.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProntuarioRequestDTO {

    @NotNull(message = "O ID do paciente é obrigatório")
    private Long pacienteId;

    private Long agendamentoId;
    private Long anamneseId;
    private String medicoNome;

    private String diagnosticoClinico;
    private String tratamentoIndicado;
    private Integer numeroSessoes;
    private String receituarioMedicamentos;
    private String manutencaoHomeCare;
    private String observacoesMedicas;
    private Integer retornoDias;
}
