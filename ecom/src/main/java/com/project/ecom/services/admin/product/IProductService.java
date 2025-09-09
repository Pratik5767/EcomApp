package com.project.ecom.services.admin.product;

import com.project.ecom.dtos.ProductDto;

import java.io.IOException;
import java.util.List;

public interface IProductService {
    ProductDto addProduct(ProductDto dto) throws IOException;

    List<ProductDto> getAllProducts();
}
