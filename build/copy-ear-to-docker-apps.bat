@echo off
setlocal EnableExtensions EnableDelayedExpansion

set "BASE=%~dp0"
set "SRC=%BASE%..\app\ear\target"
set "DST=%BASE%docker\apps"

echo ==========================================
echo  Copia EAR in build\docker\apps
echo ==========================================
echo.

REM Verifica se lo script sta girando come amministratore
net session >nul 2>&1
if %errorlevel% neq 0 (
    echo Richiesta esecuzione come Amministratore...
    echo Se compare il prompt UAC, conferma o inserisci le credenziali admin.
    echo.

    powershell -NoProfile -ExecutionPolicy Bypass -Command "Start-Process -FilePath '%~f0' -Verb RunAs -WorkingDirectory '%BASE%' -Wait"
    exit /b
)

echo Esecuzione con privilegi amministrativi OK.
echo.

echo Folder script:
echo "%BASE%"
echo.
echo Sorgente:
echo "%SRC%"
echo.
echo Destinazione:
echo "%DST%"
echo.

REM Verifica folder sorgente
if not exist "%SRC%" (
    echo ERRORE: la folder sorgente non esiste:
    echo "%SRC%"
    pause
    exit /b 1
)

REM Crea folder destinazione se non esiste
if not exist "%DST%" (
    echo Folder destinazione non trovata. La creo:
    echo "%DST%"
    mkdir "%DST%"
    if %errorlevel% neq 0 (
        echo ERRORE: impossibile creare la folder destinazione.
        pause
        exit /b 1
    )
)

REM Controlla presenza file EAR
dir "%SRC%\*.ear" >nul 2>&1
if %errorlevel% neq 0 (
    echo ERRORE: nessun file .ear trovato nella folder sorgente:
    echo "%SRC%"
    pause
    exit /b 1
)

echo Svuoto la folder destinazione...
echo.

del /f /q "%DST%\*" >nul 2>&1

for /d %%D in ("%DST%\*") do (
    rd /s /q "%%D"
)

echo Copio file EAR...
echo.

copy /Y "%SRC%\*.ear" "%DST%\"

if %errorlevel% neq 0 (
    echo ERRORE durante la copia del file EAR.
    pause
    exit /b 1
)

echo.
echo Copia completata correttamente.
echo.

dir "%DST%\*.ear"

echo.
pause
exit /b 0