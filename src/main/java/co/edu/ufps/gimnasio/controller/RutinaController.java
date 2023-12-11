package co.edu.ufps.gimnasio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.ufps.gimnasio.model.dto.RutinaDTO;
import co.edu.ufps.gimnasio.model.entity.Rutina;
import co.edu.ufps.gimnasio.service.RutinaService;

@RestController
@RequestMapping("/rutina")
@CrossOrigin
public class RutinaController {
	
	@Autowired
	RutinaService rutinaService;
	
	@GetMapping
	public ResponseEntity<?> findAll(){
		try {
			return ResponseEntity.ok(rutinaService.finAllRutinas());
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro la lista");
		}
	}
	@PostMapping("/save")
	public ResponseEntity<?> saveRutina(@RequestBody RutinaDTO rutina){
		try {
			return ResponseEntity.ok(rutinaService.saveRutina(rutina));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro la lista");
		}
	}
	@PutMapping("/update")
	public ResponseEntity<?> updateRutina(@RequestBody RutinaDTO rutina){
		try {
			return ResponseEntity.ok(rutinaService.updateRutina(rutina));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro la lista");
		}
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> updateRutina(@PathVariable Integer id){
		try {
			return ResponseEntity.ok(rutinaService.deleteRutina(id));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se elimino la rutina");
		}
	}
	

}
