package com.abcrestaurant.admin.category;

import com.abcrestaurant.admin.FileUploadUtil;
import com.abcrestaurant.common.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String listAll(Model model) {
        List<Category> listCategories = categoryService.listAll();
        model.addAttribute("listCategories", listCategories);
        return "categories/categories"; // Your Thymeleaf template for the list
    }

    @GetMapping("/new")
    public String newCategory(Model model) {
        Category category = new Category();
        model.addAttribute("category", category);
        model.addAttribute("pageTitle", "Create New Category");
        return "categories/category_form"; // Form to create a new category
    }

    @PostMapping("/save")
    public String saveCategory(@ModelAttribute("category") Category category,
                               @RequestParam("fileImage") MultipartFile multipartFile) throws IOException {
        if (!multipartFile.isEmpty()) {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            category.setImage(fileName);
            Category savedCategory = categoryService.save(category);

            String uploadDir = "ABCWebParent/ABCBackEnd/src/main/resources/static/images/category-images/" + savedCategory.getId();
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        } else {
            categoryService.save(category);
        }

        return "redirect:/categories";
    }

    @GetMapping("/edit/{id}")
    public String editCategory(@PathVariable("id") Integer id, Model model) {
        Category category = categoryService.get(id);
        model.addAttribute("category", category);
        model.addAttribute("pageTitle", "Edit Category (ID: " + id + ")");
        return "categories/category_form"; // Form to edit a category
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") Integer id) {
        categoryService.delete(id);
        return "redirect:/categories";
    }
}
