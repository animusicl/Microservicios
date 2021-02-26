package com.academy.digitallab.store.product.service;

import com.academy.digitallab.store.product.model.Category;
import java.util.List;

public interface CategoryService {

    public List <Category> listAllCategories();
    public Category getCategory(Long id);
    public Category createCategory(Category category);
    public Category updateCategory(Category category);
    public Category deleteCategory(Long id);

}
