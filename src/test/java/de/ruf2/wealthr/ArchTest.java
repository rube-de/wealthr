package de.ruf2.wealthr;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("de.ruf2.wealthr");

        noClasses()
            .that()
                .resideInAnyPackage("de.ruf2.wealthr.service..")
            .or()
                .resideInAnyPackage("de.ruf2.wealthr.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..de.ruf2.wealthr.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
