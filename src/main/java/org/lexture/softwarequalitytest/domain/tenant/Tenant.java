package org.lexture.softwarequalitytest.domain.tenant;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class Tenant {

  private final UUID id;
  private TenantName name;
  private final Set<UUID> adminUserIds;
  private final Set<UUID> memberUserIds;
  private final Set<String> projectNames;
  private String apiKey;

  private Tenant(UUID id, TenantName name, Set<UUID> adminUserIds, Set<UUID> memberUserIds) {
    this.id = Objects.requireNonNull(id, "Tenant id must not be null.");
    this.name = Objects.requireNonNull(name, "Tenant name must not be null.");
    this.adminUserIds = new LinkedHashSet<>(Objects.requireNonNull(adminUserIds));
    this.memberUserIds = new LinkedHashSet<>(Objects.requireNonNull(memberUserIds));
    this.projectNames = new LinkedHashSet<>();
    ensureAtLeastOneAdmin();
  }

  public static Tenant create(UUID id, TenantName name, UUID initialAdminUserId) {
    if (initialAdminUserId == null) {
      throw new IllegalArgumentException("Tenant must have at least one admin.");
    }
    Set<UUID> admins = new LinkedHashSet<>();
    admins.add(initialAdminUserId);
    Set<UUID> members = new LinkedHashSet<>();
    members.add(initialAdminUserId);
    return new Tenant(id, name, admins, members);
  }

  public UUID getId() {
    return id;
  }

  public TenantName getName() {
    return name;
  }

  public Set<UUID> getAdminUserIds() {
    return Set.copyOf(adminUserIds);
  }

  public Set<UUID> getMemberUserIds() {
    return Set.copyOf(memberUserIds);
  }

  public Set<String> getProjectNames() {
    return Set.copyOf(projectNames);
  }

  public Optional<String> getApiKey() {
    return Optional.ofNullable(apiKey);
  }

  public void rename(TenantName newName) {
    this.name = Objects.requireNonNull(newName, "Tenant name must not be null.");
  }

  public void addAdmin(UUID userId) {
    if (userId == null) {
      throw new IllegalArgumentException("Admin user id must not be null.");
    }
    adminUserIds.add(userId);
    memberUserIds.add(userId);
    ensureAtLeastOneAdmin();
  }

  public void inviteUser(UUID userId) {
    if (userId == null) {
      throw new IllegalArgumentException("Invited user id must not be null.");
    }
    memberUserIds.add(userId);
  }

  public void createProject(String projectName) {
    if (projectName == null || projectName.isBlank()) {
      throw new IllegalArgumentException("Project name must not be blank.");
    }
    projectNames.add(projectName.trim());
  }

  public String generateApiKey() {
    if (this.apiKey != null) {
      throw new IllegalStateException("Exactly one API key is allowed per tenant.");
    }
    this.apiKey = UUID.randomUUID().toString().replace("-", "");
    return this.apiKey;
  }

  private void ensureAtLeastOneAdmin() {
    if (adminUserIds.isEmpty()) {
      throw new IllegalArgumentException("Tenant must always have at least one admin.");
    }
  }
}
