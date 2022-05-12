package guru.springframework.controllers.v1;

import guru.springframework.api.v1.model.CategoryDTO;
import guru.springframework.api.v1.model.CategoryListDTO;
import guru.springframework.controllers.RestResponseEntityExceptionHandler;
import guru.springframework.exceptions.ResourceNotFoundException;
import guru.springframework.services.CategoryService;
import org.hibernate.annotations.CollectionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import guru.springframework.api.v1.model.Constant;

class CategoryControllerTest {

    private AutoCloseable closeable;

    CategoryController categoryController;

    @Mock
    private CategoryService mockCategoryService;
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        categoryController = new CategoryController(mockCategoryService);
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    void getAllCategories() throws Exception {
        CategoryDTO cat1DTO=new CategoryDTO();
        CategoryDTO cat2DTO=new CategoryDTO();
        Long cat1ID=1L;
        Long cat2ID=2L;
        String cat1Name="Cat 1 Name";
        String cat2Name="Cat 2 Name";
        cat1DTO.setId(cat1ID);
        cat1DTO.setName(cat1Name);
        cat2DTO.setId(cat2ID);
        cat2DTO.setName(cat2Name);

        CategoryListDTO categoryDTOList= new CategoryListDTO(Arrays.asList(cat1DTO,cat2DTO));

        when(mockCategoryService.getAllCategories()).thenReturn(categoryDTOList);

        categoryController.getAllCategories();

        mockMvc.perform(get(Constant.API_V_1_CATEGORIES_URL).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categories", hasSize(2)))
                .andExpect(jsonPath("$.categories[0].id",equalTo(cat1ID.intValue())));
        ;

    }

    @Test
    void getCategoryByName() throws Exception {
        CategoryDTO cat1 = new CategoryDTO();
        Long cat1ID=1L;
        String cat1Name="Cat 1 Name";
        cat1.setId(cat1ID);
        cat1.setName(cat1Name);

        when(mockCategoryService.getCategoryByName(anyString())).thenReturn(cat1);

        mockMvc.perform(get(Constant.API_V_1_CATEGORIES_URL+"/anyString").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(cat1ID.intValue())))
                .andExpect(jsonPath("$.name", equalTo(cat1Name)));
    }

    @Test
    public void testGetByNameNotFound() throws Exception {
        String cat1Name="Cat 1 Name";

        when(mockCategoryService.getCategoryByName(anyString())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get(Constant.API_V_1_CATEGORIES_URL + "/"+cat1Name)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}