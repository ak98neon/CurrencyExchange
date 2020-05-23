package com.ak98neon.currencyexchange.model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum RoleName {
    ROLE_ADMIN, ROLE_USER, ROLE_SAFETY, ROLE_AB_MANAGER, ROLE_SC, ROLE_MANAGER, ROLE_SUPERVISOR, ROLE_DRIVER;

    public static List<String> getRolesWithoutRoleUser() {
        return Stream.of(RoleName.values())
                .map(Enum::name)
                .filter(r -> !r.equals("ROLE_USER"))
                .collect(Collectors.toList());
    }
}
