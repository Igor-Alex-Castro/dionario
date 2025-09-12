package com.dionariao.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dionariao.dto.AddPalavraDto;
import com.dionariao.model.Palavra;
import com.dionariao.service.PalavaService;
import com.dionariao.service.impl.PalavraServiceImpl;

@RestController
@RequestMapping("/palavra")
public class PalavraController {

	
	private final PalavaService palavaService;

	public PalavraController(PalavaService palavaService) {
		
		this.palavaService = palavaService;
	}
	
	
	@PostMapping
	public ResponseEntity<String> addPalavra(@RequestBody AddPalavraDto addPalavraDto){
		
		Palavra palavra = null;
		
		
		palavra = palavaService.addPalavra(addPalavraDto);
	
		
		return ResponseEntity.ok(palavra.getNome());
	}
	
	
	
	
	@GetMapping()
	public ResponseEntity<Palavra> findByIdAndDicionarioId(@RequestParam(required = false) Long idPalavra, @RequestParam(required = false) Long idDicionario){
		
			Palavra palavra = null;
	
			palavra = palavaService.findByIdAndDicionarioId(idPalavra, idDicionario);
	
		
			return ResponseEntity.ok(palavra);
	}
	
	@DeleteMapping()
	public ResponseEntity<String> deletePalavra(@RequestParam Long idPalavra){
		
		Palavra palavra = palavaService.deleteById(idPalavra);
		
		return ResponseEntity.ok( "A Palavra " + palavra.getNome() + " foi deletada com sucesso" );
	}
}
