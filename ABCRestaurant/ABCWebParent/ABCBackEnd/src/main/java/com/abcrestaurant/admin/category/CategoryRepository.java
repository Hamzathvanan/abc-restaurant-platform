package com.abcrestaurant.admin.category;

import com.abcrestaurant.common.entity.Category;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends PagingAndSortingRepository<Category, Integer> {
    Category findByName(@Param("name") String name);
}
