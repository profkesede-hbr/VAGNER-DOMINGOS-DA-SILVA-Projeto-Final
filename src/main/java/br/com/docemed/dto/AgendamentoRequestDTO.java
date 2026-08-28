package br.com.docemed.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AgendamentoRequestDTO {

    @NotNull(message = "O ID do paciente é obrigatório")
    private Long pacienteId;

    @NotNull(message = "A data e horário são obrigatórios")
    private String dataHora; // formato ISO yyyy-MM-ddTHH:mm ou yyyy-MM-dd HH:mm

    private Long anamneseId;
    private String medicoResponsavel;
}
