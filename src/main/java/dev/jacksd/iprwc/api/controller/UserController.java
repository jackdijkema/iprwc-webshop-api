package dev.jacksd.iprwc.api.controller;

import dev.jacksd.iprwc.api.DTO.UserDTO;
import dev.jacksd.iprwc.api.Service.UserService;
import dev.jacksd.iprwc.api.model.User;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RequestMapping("api/v1/users")
@RestController
@PreAuthorize("hasRole('ADMIN')")
public class UserController {

    private final UserService userService;

    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getUsers();
    }

    @GetMapping(path = "/{id}")
    public Optional<User> getUserById(@PathVariable UUID id) {
        return userService.getUserById(id);
    }

    @GetMapping(path = "/customer")
    public ResponseEntity<UserDTO> getCustomerData() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authenticatedUserEmail = authentication.getName();

        Optional<User> user = userService.getUserByEmail(authenticatedUserEmail);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        if (!authenticatedUserEmail.equals(user.get().getEmail())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        Optional<User> requestedUser = userService.getUserByEmail(authenticatedUserEmail);
        Optional<UserDTO> userDTO = Optional.ofNullable(convertToDto(requestedUser));



        return userDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    private UserDTO convertToDto(Optional<User> user) {
        return modelMapper.map(user, UserDTO.class);
    }
}