package org.lexture.softwarequalitytest.application.tenant.port.in;

import java.util.UUID;

public interface UpdateTenantUseCase {

  TenantResponse updateName(UpdateTenantCommand command);

  record UpdateTenantCommand(UUID tenantId, String tenantName) {}
}
