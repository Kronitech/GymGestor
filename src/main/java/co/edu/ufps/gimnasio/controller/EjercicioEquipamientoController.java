package co.edu.ufps.gimnasio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.ufps.gimnasio.model.entity.EjercicioEquipamiento;
import co.edu.ufps.gimnasio.service.EjercicioEquipamientoService;

@RestController
@RequestMapping("/ejercicio/equipamiento")
@CrossOrigin
public class EjercicioEquipamientoController {
	
	@Autowired
	EjercicioEquipamientoService ejercicioEquipamientoService;
	
	@GetMapping
	public ResponseEntity<?>lista(){
		try {
			return ResponseEntity.ok(ejercicioEquipamientoService.lista());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron ejercicios");
		}
	}
	@GetMapping("/{id}")
	public ResponseEntity<?>findById(@PathVariable  Integer id){
		try {
			return ResponseEntity.ok(ejercicioEquipamientoService.findById(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron ejercicios");
		}
	}
	@PostMapping("/save")
	public ResponseEntity<?>saveEjercicioEquipamiento(@RequestBody EjercicioEquipamiento ejercicioEquipamiento){
		try {
			return ResponseEntity.ok(ejercicioEquipamientoService.saveEjercicio(ejercicioEquipamiento));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron ejercicios");
		}
	}
	@DeleteMapping("/delete/id")
	public ResponseEntity<?>deleteFindById(@PathVariable Integer id){
		try {
			return ResponseEntity.ok(ejercicioEquipamientoService.deleteEjercicioEquipamiento(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron ejercicios");
		}
	}

}
