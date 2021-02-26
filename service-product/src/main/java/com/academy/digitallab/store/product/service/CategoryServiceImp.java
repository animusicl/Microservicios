package com.academy.digitallab.store.product.service;

import com.academy.digitallab.store.product.model.Category;
import com.academy.digitallab.store.product.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService {


    private final CategoryRepository categoryRepository;
    public CategoryServiceImp(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List <Category> listAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategory(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category category) {
        Category categoryDB = getCategory(category.getId());
        categoryDB.setName(category.getName());

        if (categoryDB == null){
            return null;
        }
        return categoryRepository.save(categoryDB);
    }

    @Override
    public Category deleteCategory(Long id) { //I need to change this for an optional probably
        Category categoryDB = getCategory(id);      //or make it void
        categoryRepository.delete(categoryDB);
        return categoryDB;
    }
}
