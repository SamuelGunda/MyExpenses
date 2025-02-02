package kasv.gunda.myexpense.services;

import kasv.gunda.myexpense.mappers.UserDtoMapper;
import kasv.gunda.myexpense.models.dtos.UserDto;
import kasv.gunda.myexpense.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServices {
    private final UserRepository userRepository;
    private final UserDtoMapper userDtoMapper;

    public UserServices(UserRepository userRepository, UserDtoMapper userDtoMapper) {
        this.userRepository = userRepository;
        this.userDtoMapper = userDtoMapper;
    }

    public UserDto getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userDtoMapper::toDto)
                .orElse(null);

    }
}
