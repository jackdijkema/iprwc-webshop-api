package dev.jacksd.iprwc.api.DTO;

import dev.jacksd.iprwc.api.enums.Role;
import dev.jacksd.iprwc.api.model.Order;
import lombok.Data;

import java.util.Set;

@Data
public class UserDTO {
    String id;
    String firstname;
    String lastname;
    String email;
    Role role;
}
