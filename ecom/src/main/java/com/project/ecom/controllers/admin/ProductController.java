package com.project.ecom.controllers.admin;

import com.project.ecom.dtos.ProductDto;
import com.project.ecom.services.admin.product.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class ProductController {

    private final IProductService productService;

    @PostMapping("/product")
    public ResponseEntity<ProductDto> addProduct(@ModelAttribute ProductDto productDto) {
        try {
            ProductDto product = productService.addProduct(productDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(product);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }
}
