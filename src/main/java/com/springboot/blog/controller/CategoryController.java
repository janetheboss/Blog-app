package com.springboot.blog.controller;

import com.springboot.blog.entity.Category;
import com.springboot.blog.payload.CategoryDto;
import com.springboot.blog.repository.CategoryRepository;
import com.springboot.blog.service.CategorySerice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    private CategorySerice categorySerice;

    public CategoryController(CategorySerice categorySerice) {
        this.categorySerice = categorySerice;
    }

    // Build add Cetegory Rest api
    @GetMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDto){
        CategoryDto savedCategory = categorySerice.addCategory(categoryDto);
      return new  ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable("id") Long categoryId){
       CategoryDto categoryDto  = categorySerice.getCategory(categoryId);
        return ResponseEntity.ok(categoryDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategory(){
    return ResponseEntity.ok(categorySerice.getAllCategories());
    }
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable("id") Long categoryId,@RequestBody CategoryDto categoryDto){
        return ResponseEntity.ok(categorySerice.updateCategory(categoryDto,categoryId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> getDeleteCategory(@PathVariable("id") Long categorytId) {
        categorySerice.deleteCategory(categorytId);
        return ResponseEntity.ok("Delete Category");
    }
}
