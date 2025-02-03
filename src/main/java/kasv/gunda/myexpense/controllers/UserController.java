package kasv.gunda.myexpense.controllers;

import kasv.gunda.myexpense.mappers.UserDtoMapper;
import kasv.gunda.myexpense.models.dtos.UserDto;
import kasv.gunda.myexpense.models.entities.User;
import kasv.gunda.myexpense.services.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private final IUserService userServices;
    private final UserDtoMapper dtoMapper;

    public UserController(IUserService userServices, UserDtoMapper dtoMapper) {
        this.userServices = userServices;
        this.dtoMapper = dtoMapper;
    }

    @GetMapping("/{publicId}")
    public ResponseEntity<UserDto> getUserByPublicId(@PathVariable String publicId) {
        UserDto userDto = Optional.ofNullable(userServices.getUserByPublicId(publicId))
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
