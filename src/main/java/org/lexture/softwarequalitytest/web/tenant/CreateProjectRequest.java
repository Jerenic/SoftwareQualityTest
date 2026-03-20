package org.lexture.softwarequalitytest.web.tenant;

import jakarta.validation.constraints.NotBlank;

public record CreateProjectRequest(@NotBlank String projectName) {}
