package com.lambdaschool.todos.repository;

import com.lambdaschool.todos.models.User;
import com.lambdaschool.todos.views.UserCountTodos;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long>

{
    @Query(value = "SELECT u.username as username, COUNT(*) as count FROM users u JOIN todos t ON u.userid = t.userid GROUP BY u.username ORDER BY u.username",
            nativeQuery = true)
    List<UserCountTodos> getUserTodos();

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM todos WHERE userid = :userid",
            nativeQuery = true)
    void deleteUserTodos(long userid);

}
