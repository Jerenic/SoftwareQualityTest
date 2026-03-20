package org.lexture.softwarequalitytest.application.tenant.port.out;

import java.util.Optional;
import java.util.UUID;
import org.lexture.softwarequalitytest.domain.tenant.Tenant;

public interface TenantRepository {

  Tenant save(Tenant tenant);

  Optional<Tenant> findById(UUID tenantId);
}
