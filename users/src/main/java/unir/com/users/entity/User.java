package unir.com.users.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false, length = 255)
        private String firstName;

        @Column(nullable = false, length = 255)
        private String lastName;

        @Column(nullable = false, unique = true, length = 100)
        private String email;

        @Column(name = "created_at", nullable = false, updatable = false)
        @CreationTimestamp
        private LocalDateTime createdAt;

        public void updateFrom(User source) {
                this.firstName = source.getFirstName();
                this.lastName = source.getLastName();
                this.email = source.getEmail();
        }
}
