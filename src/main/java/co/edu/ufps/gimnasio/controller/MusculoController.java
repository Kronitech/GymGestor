package co.edu.ufps.gimnasio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.ufps.gimnasio.model.entity.Musculo;
import co.edu.ufps.gimnasio.service.MusculoService;

@RestController
@RequestMapping("/musculo")
@CrossOrigin

public class MusculoController {

	@Autowired
	MusculoService musculoService;
	
	@GetMapping
	public ResponseEntity<?>lista(){
		try {
			return ResponseEntity.ok(musculoService.lista());
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("error buscando musculos");
			// TODO: handle exception
		}
	}
	@PostMapping("/save")
	public ResponseEntity<?>lista(@RequestBody Musculo musculo){
		try {
			return ResponseEntity.ok(musculoService.saveMusculo(musculo));
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("error buscando musculos");
			// TODO: handle exception
		}
	}
}
