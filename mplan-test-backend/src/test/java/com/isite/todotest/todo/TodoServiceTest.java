package com.isite.todotest.todo;

import com.isite.todotest.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})
@WebAppConfiguration
public class TodoServiceTest {

    @InjectMocks
    TodoService todoService;

    @Mock
    TodoRepository todoRepository;

    @Test
    public void testForUser() throws Exception {

    }

    @Test
    public void testCreate() throws Exception {

    }

    @Test
    public void testFindOne() throws Exception {

    }

    @Test
    public void testMarkAsComplete() throws Exception {

    }
}
