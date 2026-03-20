package org.lexture.softwarequalitytest.web.tenant;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UpdateTenantRequest(
    @NotNull @Pattern(regexp = "^[a-zA-Z0-9]{3,20}$") String tenantName) {}
