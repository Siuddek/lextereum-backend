package com.lextereum.lextereumbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellAgreementRepository extends JpaRepository<SellAgreement, Long> {
    boolean existsById(String id);
}
