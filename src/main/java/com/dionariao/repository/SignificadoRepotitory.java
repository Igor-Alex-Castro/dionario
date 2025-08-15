package com.dionariao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dionariao.model.Significado;

@Repository
public interface SignificadoRepotitory extends JpaRepository<Significado, Long> {

	
}
