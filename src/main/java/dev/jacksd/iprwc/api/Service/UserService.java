package dev.jacksd.iprwc.api.Service;

import dev.jacksd.iprwc.api.model.User;
import dev.jacksd.iprwc.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(UUID id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUserByEmail(String email) {return userRepository.findByEmail(email);}

    public boolean isUserTaken(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public void save(User user) {userRepository.save(user);}

}
