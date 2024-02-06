package dev.jacksd.iprwc.api.DTO;

import dev.jacksd.iprwc.api.model.Order;
import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    String name;
    String email;
    String password;
    private List<Order> orders;
}
