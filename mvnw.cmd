@echo off
setlocal

set "MAVEN_VERSION=3.9.9"
set "MVN_HOME=%USERPROFILE%\.m2\wrapper\dists\apache-maven-%MAVEN_VERSION%\apache-maven-%MAVEN_VERSION%"
set "MVN_CMD=%MVN_HOME%\bin\mvn.cmd"
set "IDEA_MVN=C:\Program Files\JetBrains\IntelliJ IDEA 2026.2.2\plugins\maven\lib\maven3\bin\mvn.cmd"

if exist "%MVN_CMD%" (
    call "%MVN_CMD%" %*
    exit /b %ERRORLEVEL%
)

if exist "%IDEA_MVN%" (
    call "%IDEA_MVN%" %*
    exit /b %ERRORLEVEL%
)

echo Downloading Apache Maven %MAVEN_VERSION%...
powershell -NoProfile -ExecutionPolicy Bypass -Command ^
    "$dest = '%USERPROFILE%\.m2\wrapper\dists\apache-maven-%MAVEN_VERSION%'; " ^
    "New-Item -ItemType Directory -Force -Path $dest | Out-Null; " ^
    "$zip = Join-Path $dest 'maven.zip'; " ^
    "Invoke-WebRequest -Uri 'https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/%MAVEN_VERSION%/apache-maven-%MAVEN_VERSION%-bin.zip' -OutFile $zip; " ^
    "Expand-Archive -LiteralPath $zip -DestinationPath $dest -Force"

call "%MVN_CMD%" %*
exit /b %ERRORLEVEL%
