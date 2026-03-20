package org.lexture.softwarequalitytest.application.tenant.port.in;

import java.util.Set;
import java.util.UUID;
import org.lexture.softwarequalitytest.domain.tenant.Tenant;

public record TenantResponse(
    UUID id,
    String name,
    Set<UUID> adminUserIds,
    Set<UUID> memberUserIds,
    Set<String> projects,
    String apiKey) {

  public static TenantResponse from(Tenant tenant) {
    return new TenantResponse(
        tenant.getId(),
        tenant.getName().value(),
        tenant.getAdminUserIds(),
        tenant.getMemberUserIds(),
        tenant.getProjectNames(),
        tenant.getApiKey().orElse(null));
  }
}
