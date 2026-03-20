package org.lexture.softwarequalitytest.application.tenant.port.in;

import java.util.UUID;

public interface CreateTenantUseCase {

  TenantResponse create(CreateTenantCommand command);

  record CreateTenantCommand(String tenantName, UUID initialAdminUserId) {}
}
