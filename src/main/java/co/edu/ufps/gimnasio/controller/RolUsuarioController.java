package co.edu.ufps.gimnasio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.ufps.gimnasio.service.RolUsuarioService;

@RestController
@RequestMapping("/rol/usuario")
@CrossOrigin
public class RolUsuarioController {

	@Autowired
	RolUsuarioService rolUsuarioService;
	
	@PreAuthorize("hasAnyRole('ADMIN' , 'RECEPCIONISTA')")
	@GetMapping("/{id}")
	public ResponseEntity<?> listaUsuarioRol(@PathVariable Integer id){
		try {
			return ResponseEntity.ok(rolUsuarioService.listaRol(id));
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El rol no existe");
		}
	}
	@PreAuthorize("hasAnyRole('ADMIN' , 'CLIENTE')")
	@GetMapping("/entrenadores")
	public ResponseEntity<?> listaUsuarioEntrenador(){
		try {
			int id=3;
			return ResponseEntity.ok(rolUsuarioService.listaRol(id));
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El rol no existe");
		}
	}
	
}
