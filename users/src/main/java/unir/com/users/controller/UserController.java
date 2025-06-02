package unir.com.users.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unir.com.users.client.TaskClient;
import unir.com.users.dto.CreateUserRequest;
import unir.com.users.dto.TaskDTO;
import unir.com.users.dto.UserDTO;
import unir.com.users.entity.User;
import unir.com.users.service.UserService;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    TaskClient taskClient;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> listOrFindByEmail(
            @RequestParam(required = false) String email
    ) {
        if (email != null) {
            User user = userService.findByEmail(email);
            return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody CreateUserRequest userRequest) {
        User createdUser = userService.createUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        return ResponseEntity.ok(this.userService.update(id,user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if(this.userService.deleteUser(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/tasks")
    public ResponseEntity<UserDTO> getTasksByUser(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        if(user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<TaskDTO> tasks = taskClient.getTasks(id);
        UserDTO userResponse = new UserDTO();
        userResponse.setId(id);
        userResponse.setTasks(tasks.toArray(new TaskDTO[0]));
        userResponse.setFirstName(user.get().getFirstName());
        userResponse.setLastName(user.get().getLastName());
        userResponse.setEmail(user.get().getEmail());
        return ResponseEntity.ok(userResponse);
    }
}
