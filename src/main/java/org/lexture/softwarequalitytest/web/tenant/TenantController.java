package org.lexture.softwarequalitytest.web.tenant;

import jakarta.validation.Valid;
import java.util.UUID;
import org.lexture.softwarequalitytest.application.tenant.port.in.CreateTenantUseCase;
import org.lexture.softwarequalitytest.application.tenant.port.in.ManageTenantUseCase;
import org.lexture.softwarequalitytest.application.tenant.port.in.TenantResponse;
import org.lexture.softwarequalitytest.application.tenant.port.in.UpdateTenantUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tenants")
public class TenantController {

  private final CreateTenantUseCase createTenantUseCase;
  private final UpdateTenantUseCase updateTenantUseCase;
  private final ManageTenantUseCase manageTenantUseCase;

  public TenantController(
      CreateTenantUseCase createTenantUseCase,
      UpdateTenantUseCase updateTenantUseCase,
      ManageTenantUseCase manageTenantUseCase) {
    this.createTenantUseCase = createTenantUseCase;
    this.updateTenantUseCase = updateTenantUseCase;
    this.manageTenantUseCase = manageTenantUseCase;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public TenantResponse createTenant(@Valid @RequestBody CreateTenantRequest request) {
    return createTenantUseCase.create(
        new CreateTenantUseCase.CreateTenantCommand(
            request.tenantName(), request.initialAdminUserId()));
  }

  @PutMapping("/{tenantId}")
  public TenantResponse updateTenant(
      @PathVariable UUID tenantId, @Valid @RequestBody UpdateTenantRequest request) {
    return updateTenantUseCase.updateName(
        new UpdateTenantUseCase.UpdateTenantCommand(tenantId, request.tenantName()));
  }

  @PostMapping("/{tenantId}/api-key")
  public TenantResponse createApiKey(@PathVariable UUID tenantId) {
    return manageTenantUseCase.createApiKey(tenantId);
  }

  @PostMapping("/{tenantId}/members")
  public TenantResponse inviteUser(
      @PathVariable UUID tenantId, @Valid @RequestBody InviteUserRequest request) {
    return manageTenantUseCase.inviteUser(tenantId, request.userId());
  }

  @PostMapping("/{tenantId}/projects")
  public TenantResponse createProject(
      @PathVariable UUID tenantId, @Valid @RequestBody CreateProjectRequest request) {
    return manageTenantUseCase.createProject(tenantId, request.projectName());
  }
}
