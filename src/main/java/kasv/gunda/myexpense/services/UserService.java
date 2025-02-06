package kasv.gunda.myexpense.services;

import jakarta.persistence.EntityNotFoundException;
import kasv.gunda.myexpense.events.UserDeletedEvent;
import kasv.gunda.myexpense.models.entities.User;
import kasv.gunda.myexpense.repositories.UserRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final ApplicationEventPublisher eventPublisher;

    public UserService(UserRepository userRepository, ApplicationEventPublisher eventPublisher) {
        this.userRepository = userRepository;
        this.eventPublisher = eventPublisher;
    }

    public User getUserById(String id) {
        return userRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    @Transactional
    public void deleteUser() {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = getUserByEmail(userEmail);

        eventPublisher.publishEvent(new UserDeletedEvent(this, user));

        userRepository.delete(user);
    }
}
