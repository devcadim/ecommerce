package br.devcadim.ecommerce.controller;


import br.devcadim.ecommerce.dto.RegisterUserDTO;
import br.devcadim.ecommerce.dto.UpdateUserDTO;
import br.devcadim.ecommerce.model.UserEntity;
import br.devcadim.ecommerce.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> saveUser(@RequestBody RegisterUserDTO dto) {
        userService.saveUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto.email() + " created");
    }

    @GetMapping("/list")
    public ResponseEntity<List<UserEntity>> listUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.listUsers());
    }


    @DeleteMapping("/delete/{email}")
    public ResponseEntity<?> deleteUser(@PathVariable String email) {

        userService.deleteUserByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(email + " Deleted successfully");
    }

    @PatchMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody UpdateUserDTO dto){
        userService.updateUser(dto);
        return ResponseEntity.status(HttpStatus.OK).body(dto.email() + " updated successfully");
    }



}

