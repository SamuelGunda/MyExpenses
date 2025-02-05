package kasv.gunda.myexpense.controllers;

import jakarta.validation.Valid;
import kasv.gunda.myexpense.models.dtos.UserDto;
import kasv.gunda.myexpense.models.requests.LoginRequest;
import kasv.gunda.myexpense.models.requests.RegisterRequest;
import kasv.gunda.myexpense.models.responses.LoginResponse;
import kasv.gunda.myexpense.security.AuthenticationService;
import kasv.gunda.myexpense.security.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto> register(@RequestBody @Valid RegisterRequest request) {
        UserDto registeredUser = authenticationService.signup(request);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginRequest request) {
        String jwtToken = authenticationService.authenticate(request);

        LoginResponse loginResponse = new LoginResponse(jwtToken, jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
}
