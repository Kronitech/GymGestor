package co.edu.ufps.gimnasio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.ufps.gimnasio.service.EntrenadorService;

@RestController
@RequestMapping("/entrenador")
@CrossOrigin
public class EntrenadorController {
	
	@Autowired
	EntrenadorService entrenadorService;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findByIdEntrenador(@PathVariable Integer id){
		try {
			return ResponseEntity.ok(entrenadorService.findByIdEntrenador(id));
			
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro el entrenador");
		}
		
	}
	

}
