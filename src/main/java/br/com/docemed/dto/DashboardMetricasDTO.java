package br.com.docemed.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardMetricasDTO {
    private long totalPacientes;
    private long agendamentosHoje;
    private long pacientesNaFila;
    private long atendimentosConcluidos;
    private Map<String, Long> alopeciasFrequentes;
    private Map<String, Long> tiposCabeloDistribuicao;
    private Map<String, Long> caracteristicasCouro;
}
