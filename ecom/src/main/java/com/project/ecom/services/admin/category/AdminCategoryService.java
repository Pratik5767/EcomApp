package com.project.ecom.services.admin.category;

import com.project.ecom.dtos.CategoryDto;
import com.project.ecom.entity.CategoryEntity;

import java.util.List;

public interface AdminCategoryService {

    CategoryEntity createCategory(CategoryDto categoryDto);

    List<CategoryEntity> getAllCategories();
}
