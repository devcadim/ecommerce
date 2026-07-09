package br.devcadim.ecommerce.controller.product;

import br.devcadim.ecommerce.dto.product.RegisterProductDTO;
import br.devcadim.ecommerce.dto.product.UpdateProductDTO;
import br.devcadim.ecommerce.service.ProductService;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping("/create")
    public ResponseEntity<?> saveProduct(@RequestBody RegisterProductDTO dto) {
        productService.saveProduct(dto);
        return ResponseEntity.ok("Product: " + dto.name() + " created");
    }

    @GetMapping("/list")
    public ResponseEntity<?> listProducts() {
        return ResponseEntity.ok(productService.listProducts());
    }

    @PatchMapping("/update")
    public ResponseEntity<?> updateProduct(@RequestBody UpdateProductDTO dto) {
        productService.updateProduct(dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product " + id + " deleted successfully");
    }

}
