package org.lexture.softwarequalitytest.application.tenant.port.in;

import java.util.UUID;

public interface ManageTenantUseCase {

  TenantResponse createApiKey(UUID tenantId);

  TenantResponse inviteUser(UUID tenantId, UUID userId);

  TenantResponse createProject(UUID tenantId, String projectName);
}
