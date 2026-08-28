@echo off
title Doc-eMed - Tunel Publico (Link Fixo Ngrok)
cls
echo ================================================================
echo   Doc-eMed - Tunel Publico Permanente (Ngrok)
echo   Dominio Fixo : https://slighting-zippy-machinist.ngrok-free.dev
echo   Swagger UI   : https://slighting-zippy-machinist.ngrok-free.dev/swagger-ui/index.html
echo ================================================================
echo.
powershell -ExecutionPolicy Bypass -File "%~dp0iniciar-tunnel-publico.ps1"
pause
