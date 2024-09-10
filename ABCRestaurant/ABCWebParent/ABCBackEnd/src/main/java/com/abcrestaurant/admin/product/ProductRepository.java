package com.abcrestaurant.admin.product;

import com.abcrestaurant.common.entity.Category;
import com.abcrestaurant.common.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
    Long countById(Integer id);

    List<Product> findByCategory(Category category);
}
