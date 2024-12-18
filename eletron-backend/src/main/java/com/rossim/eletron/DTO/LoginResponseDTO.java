package com.rossim.eletron.DTO;

import org.springframework.security.core.userdetails.UserDetails;

public record LoginResponseDTO(
        UserDetails usuario,
        String token
) {}