package com.lextereum.lextereumbackend.repositories;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellAgreementRepository extends JpaRepository<SellAgreement, String> {
    boolean existsById(@NotNull String id);
}
