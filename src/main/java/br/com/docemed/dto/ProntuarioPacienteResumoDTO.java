package br.com.docemed.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProntuarioPacienteResumoDTO {
    private Long id;
    private Long pacienteId;
    private String pacienteNome;
    private String medicoNome;
    private LocalDateTime dataAtendimento;
    private String dataAtendimentoFormatada;
    private String diagnosticoResumo;
    private String tratamentoIndicado;
    private Integer numeroSessoes;
    private String receituarioMedicamentos;
    private String manutencaoHomeCare;
    private Integer retornoDias;
}
