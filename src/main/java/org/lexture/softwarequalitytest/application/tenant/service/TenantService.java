package org.lexture.softwarequalitytest.application.tenant.service;

import java.util.UUID;
import org.lexture.softwarequalitytest.application.tenant.port.in.CreateTenantUseCase;
import org.lexture.softwarequalitytest.application.tenant.port.in.ManageTenantUseCase;
import org.lexture.softwarequalitytest.application.tenant.port.in.TenantResponse;
import org.lexture.softwarequalitytest.application.tenant.port.in.UpdateTenantUseCase;
import org.lexture.softwarequalitytest.application.tenant.port.out.TenantRepository;
import org.lexture.softwarequalitytest.domain.tenant.Tenant;
import org.lexture.softwarequalitytest.domain.tenant.TenantName;
import org.springframework.stereotype.Service;

@Service
public class TenantService
    implements CreateTenantUseCase, UpdateTenantUseCase, ManageTenantUseCase {

  private final TenantRepository tenantRepository;

  public TenantService(TenantRepository tenantRepository) {
    this.tenantRepository = tenantRepository;
  }

  @Override
  public TenantResponse create(CreateTenantCommand command) {
    Tenant tenant =
        Tenant.create(
            UUID.randomUUID(), new TenantName(command.tenantName()), command.initialAdminUserId());
    return TenantResponse.from(tenantRepository.save(tenant));
  }

  @Override
  public TenantResponse updateName(UpdateTenantCommand command) {
    Tenant tenant = findExisting(command.tenantId());
    tenant.rename(new TenantName(command.tenantName()));
    return TenantResponse.from(tenantRepository.save(tenant));
  }

  @Override
  public TenantResponse createApiKey(UUID tenantId) {
    Tenant tenant = findExisting(tenantId);
    tenant.generateApiKey();
    return TenantResponse.from(tenantRepository.save(tenant));
  }

  @Override
  public TenantResponse inviteUser(UUID tenantId, UUID userId) {
    Tenant tenant = findExisting(tenantId);
    tenant.inviteUser(userId);
    return TenantResponse.from(tenantRepository.save(tenant));
  }

  @Override
  public TenantResponse createProject(UUID tenantId, String projectName) {
    Tenant tenant = findExisting(tenantId);
    tenant.createProject(projectName);
    return TenantResponse.from(tenantRepository.save(tenant));
  }

  private Tenant findExisting(UUID tenantId) {
    return tenantRepository
        .findById(tenantId)
        .orElseThrow(() -> new IllegalArgumentException("Tenant not found: " + tenantId));
  }
}
