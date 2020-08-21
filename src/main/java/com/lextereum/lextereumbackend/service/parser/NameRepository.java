package com.lextereum.lextereumbackend.service.parser;

import com.lextereum.lextereumbackend.model.Name;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NameRepository extends JpaRepository<Name, Long> {
    Name findByName(String name);
    boolean existsByName(String name);
}
