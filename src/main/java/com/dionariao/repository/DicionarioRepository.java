package com.dionariao.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dionariao.model.Dicionario;


@Repository
public interface DicionarioRepository  extends JpaRepository<Dicionario, Long> {
	
	Optional<Dicionario> findByNome(String nomeDicionario);
	 
	
	
}
