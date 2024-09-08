package com.abcrestaurant.admin.category;

import com.abcrestaurant.common.entity.Category;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoryRepository extends PagingAndSortingRepository<Category, Integer> {
    Category findByName(String name);
}
