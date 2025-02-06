package kasv.gunda.myexpense.controllers;

import kasv.gunda.myexpense.mappers.UserDtoMapper;
import kasv.gunda.myexpense.models.dtos.UserDto;
import kasv.gunda.myexpense.models.entities.User;
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
        User user = userServices.getUserById(id);
        UserDto userDto = dtoMapper.toDto(user);

        return ResponseEntity.ok(userDto);
    }

    @GetMapping()
    public ResponseEntity<UserDto> getUserByEmail(@RequestParam String email) {
        User user = userServices.getUserByEmail(email);
        UserDto userDto = dtoMapper.toDto(user);

        return ResponseEntity.ok(userDto);
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteUser() {
        userServices.deleteUser();

        return ResponseEntity.noContent().build();
    }
}
