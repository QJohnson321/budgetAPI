package org.example.budget_tracker;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryControllerTest {

    @Test
    void getAll_returnsCategories() {
        CategoryRepository mockRepo = Mockito.mock(CategoryRepository.class);
        CategoryController controller = new CategoryController(mockRepo);

        when(mockRepo.findAll()).thenReturn(List.of(new Category("Food")));

        List<Category> result = controller.getAll();

        assertEquals(1, result.size());
        assertEquals("Food", result.get(0).getName());
    }

    @Test
    void create_blankName_throwsException() {
        CategoryRepository mockRepo = Mockito.mock(CategoryRepository.class);
        CategoryController controller = new CategoryController(mockRepo);

        Category badCategory = new Category("");

        assertThrows(RuntimeException.class, () -> controller.create(badCategory));
    }
}