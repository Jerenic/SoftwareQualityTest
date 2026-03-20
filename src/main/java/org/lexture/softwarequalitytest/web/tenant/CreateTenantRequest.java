package org.lexture.softwarequalitytest.web.tenant;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.util.UUID;

public record CreateTenantRequest(
    @NotNull @Pattern(regexp = "^[a-zA-Z0-9]{3,20}$") String tenantName,
    @NotNull UUID initialAdminUserId) {}
