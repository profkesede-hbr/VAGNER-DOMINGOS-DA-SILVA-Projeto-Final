# ==============================================================================
# Doc-eMed — Script de Inicialização do Túnel Público Gratuito (Cloudflare)
# Cria um link público HTTPS acessível de qualquer dispositivo no mundo sem custos!
# ==============================================================================

$ToolsDir = "P:\01-PROJETOS\VAGNER-DOMINGOS-DA-SILVA-Projeto-Final\tools"
$CloudflaredExe = "$ToolsDir\cloudflared.exe"

if (-not (Test-Path $ToolsDir)) {
    New-Item -ItemType Directory -Path $ToolsDir -Force | Out-Null
}

if (-not (Test-Path $CloudflaredExe)) {
    Write-Host ">>> Baixando Cloudflare Tunnel portátil (gratuito e seguro)..." -ForegroundColor Cyan
    $Url = "https://github.com/cloudflare/cloudflared/releases/latest/download/cloudflared-windows-amd64.exe"
    Invoke-WebRequest -Uri $Url -OutFile $CloudflaredExe -UseBasicParsing
    Write-Host ">>> Cloudflare Tunnel pronto em $CloudflaredExe" -ForegroundColor Green
}

Write-Host ">>> Iniciando Túnel Público para http://localhost:8080..." -ForegroundColor Cyan
Write-Host ">>> O link público HTTPS gerado pela Cloudflare aparecerá abaixo:" -ForegroundColor Yellow

& $CloudflaredExe tunnel --url http://localhost:8080
