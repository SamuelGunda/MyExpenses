package kasv.gunda.myexpense.security;

import kasv.gunda.myexpense.exceptions.ConflictException;
import kasv.gunda.myexpense.models.dtos.UserDto;
import kasv.gunda.myexpense.models.entities.User;
import kasv.gunda.myexpense.models.requests.LoginRequest;
import kasv.gunda.myexpense.models.requests.RegisterRequest;
import kasv.gunda.myexpense.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            JwtService jwtService
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public UserDto signup(RegisterRequest input) {
        if (userRepository.findByEmail(input.getEmail()).isPresent()) {
            throw new ConflictException("Email");
        }

        User user = new User();
        user.setFullName(input.getFullName());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));

        userRepository.save(user);

        return new UserDto(user.getId(), user.getEmail(), user.getFullName());
    }

    public String authenticate(LoginRequest input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        User authenticatedUser =  userRepository.findByEmail(input.getEmail())
                                                .orElseThrow();

        return jwtService.generateToken(authenticatedUser);
    }
}
