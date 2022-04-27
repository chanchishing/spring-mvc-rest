package guru.springframework.services;

import guru.springframework.api.v1.mapper.CategoryMapper;
import guru.springframework.api.v1.model.CategoryDTO;
import guru.springframework.domain.Category;
import guru.springframework.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class CategoryServiceImplTest {

    private AutoCloseable closeable;

    CategoryService categoryService;

    @Mock
    CategoryRepository mockCategoryRepository;

    @BeforeEach
    void setUp() {
        closeable= MockitoAnnotations.openMocks(this);
        categoryService= new CategoryServiceImpl(CategoryMapper.INSTANCE,mockCategoryRepository);
    }

    @Test
    void getAllCategories() {
        Category cat1=new Category();
        Category cat2=new Category();
        List<Category> mockCategoryList= new ArrayList<>(Arrays.asList(cat1,cat2));

        when(mockCategoryRepository.findAll()).thenReturn(mockCategoryList);

        List<CategoryDTO> categoryDTOList=categoryService.getAllCategories();

        assertEquals(mockCategoryList.size(),categoryDTOList.size());


    }

    @Test
    void getCategoryByName() {
        final Long testId1= 77L;
        final String testName1="test name 77";

        Category cat1=new Category();
        cat1.setId(testId1);
        cat1.setName(testName1);

        when(mockCategoryRepository.findByName(anyString())).thenReturn(cat1);

        CategoryDTO categoryDTO=categoryService.getCategoryByName(testName1);

        assertEquals(testId1,categoryDTO.getId());
        assertEquals(testName1,categoryDTO.getName());
    }
}