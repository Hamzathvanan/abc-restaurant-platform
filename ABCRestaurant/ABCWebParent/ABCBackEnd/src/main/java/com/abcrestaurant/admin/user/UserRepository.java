package com.abcrestaurant.admin.user;

import com.abcrestaurant.common.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
