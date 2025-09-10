package com.project.ecom.services.admin.product;

import com.project.ecom.dtos.ProductDto;
import com.project.ecom.entity.CategoryEntity;
import com.project.ecom.entity.ProductEntity;
import com.project.ecom.repositories.CategoryRepository;
import com.project.ecom.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public ProductDto addProduct(ProductDto dto) throws IOException {
        ProductEntity product = convertToEntity(dto);
        CategoryEntity category = categoryRepository.findById(dto.getCategoryId()).orElseThrow();
        product.setCategory(category);
        return productRepository.save(product).convertToDto();
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<ProductEntity> products = productRepository.findAll();
        return products.stream().map(ProductEntity::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getAllProductsByName(String name) {
        List<ProductEntity> products = productRepository.findAllByNameContaining(name);
        return products.stream().map(ProductEntity::convertToDto).collect(Collectors.toList());
    }

    private ProductEntity convertToEntity(ProductDto dto) throws IOException {
        return ProductEntity.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .image(dto.getImage().getBytes())
                .build();
    }
}
