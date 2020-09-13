package com.lextereum.lextereumbackend.repositories;

import com.lextereum.lextereumbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserById(String id);
    boolean existsById(String id);
}
