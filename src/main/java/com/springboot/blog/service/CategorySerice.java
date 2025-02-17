package com.springboot.blog.service;

import com.springboot.blog.payload.CategoryDto;

import java.util.List;

public interface CategorySerice {
    CategoryDto addCategory(CategoryDto categoryDto);
    CategoryDto getCategory(Long categorytId);
    List<CategoryDto> getAllCategories();
    CategoryDto updateCategory(CategoryDto categoryDto,Long categorytId);
    void deleteCategory(Long categorytId);
}
