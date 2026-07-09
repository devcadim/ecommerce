package br.devcadim.ecommerce.dto.product;

import java.util.Optional;

public record UpdateProductDTO (String id,
                                Optional<String> name,
                                Optional<String> description,
                                Optional<String> price,
                                Optional<String> productType){
}
