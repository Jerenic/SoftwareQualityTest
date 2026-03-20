package org.lexture.softwarequalitytest;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.Test;

class ArchitectureTest {

  private final JavaClasses importedClasses =
      new ClassFileImporter().importPackages("org.lexture.softwarequalitytest");

  @Test
  void domainShouldNotDependOnWebOrInfrastructure() {
    noClasses()
        .that()
        .resideInAPackage("..domain..")
        .should()
        .dependOnClassesThat()
        .resideInAnyPackage("..web..", "..infrastructure..")
        .check(importedClasses);
  }

  @Test
  void applicationShouldNotDependOnWeb() {
    noClasses()
        .that()
        .resideInAPackage("..application..")
        .should()
        .dependOnClassesThat()
        .resideInAnyPackage("..web..")
        .check(importedClasses);
  }
}
