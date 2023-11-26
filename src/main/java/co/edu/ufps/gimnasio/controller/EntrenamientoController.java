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

import co.edu.ufps.gimnasio.model.entity.Entrenamiento;
import co.edu.ufps.gimnasio.service.EntrenamientoService;

@RestController
@RequestMapping("/entrenamiento")
@CrossOrigin
public class EntrenamientoController {
	@Autowired
	EntrenamientoService entrenamientoService;
	
	@GetMapping
	public ResponseEntity<?> listar(){
		try {
			return ResponseEntity.ok(entrenamientoService.entrenamientos());
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error en obtener la lista de entrenamientos");
		}
	}
	@PostMapping("/save")
	public ResponseEntity<?> saveEntrenamiento(@RequestBody Entrenamiento entrenamiento){
		try {
			return ResponseEntity.ok(entrenamientoService.saveEntrenamiento(entrenamiento));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error en guardar el entrenamiento");
		}
	}
	
	
	

}
