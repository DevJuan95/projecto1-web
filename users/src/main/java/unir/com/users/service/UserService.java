package unir.com.users.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import unir.com.users.dto.CreateUserRequest;
import unir.com.users.entity.User;
import unir.com.users.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User createUser(CreateUserRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email existente");
        }
        User user = new User();
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setEmail(request.email());
        return userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User update(Long id, User user) {
        var userToUpdate = userRepository.findById(id);
        if(userToUpdate.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "");
        }
        userToUpdate.get().updateFrom(user);
        return userRepository.save(userToUpdate.get());
    }

    public boolean deleteUser(Long id) {
        var taskToDelete = userRepository.findById(id);
        if(taskToDelete.isEmpty()) {
            return false;
        }
        userRepository.deleteById(id);
        return true;
    }
}
