package com.project.ecom.services.customer.product;

import com.project.ecom.dtos.ProductDto;
import java.util.List;

public interface CustomerProductService {
    public List<ProductDto> getAllProducts();

    public List<ProductDto> searchProductByTitle(String name);
}
