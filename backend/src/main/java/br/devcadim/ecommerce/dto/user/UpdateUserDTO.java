package br.devcadim.ecommerce.dto.user;

import java.util.Optional;

public record UpdateUserDTO(String email, Optional<String> name, Optional<String> password, Optional<String> userType) {
}
