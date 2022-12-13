package com.isite.todotest.todo;

import com.isite.todotest.Application;
import com.isite.todotest.registration.UserEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})
@WebAppConfiguration
public class TodoControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    TodoController todoController;

    @Mock
    TodoService todoService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(todoController).build();
    }

    @Test
    public void list() throws Exception {
        List<TodoEntity> todos = new ArrayList<>();
        when(todoService.forUser(any(UserEntity.class))).thenReturn(todos);

        mockMvc.perform(
                get("/todo-list")
        )
                .andExpect(model().attribute("todos", equalTo(todos)))
                .andExpect(view().name("todo/list"));
    }

    @Test
    public void add() throws Exception {
        mockMvc.perform(
                post("/todo-list")
                        .param("name", "Take out the garbage")
        )
                .andExpect(flash().attribute("alertSuccess", "Todo created"))
                .andExpect(redirectedUrl("/todo-list"));

        verify(todoService).create(eq("Take out the garbage"), any(UserEntity.class));
    }

    @Test
    public void complete() throws Exception {
        TodoEntity todo = new TodoEntity();
        todo.setName("Take out the garbage");

        when(todoService.findOne(13L)).thenReturn(Optional.of(todo));

        mockMvc.perform(
                delete("/todo-list/13")
        )
                .andExpect(status().isFound())
                .andExpect(flash().attribute("alertSuccess", "Take out the garbage successfully completed"))
                .andExpect(redirectedUrl("/todo-list"));

        verify(todoService).delete(eq(todo), any(UserEntity.class));
    }
}
