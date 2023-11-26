package co.edu.ufps.gimnasio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.ufps.gimnasio.model.dto.EjercicioDTO;
import co.edu.ufps.gimnasio.model.entity.Ejercicio;
import co.edu.ufps.gimnasio.service.EjercicioService;

@RestController
@RequestMapping("/ejercicio")
@CrossOrigin
public class EjercicioController {
	
	@Autowired
	EjercicioService ejercicioService;
	
	@GetMapping
	public ResponseEntity<?> lista(){
		try {
			return ResponseEntity.ok(ejercicioService.listaEjercicios());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No se encontro los ejercicios");
		}
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Integer id){
		try {
			return ResponseEntity.ok(ejercicioService.findByIdEjercicio(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No se encontro el ejercicio");
		}
	}
	@PreAuthorize("hasAnyRole('ADMIN' , 'ENTRENADOR')")
	@PostMapping("/save")
	public ResponseEntity<?> saveEjercicio(@RequestBody EjercicioDTO ejercicio){
		try {
			return ResponseEntity.ok(ejercicioService.saveEjercicio(ejercicio));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No se guardo el ejercicio");
		}
	}
	@PreAuthorize("hasAnyRole('ADMIN' , 'ENTRENADOR')")
	@PutMapping("/update")
	public ResponseEntity<?> updateEjercicio(@RequestBody EjercicioDTO ejercicio){
		try {
			return ResponseEntity.ok(ejercicioService.updateEjercicio(ejercicio));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No se guardo el ejercicio");
		}
	}
	@PreAuthorize("hasAnyRole('ADMIN' , 'ENTRENADOR')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteEjercicio(@PathVariable Integer id){
		try {
			return ResponseEntity.ok(ejercicioService.deleteEjercicio(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No se encontro el ejercicio");
		}
	}
	
	
	

}
