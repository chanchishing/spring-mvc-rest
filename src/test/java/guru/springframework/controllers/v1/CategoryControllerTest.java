package guru.springframework.controllers.v1;

import guru.springframework.services.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;

class CategoryControllerTest {

    private AutoCloseable closeable;
    CategoryController categoryController;

    @Mock
    private CategoryService categoryService;
    @Mock
    private Model mockModel;
    MockMvc mockMvc;

    public CategoryControllerTest(CategoryController categoryController) {
        this.categoryController = categoryController;
    }

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController)
                //.setControllerAdvice(new ControllerExceptionHandler())
                .build();
    }

    @Test
    void getAllCategories() {

        categoryController.getAllCategories();
    }

    @Test
    void getCategoryByName() {
    }
}