package doston.uz.App.dto.userDTO;

import lombok.Data;

import java.util.Set;

@Data
public class UserCreationDTO {
    private String username;
    private String password;
    private Set<String> roles;
    private Integer teacherId;  // This will be null for non-teacher users

    // ... getters and setters
}