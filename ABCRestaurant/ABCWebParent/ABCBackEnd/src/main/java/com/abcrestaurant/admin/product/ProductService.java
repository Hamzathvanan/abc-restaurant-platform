package com.abcrestaurant.admin.product;

import com.abcrestaurant.common.entity.Category;
import com.abcrestaurant.common.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> listAll() {
        return (List<Product>) productRepository.findAll();
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public Product get(Integer id) throws ProductNotFoundException {
        Optional<Product> result = productRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new ProductNotFoundException("Could not find any product with ID " + id);
    }

    public void delete(Integer id) throws ProductNotFoundException {
        Long countById = productRepository.countById(id);
        if (countById == null || countById == 0) {
            throw new ProductNotFoundException("Could not find any product with ID " + id);
        }
        productRepository.deleteById(id);
    }

    public List<Product> listByCategory(Category category) {
        return productRepository.findByCategory(category);
    }


}
