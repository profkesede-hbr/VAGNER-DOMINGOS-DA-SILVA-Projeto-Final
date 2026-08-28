# ==============================================================================
# Doc-eMed — Script de Inicialização do Túnel Público com Link Fixo (Ngrok)
# Domínio Fixo Permanente: https://slighting-zippy-machinist.ngrok-free.dev
# ==============================================================================

$ToolsDir = "P:\01-PROJETOS\VAGNER-DOMINGOS-DA-SILVA-Projeto-Final\tools"
$NgrokExe = "$ToolsDir\ngrok.exe"
$NgrokConfig = "$ToolsDir\ngrok.yml"
$Domain = "slighting-zippy-machinist.ngrok-free.dev"

$TargetUrl = "http://192.168.240.10:8080"
try {
    $tcp = Test-NetConnection -ComputerName "192.168.240.10" -Port 8080 -WarningAction SilentlyContinue
    if (-not $tcp.TcpTestSucceeded) {
        $TargetUrl = "http://localhost:8080"
    }
} catch {
    $TargetUrl = "http://localhost:8080"
}

Write-Host "================================================================" -ForegroundColor Cyan
Write-Host ">>> Doc-eMed — Iniciando Túnel Ngrok Fixo" -ForegroundColor Cyan
Write-Host ">>> Alvo da API : $TargetUrl" -ForegroundColor Yellow
Write-Host ">>> Link FIXO   : https://$Domain" -ForegroundColor Green
Write-Host ">>> Swagger UI  : https://$Domain/swagger-ui/index.html" -ForegroundColor Green
Write-Host "================================================================" -ForegroundColor Cyan

& $NgrokExe http --config $NgrokConfig --url="https://$Domain" $TargetUrl
