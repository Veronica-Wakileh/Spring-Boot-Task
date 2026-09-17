package org.exalt.training.springboottask.dto;

import org.exalt.training.springboottask.model.UserRole;

public record UserRequest(
        String name
        , UserRole userRole
) {}
