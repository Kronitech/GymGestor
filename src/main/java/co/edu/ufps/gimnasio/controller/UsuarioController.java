package co.edu.ufps.gimnasio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.ufps.gimnasio.model.entity.Usuario;
import co.edu.ufps.gimnasio.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
@CrossOrigin
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping
	public ResponseEntity<?>lista(){
		try {
			return ResponseEntity.ok(usuarioService.listaUsuarios());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error listar usuarios");
		}
	}
	
	@PreAuthorize("hasAnyRole('ADMIN' , 'RECEPCIONISTA')")
	@PostMapping("/save/cliente")
	public ResponseEntity<?>saveCliente(@RequestBody Usuario usuario){
		try {
			return ResponseEntity.ok(usuarioService.saveCliente(usuario));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error al registrar el cliente");
			
		}
		
	}
	@PreAuthorize("hasAnyRole('ADMIN' , 'RECEPCIONISTA')")
	@PutMapping("/update/{id}")
	public ResponseEntity<?>updateCliente(@PathVariable Integer id, @RequestBody Usuario usuario){
		try {
			return ResponseEntity.ok(usuarioService.updateByIdUsuario(id,usuario));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error al registrar el cliente");
			
		}
		
	}
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping("/save/recepcionista")
	public ResponseEntity<?>saveRecepcionista(@RequestBody Usuario usuario){
		try {
			return ResponseEntity.ok(usuarioService.saveRecepcionista(usuario));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error para registrar recepcionista");
			
		}
		
	}
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping("/save/entrenador")
	public ResponseEntity<?>saveEntrenador(@RequestBody Usuario usuario){
		try {
			return ResponseEntity.ok(usuarioService.saveEntrenador(usuario));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error para registrar entrenador");
			
		}
		
	}
	

}
