package com.isite.todotest.todo;

import com.isite.todotest.registration.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TodoService {
    @Autowired
    TodoRepository todoRepository;

    public Iterable<TodoEntity> forUser(UserEntity user) {
        return todoRepository.findAllByUser(user);
    }

    public TodoEntity create(String name, UserEntity user) {
        TodoEntity todoEntity = new TodoEntity();
        todoEntity.setName(name);
        todoEntity.setUser(user);

        return todoRepository.save(todoEntity);
    }

    public Optional<TodoEntity> findOne(Long id) {
        Optional<TodoEntity> todo = todoRepository.findById(id);
        return todo;
    }

    public TodoEntity delete(TodoEntity todo, UserEntity user) {
        if (todo.getUser().getId() == user.getId())
            todoRepository.delete(todo);
        return todo;
    }
}
