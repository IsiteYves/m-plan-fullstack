package com.isite.todotest.todo;

import com.isite.todotest.registration.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<TodoEntity, Long> {
    Iterable<TodoEntity> findAllByUser(UserEntity user);
}
