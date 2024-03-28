package dev.jacksd.iprwc.api.auth;


import dev.jacksd.iprwc.api.DTO.AuthenticationRequest;
import dev.jacksd.iprwc.api.DTO.AuthenticationResponse;
import dev.jacksd.iprwc.api.DTO.RegisterRequest;
import dev.jacksd.iprwc.api.Service.AuthenticationService;
import dev.jacksd.iprwc.api.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {

        if(userService.isUserTaken(request.getEmail())) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
