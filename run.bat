@echo off
REM Usage: run.bat 1   (runs Experiment 1)   |   run.bat 10 (Experiment 10)
if "%1"=="" (
  echo Usage: run.bat ^<experiment number 1-10^>
  exit /b 1
)
set N=0%1
set N=%N:~-2%
for /d %%D in (Exp%N%_*) do (
  if not exist out\%%D mkdir out\%%D
  javac -cp "lib\*" -d out\%%D %%D\*.java
  if errorlevel 1 exit /b 1
  java -cp "out\%%D;lib\*" Main
)
