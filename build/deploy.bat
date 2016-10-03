@echo off
title Deploying Spring Unitils
call msg info "[INFO] Deploying Spring Unitils" & echo.

set BASEDIR=%~dp0

pushd %BASEDIR%\..\src\spring-unitils
  call mvn clean deploy -DperformTest=true -DperformSource=true -DperformDeploy=true
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

pause