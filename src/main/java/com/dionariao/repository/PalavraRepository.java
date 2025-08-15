package com.dionariao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dionariao.model.Palavra;

@Repository
public interface PalavraRepository extends JpaRepository<Palavra, Long>{

	
	List<Palavra> findByDicionarioId(Long idDicionario);
	
	Optional<Palavra> findByIdAndDicionarioId(Long idPalavra, Long idDicionario);
}
