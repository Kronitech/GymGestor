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

import co.edu.ufps.gimnasio.model.entity.Membresia;
import co.edu.ufps.gimnasio.service.MembresiaService;

@RestController
@RequestMapping("/membresia")
@CrossOrigin
public class MembresiaController {
	
	@Autowired
	MembresiaService membresiaService;
	
	
	@GetMapping
	public ResponseEntity<?>lista(){
		try {
			return ResponseEntity.ok(membresiaService.membresias());
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Error listar membresias");
		}
	}
	@GetMapping("/activas")
	public ResponseEntity<?>listaActivas(){
		try {
			return ResponseEntity.ok(membresiaService.membresiasActivas());
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Error listar membresias");
		}
	}
	@GetMapping("/{id}")
	public ResponseEntity<?>findById(@PathVariable Integer id){
		try {
			return ResponseEntity.ok(membresiaService.finByIdMembresia(id));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Error encontrar membresia");
		}
	}
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping("/save")
	public ResponseEntity<?>saveMembresia(@RequestBody Membresia membresia){
		try {
			return ResponseEntity.ok(membresiaService.saveMembresia(membresia));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Error guardar membresia");
		}
	}
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping("/{id}/update")
	public ResponseEntity<?>updateMembresia(@RequestBody Membresia membresia,@PathVariable Integer id){
		try {
			return ResponseEntity.ok(membresiaService.updateMembresia(membresia,id));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Error actualizar membresia");
		}
	}
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<?>deleteById(@PathVariable Integer id){
		try {
			membresiaService.deleteById(id);
			return ResponseEntity.ok(true);
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Error encontrar membresia");
		}
	}

}
