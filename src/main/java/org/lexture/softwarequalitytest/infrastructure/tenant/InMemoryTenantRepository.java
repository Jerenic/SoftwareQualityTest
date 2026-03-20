package org.lexture.softwarequalitytest.infrastructure.tenant;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.lexture.softwarequalitytest.application.tenant.port.out.TenantRepository;
import org.lexture.softwarequalitytest.domain.tenant.Tenant;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryTenantRepository implements TenantRepository {

  private final Map<UUID, Tenant> tenants = new ConcurrentHashMap<>();

  @Override
  public Tenant save(Tenant tenant) {
    tenants.put(tenant.getId(), tenant);
    return tenant;
  }

  @Override
  public Optional<Tenant> findById(UUID tenantId) {
    return Optional.ofNullable(tenants.get(tenantId));
  }
}
