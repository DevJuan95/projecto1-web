package unir.com.users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateUserRequest(
        @NotBlank(message = "Primer Nombre es obligatorio")
        String firstName,
        @NotBlank(message = "Apellido es obligatorio")
        String lastName,
        @Email @NotBlank(message = "Email es obligatorio")
        String email
) { }
