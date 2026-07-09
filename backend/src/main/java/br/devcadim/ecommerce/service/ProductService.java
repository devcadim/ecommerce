package br.devcadim.ecommerce.service;


import br.devcadim.ecommerce.dto.product.RegisterProductDTO;
import br.devcadim.ecommerce.dto.product.UpdateProductDTO;
import br.devcadim.ecommerce.model.product.ProductEntity;
import br.devcadim.ecommerce.model.product.ProductType;
import br.devcadim.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public void saveProduct(RegisterProductDTO dto) {
        ProductEntity newProduct = new ProductEntity();
        newProduct.setName(dto.name());
        newProduct.setDescription(dto.description());

        newProduct.setProductType(ProductType.valueOf(dto.productType().toUpperCase()));
        BigDecimal price = new BigDecimal(dto.price());
        newProduct.setPrice(price);


        productRepository.save(newProduct);
    }

    public List<ProductEntity> listProducts() {
        return productRepository.findAll();
    }

    @Transactional
    public void updateProduct(UpdateProductDTO dto) {
        UUID productUUID = UUID.fromString(dto.id());
        ProductEntity product = productRepository.getById(productUUID);

        product.updateFromDto(dto);
    }

    @Transactional
    public void deleteProduct(String id) {
        UUID productUUID = UUID.fromString(id);
        ProductEntity product = productRepository.getById(productUUID);
        productRepository.delete(product);
    }

}
