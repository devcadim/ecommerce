package br.devcadim.ecommerce.model.product;


import br.devcadim.ecommerce.dto.product.UpdateProductDTO;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "tb_product s")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "description", nullable = false, length = 150)
    private String description;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(name = "product_type", nullable = false, length = 30)
    private ProductType productType;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public void updateFromDto(UpdateProductDTO dto) {
        dto.name().ifPresent(newName -> {
            if(!newName.isBlank()) this.setName(newName);
        });

        dto.description().ifPresent(newDescription -> {
            if(!newDescription.isBlank()) this.setDescription(newDescription);
        });

        dto.price().ifPresent(newPrice -> {
            if(!newPrice.isBlank()) this.setPrice(new BigDecimal(newPrice));
        });

        dto.productType().ifPresent(newProductType -> {
            if(!newProductType.isBlank()) this.setProductType(ProductType.valueOf(newProductType.toUpperCase()));
        });
    }
}
