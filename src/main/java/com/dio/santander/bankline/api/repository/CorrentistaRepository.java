package com.dio.santander.bankline.api.repository;

import com.dio.santander.bankline.api.models.Correntista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CorrentistaRepository extends JpaRepository<Correntista, Integer> {
}
