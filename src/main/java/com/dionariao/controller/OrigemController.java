package com.dionariao.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dionariao.dto.AddOrigemDto;
import com.dionariao.dto.PalavraDto;
import com.dionariao.model.Origem;
import com.dionariao.model.Palavra;
import com.dionariao.model.Significado;
import com.dionariao.service.OrigemService;
import com.dionariao.service.PalavaService;
import com.dionariao.service.SignificadoService;

@RestController
@RequestMapping("/origem")
public class OrigemController {

	
	private final OrigemService origemService;

	public OrigemController(OrigemService origemService) {
		
		this.origemService = origemService;
	}
	
	
	@PostMapping
	public ResponseEntity<Origem> Origem(@RequestBody AddOrigemDto addOrigemDto ){
		
		Origem origem = new Origem();
		try {
			origem  = origemService.addOrigem(addOrigemDto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ResponseEntity.ok(origem);
	}
	

	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteOrigem(@RequestParam Long idOrigem){
		
		origemService.deleteOrigem(idOrigem);
		
		return ResponseEntity.ok( "delete" );
	}
	
}
