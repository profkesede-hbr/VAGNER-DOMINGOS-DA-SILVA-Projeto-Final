package br.com.docemed.dto;

import br.com.docemed.model.StatusFila;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilaItemDTO {
    private Long id;
    private Long pacienteId;
    private String pacienteNome;
    private String pacienteTelefone;
    private Long agendamentoId;
    private Integer posicao;
    private StatusFila status;
    private String statusDescricao;
    private String sala;
    private String medicoNome;
    private LocalDateTime horarioEntrada;
    private LocalDateTime horarioChamada;
    private Boolean possuiAnamnese;
    private String queixaPrincipal;
}
