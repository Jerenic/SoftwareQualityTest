# SoftwareQualityTest

Ein erstes Setup fuer das Projekt `SoftwareQualityTest`.

## Voraussetzungen

- Java 21 (LTS, empfohlen: per SDKMAN verwalten)
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

Empfohlene Java-Version installieren:

```bash
sdk install java 21.0.10-amzn
sdk default java 21.0.10-amzn
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

## Einheitliche Formatierung (alle OS)

Dieses Repo nutzt gemeinsame Regeln, damit Commits von Windows, macOS und Linux gleich formatiert sind:

- `.gitattributes` erzwingt konsistente Zeilenenden
- `.editorconfig` harmonisiert Editor-Formatierung
- Spotless (Google Java Format) formatiert Java-Code reproduzierbar

### Spotless manuell ausfuehren

```bash
./gradlew spotlessApply
./gradlew spotlessCheck
```

### Pre-commit Hook aktivieren

Linux/macOS/Git Bash:

```bash
./scripts/setup-git-hooks.sh
```

Windows PowerShell:

```powershell
.\scripts\setup-git-hooks.ps1
```

Danach wird vor jedem Commit automatisch Spotless ausgefuehrt.
