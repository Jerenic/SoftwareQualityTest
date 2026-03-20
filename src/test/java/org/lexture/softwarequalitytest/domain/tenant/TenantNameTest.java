package org.lexture.softwarequalitytest.domain.tenant;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class TenantNameTest {

  @Test
  void shouldAcceptValidTenantName() {
    assertDoesNotThrow(() -> new TenantName("Tenant123"));
  }

  @Test
  void shouldRejectNullOrBlank() {
    assertThrows(IllegalArgumentException.class, () -> new TenantName(null));
    assertThrows(IllegalArgumentException.class, () -> new TenantName(" "));
  }

  @Test
  void shouldRejectInvalidCharactersAndLength() {
    assertThrows(IllegalArgumentException.class, () -> new TenantName("ab"));
    assertThrows(IllegalArgumentException.class, () -> new TenantName("name-with-dash"));
    assertThrows(IllegalArgumentException.class, () -> new TenantName("nämé"));
    assertThrows(IllegalArgumentException.class, () -> new TenantName("name with space"));
    assertThrows(IllegalArgumentException.class, () -> new TenantName("123456789012345678901"));
  }
}
