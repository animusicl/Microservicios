package com.academy.digitallab.store.product.controller;

import com.academy.digitallab.store.product.model.Category;
import com.academy.digitallab.store.product.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity <Category> createCategory(@RequestBody Category newCategory) {
        return ResponseEntity.ok(categoryService.createCategory(newCategory));
    }

    @GetMapping
    public ResponseEntity <List<Category>> getAllCategories(){
       return ResponseEntity.ok(categoryService.listAllCategories());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Category> updateCategory (
            @PathVariable("id") Long id,
            @RequestBody Category category) {
        category.setId(id);
        Category categoryDB = categoryService.updateCategory(category);
        return (categoryDB == null) ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok(categoryDB);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity <Category> deleteCategory (@PathVariable("id") Long id) {
        Category categoryToDelete = categoryService.getCategory(id);
        return (categoryToDelete == null) ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok(categoryService.deleteCategory(id));
    }


}
