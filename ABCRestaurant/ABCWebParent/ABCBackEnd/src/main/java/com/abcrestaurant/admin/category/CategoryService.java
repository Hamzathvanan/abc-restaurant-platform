package com.abcrestaurant.admin.category;

import com.abcrestaurant.common.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> listAll() {
        return (List<Category>) categoryRepository.findAll();
    }

    public Category save(Category category) {
        categoryRepository.save(category);
        return category;
    }

    public Category get(Integer id) {
        return categoryRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Could not find any category with ID " + id));
    }

    public void delete(Integer id) {
        categoryRepository.deleteById(id);
    }
}
