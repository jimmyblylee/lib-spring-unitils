@echo off
title Installing Spring Unitils
call msg info "[INFO] Installing Spring Unitils" & echo.

set BASEDIR=%~dp0

pushd %BASEDIR%\..\src\spring-unitils
  call mvn clean install -DperformTest=true -DperformSource=true
popd

call beep.bat
timeout /t 1 >NUL 
call beep.bat
timeout /t 1 >NUL 
call beep.bat
timeout /t 1 >NUL 
call beep.bat
timeout /t 1 >NUL 
call beep.bat
timeout /t 1 >NUL 

timeout /t 10 >NUL 