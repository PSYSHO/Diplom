package diplom.controllers;

import diplom.entity.Role;
import diplom.entity.Roles;
import diplom.entity.User;
import diplom.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/home")
public class HomeControlers {
    @Autowired
    UserRepository userRepository;
    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String userAccess() {
        return "User Content.";
    }

    @GetMapping("/mod")
    @PreAuthorize("hasRole('MODERATOR')")
    public String moderatorAccess() {
        return "Moderator Board.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }

    @PostMapping("/admin/roles/{id}")
    @PreAuthorize("hasRole(ADMIN)")
    public void updateAccess(@PathVariable("id") Long id){
        User user= userRepository.findById(id).get();
        Set<Role> roleSet= user.getRoles();
        Role role =new Role();role.setName(Roles.ROLE_MODERATOR);
        roleSet.add(role);
        user.setRoles(roleSet);
        userRepository.save(user);
    }
    @GetMapping("/admin/users")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    @DeleteMapping("/{id}")
    public void deleteAccess(@PathVariable("id") Long id){
        User user= userRepository.findById(id).get();
        Set<Role> roleSet =new HashSet<>();
        Role role =new Role();role.setName(Roles.ROLE_USER);
        roleSet.add(role);
        user.setRoles(roleSet);
    }
}
