package org.lexture.softwarequalitytest.application.tenant.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.lexture.softwarequalitytest.application.tenant.port.in.CreateTenantUseCase.CreateTenantCommand;
import org.lexture.softwarequalitytest.application.tenant.port.in.TenantResponse;
import org.lexture.softwarequalitytest.application.tenant.port.in.UpdateTenantUseCase.UpdateTenantCommand;
import org.lexture.softwarequalitytest.application.tenant.port.out.TenantRepository;
import org.lexture.softwarequalitytest.domain.tenant.Tenant;

class TenantServiceTest {

  private TenantService tenantService;

  @BeforeEach
  void setup() {
    tenantService = new TenantService(new InMemoryTestTenantRepository());
  }

  @Test
  void shouldCreateTenantWithValidNameAndAdmin() {
    TenantResponse response =
        tenantService.create(new CreateTenantCommand("Tenant123", UUID.randomUUID()));

    assertNotNull(response.id());
    assertEquals("Tenant123", response.name());
    assertEquals(1, response.adminUserIds().size());
  }

  @Test
  void shouldRejectInvalidTenantName() {
    assertThrows(
        IllegalArgumentException.class,
        () -> tenantService.create(new CreateTenantCommand("invalid name", UUID.randomUUID())));
  }

  @Test
  void shouldUpdateTenantName() {
    TenantResponse created =
        tenantService.create(new CreateTenantCommand("Tenant123", UUID.randomUUID()));

    TenantResponse updated =
        tenantService.updateName(new UpdateTenantCommand(created.id(), "Tenant456"));

    assertEquals("Tenant456", updated.name());
  }

  private static class InMemoryTestTenantRepository implements TenantRepository {

    private final Map<UUID, Tenant> store = new HashMap<>();

    @Override
    public Tenant save(Tenant tenant) {
      store.put(tenant.getId(), tenant);
      return tenant;
    }

    @Override
    public Optional<Tenant> findById(UUID tenantId) {
      return Optional.ofNullable(store.get(tenantId));
    }
  }
}
