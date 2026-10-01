$ErrorActionPreference = 'Stop'
$projectRoot = Split-Path -Parent $MyInvocation.MyCommand.Path
$maven = Get-Command mvn -ErrorAction SilentlyContinue

if (-not $maven) {
    throw "Maven no está instalado o no está disponible en PATH. Instala Maven y vuelve a ejecutar este script."
}

$port = 8081
if (Get-NetTCPConnection -LocalPort $port -State Listen -ErrorAction SilentlyContinue) {
    throw "Los puertos 8080 y 8081 están ocupados. Cierra una ejecución anterior y vuelve a intentarlo."
}
Write-Host "Iniciando Job Application Assistant en http://localhost:8081" -ForegroundColor Green

Push-Location $projectRoot
try {
    & $maven.Source "-Dspring-boot.run.arguments=--server.port=$port" spring-boot:run
}
finally {
    Pop-Location
}
