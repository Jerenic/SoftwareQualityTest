plugins {
    java
    checkstyle
    pmd
    id("org.springframework.boot") version "4.0.4"
    id("io.spring.dependency-management") version "1.1.7"
    id("com.github.spotbugs") version "6.4.8"
    id("com.diffplug.spotless") version "7.2.1"
    id("com.star-zero.gradle.githook") version "1.2.1"
}

group = "org.lexture"
version = "0.0.1-SNAPSHOT"
description = "SoftwareQualityTest"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.testcontainers:testcontainers-bom:1.20.1"))
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-webmvc")
    implementation("org.flywaydb:flyway-core")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    developmentOnly("org.springframework.boot:spring-boot-docker-compose")
    runtimeOnly("org.postgresql:postgresql")
    testImplementation("org.springframework.boot:spring-boot-starter-data-jpa-test")
    testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")
    testImplementation("com.tngtech.archunit:archunit-junit5:1.3.0")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.testcontainers:postgresql")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

spotless {
    java {
        googleJavaFormat("1.22.0")
        trimTrailingWhitespace()
        endWithNewline()
    }

    format("misc") {
        target(
            "*.md",
            "*.gitignore",
            "*.gitattributes",
            "*.yaml",
            "*.yml",
            "*.properties",
            "*.sh",
            "*.ps1"
        )
        trimTrailingWhitespace()
        endWithNewline()
    }
}

tasks.named("check") {
    dependsOn("spotlessCheck", "checkstyleMain", "pmdMain", "spotbugsMain")
}

checkstyle {
    toolVersion = "10.21.1"
    configFile = file("config/checkstyle/checkstyle.xml")
    isIgnoreFailures = false
}

pmd {
    toolVersion = "7.16.0"
    ruleSetFiles = files("config/pmd/ruleset.xml")
    ruleSets = listOf()
    isIgnoreFailures = false
}

tasks.withType<com.github.spotbugs.snom.SpotBugsTask>().configureEach {
    reportLevel = com.github.spotbugs.snom.Confidence.HIGH
    effort = com.github.spotbugs.snom.Effort.DEFAULT
    ignoreFailures = false
    reports.create("html") {
        required = true
        outputLocation = layout.buildDirectory.file("reports/spotbugs/${name}.html").get().asFile
    }
    reports.create("xml") {
        required = true
        outputLocation = layout.buildDirectory.file("reports/spotbugs/${name}.xml").get().asFile
    }
}

extensions.configure<Any>("githook") {
    withGroovyBuilder {
        "hooks" {
            "create"("pre-commit") {
                setProperty(
                    "task",
                    "spotlessApply spotlessCheck checkstyleMain pmdMain spotbugsMain test"
                )
            }
        }
    }
}
