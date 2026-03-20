# SoftwareQualityTest

Ein erstes Setup fuer das Projekt `SoftwareQualityTest`.

## Voraussetzungen

- Java (empfohlen: per SDKMAN verwalten)
- Gradle Wrapper (im Projekt enthalten)

## SDKMAN installieren

Fuehre zur Installation von SDKMAN folgenden Befehl aus:

```bash
curl -s "https://get.sdkman.io" | bash
```

Danach die Shell neu laden oder neu starten und pruefen:

```bash
sdk version
```

## Projekt starten

Im Projektverzeichnis:

```bash
./gradlew bootRun
```

Unter Windows PowerShell:

```powershell
.\gradlew.bat bootRun
```

## Tests ausfuehren

```bash
./gradlew test
```
