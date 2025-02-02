package kasv.gunda.myexpense.models.dtos;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserDto {
    private UUID publicId;
    private String email;
    private String fullName;
}
