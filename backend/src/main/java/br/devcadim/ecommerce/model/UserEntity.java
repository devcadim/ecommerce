package br.devcadim.ecommerce.model;

import br.devcadim.ecommerce.dto.UpdateUserDTO;
import jakarta.persistence.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

@Entity
@Table(name = "tb_users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, unique = true,  length = 100)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type", nullable = false, length = 20)
    private UserType userType;

    public UserEntity () {}

    public UserEntity(String name, String email, String password, UserType userType) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.userType = userType;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }


    public void updateFromDTO(UpdateUserDTO dto, PasswordEncoder passwordEncoder) {
        dto.name().ifPresent(newName -> {
            if(!newName.isBlank()) this.setName(newName);
        });

        dto.password().ifPresent(newPassword -> {
            if(!newPassword.isBlank()) this.setPassword(newPassword);
        });

        dto.userType().ifPresent(newUserType -> {
            if(!newUserType.isBlank()) this.setUserType(UserType.valueOf(newUserType.toUpperCase()));
        });
    }
}
