package com.abcrestaurant.admin.user;

import com.abcrestaurant.common.entity.Role;
import com.abcrestaurant.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> listAll() {
        return (List<User>) userRepository.findAll();
    }

    public List<Role> listRoles() {
        return (List<Role>) roleRepository.findAll();
    }

    public User save(User user) {
        boolean isUpdatingUser = (user.getId() != null);

        if (isUpdatingUser) {
            User existingUser = userRepository.findById(user.getId()).get();

            if (user.getPassword().isEmpty()) {
                user.setPassword(existingUser.getPassword());
            } else {
                encodePassword(user);
            }
        } else {
            encodePassword(user);
        }
        return userRepository.save(user);
    }

    private void encodePassword(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }

    public boolean isEmailUnique(Integer id, String email) {
        User userByEmail = userRepository.getUserByEmail(email);

        if (userByEmail == null) return true;

        boolean isCreatingNew = (id == null);

        if (isCreatingNew) {
            if (userByEmail != null) return false;
        } else {
            if (!userByEmail.getId().equals(id)) {
                return false;
            }
        }

        return true;
    }

    public User get(Integer id) throws UserNotFoundException {
        try {
            return userRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new UserNotFoundException("Could not find any user with ID " + id);
        }
    }

    public void delete(Integer id) throws UserNotFoundException {
        Long countById = userRepository.countById(id);
        if (countById == null || countById == 0) {
            throw new UserNotFoundException("Could not find any user with ID " + id);
        }
        userRepository.deleteById(id);
    }

    public void updateUserActiveStatus(Integer id, boolean active) {
        userRepository.updateActiveStatus(id, active);
    }
}

