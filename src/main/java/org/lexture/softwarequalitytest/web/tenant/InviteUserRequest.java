package org.lexture.softwarequalitytest.web.tenant;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record InviteUserRequest(@NotNull UUID userId) {}
