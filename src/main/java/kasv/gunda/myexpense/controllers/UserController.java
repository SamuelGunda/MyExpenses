package kasv.gunda.myexpense.controllers;

import kasv.gunda.myexpense.mappers.UserDtoMapper;
import kasv.gunda.myexpense.models.dtos.UserDto;
import kasv.gunda.myexpense.services.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final IUserService userServices;
    private final UserDtoMapper dtoMapper;

    public UserController(IUserService userServices, UserDtoMapper dtoMapper) {
        this.userServices = userServices;
        this.dtoMapper = dtoMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable String id) {
        UserDto userDto = Optional.ofNullable(userServices.getUserById(id))
                .map(dtoMapper::toDto)
                .orElse(null);

        if (userDto == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(userDto);
    }

    @GetMapping()
    public ResponseEntity<UserDto> getUserByEmail(@RequestParam String email) {
        UserDto userDto = Optional.ofNullable(userServices.getUserByEmail(email))
                .map(dtoMapper::toDto)
                .orElse(null);

        if (userDto == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(userDto);
    }
}
