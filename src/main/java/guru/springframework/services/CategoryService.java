package guru.springframework.services;

import guru.springframework.api.v1.model.CategoryDTO;
import guru.springframework.api.v1.model.CategoryListDTO;

import java.util.List;

public interface CategoryService {
    CategoryListDTO getAllCategories();
    CategoryDTO getCategoryByName(String name);
}
