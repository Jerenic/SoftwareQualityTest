package org.lexture.softwarequalitytest.domain.tenant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;
import org.junit.jupiter.api.Test;

class TenantTest {

  @Test
  void shouldRequireAtLeastOneAdminOnCreate() {
    assertThrows(
        IllegalArgumentException.class,
        () -> Tenant.create(UUID.randomUUID(), new TenantName("Tenant1"), null));
  }

  @Test
  void shouldAllowOnlyOneApiKey() {
    Tenant tenant = Tenant.create(UUID.randomUUID(), new TenantName("Tenant1"), UUID.randomUUID());
    String apiKey = tenant.generateApiKey();

    assertNotNull(apiKey);
    assertThrows(IllegalStateException.class, tenant::generateApiKey);
  }

  @Test
  void shouldAllowMultipleProjectsAndMembers() {
    Tenant tenant = Tenant.create(UUID.randomUUID(), new TenantName("Tenant1"), UUID.randomUUID());
    UUID invitedUser = UUID.randomUUID();

    tenant.inviteUser(invitedUser);
    tenant.createProject("Project Alpha");
    tenant.createProject("Project Beta");

    assertTrue(tenant.getMemberUserIds().contains(invitedUser));
    assertEquals(2, tenant.getProjectNames().size());
  }
}
