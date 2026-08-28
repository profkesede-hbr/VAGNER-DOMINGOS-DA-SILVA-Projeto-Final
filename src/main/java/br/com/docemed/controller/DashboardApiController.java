package br.com.docemed.controller;

import br.com.docemed.dto.DashboardMetricasDTO;
import br.com.docemed.service.DashboardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
@Tag(name = "Dashboard & Métricas", description = "Métricas clínicas e dados analíticos para gráficos")
public class DashboardApiController {

    private final DashboardService dashboardService;

    @GetMapping("/metricas")
    @Operation(summary = "Obter Métricas do Dashboard Clínico")
    public ResponseEntity<DashboardMetricasDTO> obterMetricas() {
        return ResponseEntity.ok(dashboardService.obterMetricas());
    }
}
