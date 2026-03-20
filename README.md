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

PostgreSQL-Integrationstest mit Testcontainers:

```bash
./gradlew test --rerun-tasks -DrunPostgresIntegration=true
```

## Datenbankmigrationen (Flyway)

Dieses Projekt nutzt Flyway fuer versionierte Datenbankmigrationen.

- Migrationspfad: `src/main/resources/db/migration`
- Initiale Migration: `V1__init_tenant_schema.sql`
- Flyway wird beim Start automatisch ausgefuehrt.

## Continuous Integration (CI)

Ein GitHub-Workflow ist unter `.github/workflows/ci.yml` hinterlegt und fuehrt aus:

- Gradle Wrapper Validation
- `spotlessCheck`
- Unit-/Architekturtests
- PostgreSQL-Integrationstest mit Testcontainers

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

### Git-Hooks via Plugin (nicht nativ)

Dieses Projekt verwendet das Gradle-Plugin `com.star-zero.gradle.githook`.
Damit werden Git-Hooks fuer alle Umgebungen einheitlich ueber Gradle generiert.

Hook einmalig installieren/aktualisieren:

```bash
./gradlew tasks
```

Der konfigurierte `pre-commit` Hook fuehrt aus:

- `spotlessApply`
- `spotlessCheck`
- `test`
