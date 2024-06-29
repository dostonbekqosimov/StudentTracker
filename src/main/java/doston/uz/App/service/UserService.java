package doston.uz.App.service;

import doston.uz.App.dto.userDTO.UserCreationDTO;
import doston.uz.App.model.Role;
import doston.uz.App.model.Teacher;
import doston.uz.App.model.User;
import doston.uz.App.repository.RoleRepository;
import doston.uz.App.repository.TeacherRepository;
import doston.uz.App.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(UserCreationDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        Set<Role> roles = userDTO.getRoles().stream()
                .map(roleName -> roleRepository.findByName(roleName)
                        .orElseThrow(() -> new RuntimeException("Role not found: " + roleName)))
                .collect(Collectors.toSet());
        user.setRoles(roles);

        if (userDTO.getTeacherId() != null) {
            Teacher teacher = teacherRepository.findById(userDTO.getTeacherId())
                    .orElseThrow(() -> new RuntimeException("Teacher not found"));
            user.setTeacher(teacher);
        }

        return userRepository.save(user);
    }
}