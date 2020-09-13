package com.lextereum.lextereumbackend.repositories;

import com.lextereum.lextereumbackend.model.Name;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NameRepository extends JpaRepository<Name, Long> {
    boolean existsByName(String name);
}
