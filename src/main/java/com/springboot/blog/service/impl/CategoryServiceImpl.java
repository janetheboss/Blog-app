package com.springboot.blog.service.impl;

import com.springboot.blog.entity.Category;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.CategoryDto;
import com.springboot.blog.repository.CategoryRepository;
import com.springboot.blog.service.CategorySerice;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategorySerice {
    private CategoryRepository categoryRepository;
    private ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        Category savedCategory = categoryRepository.save(category);

         return modelMapper.map(savedCategory,CategoryDto.class);
    }

    @Override
    public CategoryDto getCategory(Long categorytId) {
        Category category = categoryRepository.findById(categorytId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categorytId));

        return modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList.stream().map((category) -> modelMapper.map(category,CategoryDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Long categorytId) {
      Category category = categoryRepository.findById(categorytId)
              .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categorytId));
     category.setName(categoryDto.getName());
     category.setDescription(categoryDto.getDescription());
     category.setId(categorytId);
     Category savedCategory = categoryRepository.save(category);

        return modelMapper.map(savedCategory,CategoryDto.class);
    }

    @Override
    public void deleteCategory(Long categorytId) {
        Category category = categoryRepository.findById(categorytId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categorytId));
        categoryRepository.delete(category);
    }
}
