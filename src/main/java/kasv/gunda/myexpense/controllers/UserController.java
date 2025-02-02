package kasv.gunda.myexpense.controllers;

import kasv.gunda.myexpense.models.dtos.UserDto;
import kasv.gunda.myexpense.services.UserServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserServices userServices;

    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping()
    public ResponseEntity<UserDto> getUserByEmail(@RequestParam String email) {
        UserDto userDto = userServices.getUserByEmail(email);

        if (userDto == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(userDto);
    }
}
