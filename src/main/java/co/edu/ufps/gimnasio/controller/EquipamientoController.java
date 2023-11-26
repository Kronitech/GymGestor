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

import co.edu.ufps.gimnasio.model.entity.Equipamiento;
import co.edu.ufps.gimnasio.service.EquipamientoService;

@RestController
@RequestMapping("/equipamiento")
@CrossOrigin

public class EquipamientoController {
	
	@Autowired
	EquipamientoService equipamientoService;
	
	
	@GetMapping
	public ResponseEntity<?>listaEquipamientos(){
		try {
			return ResponseEntity.ok(equipamientoService.equipamientos());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No se encontraron equipamientos");
		}
	}
	@GetMapping("/{id}")
	public ResponseEntity<?>findByIdEquipamientos(@PathVariable Integer id){
		try {
			return ResponseEntity.ok(equipamientoService.findByIdEquipamiento(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No se encontro equipamiento");
		}
	}
	@PreAuthorize("hasAnyRole('ADMIN' , 'ENTRENADOR')")
	@PostMapping("/save")
	public ResponseEntity<?>saveEquipamientos(@RequestBody Equipamiento equipamiento){
		try {
			return ResponseEntity.ok(equipamientoService.saveEquipamiento(equipamiento));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No se guardo equipamiento");
		}
	}
	@PreAuthorize("hasAnyRole('ADMIN' , 'ENTRENADOR')")
	@PutMapping("/update")
	public ResponseEntity<?>updateEquipamientos(@RequestBody Equipamiento equipamiento){
		try {
			return ResponseEntity.ok(equipamientoService.updateByIdEquipamiento(equipamiento));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No se actualizo equipamiento");
		}
	}
	@PreAuthorize("hasAnyRole('ADMIN' , 'ENTRENADOR')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?>deleteEquipamientos(@PathVariable Integer id){
		try {
			equipamientoService.deleteEquipamiento(id);
			return ResponseEntity.ok("Eliminado");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No se actualizo equipamiento");
		}
	}
	

}
