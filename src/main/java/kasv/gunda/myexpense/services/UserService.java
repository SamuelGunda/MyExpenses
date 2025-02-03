package kasv.gunda.myexpense.services;

import kasv.gunda.myexpense.models.entities.User;
import kasv.gunda.myexpense.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByPublicId(String publicId) {
        return userRepository.findByPublicId(UUID.fromString(publicId))
                .orElse(null);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElse(null);

    }
}
