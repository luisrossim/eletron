package com.rossim.eletron.DTO;

import com.rossim.eletron.Enum.UserRoleEnum;

public record RegisterDTO (
        String login,
        String password,
        String nome,
        UserRoleEnum role
) {}