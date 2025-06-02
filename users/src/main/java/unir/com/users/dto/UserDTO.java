package unir.com.users.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private TaskDTO[] tasks;
}
