package dev.jacksd.iprwc.api.DTO;

import lombok.Data;

import java.util.Set;

@Data
public class UserDTO {
    String username;
    String firstname;
    String lastname;
    String email;

    private Set<OrderDTO> orders;
}
