package br.devcadim.ecommerce.service;


import br.devcadim.ecommerce.dto.user.RegisterUserDTO;
import br.devcadim.ecommerce.dto.user.UpdateUserDTO;
import br.devcadim.ecommerce.model.user.UserEntity;
import br.devcadim.ecommerce.model.user.UserType;
import br.devcadim.ecommerce.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;


    public UserService(BCryptPasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveUser(RegisterUserDTO user) {
        UserEntity newUser = new UserEntity();
        newUser.setName(user.name());
        newUser.setEmail(user.email());
        newUser.setPassword(passwordEncoder.encode(user.password()));
        newUser.setCreated_at(LocalDateTime.now());
        try {
            newUser.setUserType(UserType.valueOf(user.userType().toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid user type");
        }
        userRepository.save(newUser);
    }


    public List<UserEntity> listUsers() {
        return userRepository.findAll();
    }


    @Transactional
    public void deleteUserByEmail(String email) {
        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        try {

            userRepository.delete(user);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("User " + email + " not found");
        }

    }

    @Transactional
    public void updateUser(UpdateUserDTO dto) {
        UserEntity user = userRepository.findByEmail(dto.email()).orElseThrow(() -> new RuntimeException("User not found"));

        user.updateFromDTO(dto, passwordEncoder);
    }

}