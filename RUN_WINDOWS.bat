@echo off
TITLE Java Library Management System
cd /d "%~dp0src"
echo Compiling Java source code...
javac *.java
if errorlevel 1 (
    echo.
    echo Compilation failed. Please install Java JDK 17 or newer.
    pause
    exit /b 1
)
echo.
java LibraryApp
pause
