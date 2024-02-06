package dev.jacksd.iprwc.api.DTO;

import lombok.Data;

import java.util.Set;

@Data
public class UserDTO {
    String username;
    String email;

    private Set<OrderDTO> orders;
}
