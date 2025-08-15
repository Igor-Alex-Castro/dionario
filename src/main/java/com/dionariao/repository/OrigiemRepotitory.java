package com.dionariao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dionariao.model.Origem;

@Repository
public interface OrigiemRepotitory extends JpaRepository<Origem, Long> {

	
}
