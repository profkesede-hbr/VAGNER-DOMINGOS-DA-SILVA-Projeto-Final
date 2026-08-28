package br.com.docemed.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReagendamentoDTO {

    @NotNull(message = "O ID do agendamento é obrigatório")
    private Long agendamentoId;

    @NotBlank(message = "O novo horário é obrigatório")
    private String novaDataHora;

    @NotBlank(message = "A justificativa/motivo para o paciente é obrigatória")
    private String motivo;
}
