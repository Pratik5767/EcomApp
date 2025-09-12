package com.project.ecom.services.customer.product;

import com.project.ecom.dtos.ProductDto;
import com.project.ecom.entity.ProductEntity;
import com.project.ecom.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerProductServiceImpl implements CustomerProductService {

    private final ProductRepository productRepository;

    @Override
    public List<ProductDto> getAllProducts() {
        List<ProductEntity> products = productRepository.findAll();
        return products.stream().map(ProductEntity::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> searchProductByTitle(String name) {
        List<ProductEntity> products = productRepository.findAllByNameContaining(name);
        return products.stream().map(ProductEntity::convertToDto).collect(Collectors.toList());
    }
}
