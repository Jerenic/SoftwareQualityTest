package org.lexture.softwarequalitytest.domain.tenant;

import java.util.Objects;
import java.util.regex.Pattern;

public record TenantName(String value) {

  private static final Pattern TENANT_NAME_PATTERN = Pattern.compile("^[a-zA-Z0-9]{3,20}$");

  public TenantName {
    if (Objects.isNull(value) || value.isBlank()) {
      throw new IllegalArgumentException("Tenant name must not be null or blank.");
    }
    if (!TENANT_NAME_PATTERN.matcher(value).matches()) {
      throw new IllegalArgumentException(
          "Tenant name must match ^[a-zA-Z0-9]{3,20}$ (3-20 alphanumeric characters).");
    }
  }
}
