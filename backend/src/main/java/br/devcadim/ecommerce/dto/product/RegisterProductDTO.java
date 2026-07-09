package br.devcadim.ecommerce.dto.product;


import java.math.BigDecimal;

public record RegisterProductDTO (String name,
                                  String description,
                                  String price,
                                  String productType){
}
