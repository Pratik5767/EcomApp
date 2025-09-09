package com.project.ecom.services.admin.category;

import com.project.ecom.dtos.CategoryDto;
import com.project.ecom.entity.CategoryEntity;
import com.project.ecom.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryEntity createCategory(CategoryDto categoryDto) {
        CategoryEntity category = CategoryEntity.builder()
                .name(categoryDto.getName())
                .description(categoryDto.getDescription())
                .build();
        return categoryRepository.save(category);
    }

    @Override
    public List<CategoryEntity> getAllCategories() {
        return categoryRepository.findAll();
    }
}
