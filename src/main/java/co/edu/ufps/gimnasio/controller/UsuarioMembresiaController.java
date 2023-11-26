package co.edu.ufps.gimnasio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.ufps.gimnasio.model.entity.UsuarioMembresia;
import co.edu.ufps.gimnasio.service.UsuarioMembresiaService;

@RestController
@RequestMapping("/usuario/membresia")
@CrossOrigin
public class UsuarioMembresiaController {
	
	@Autowired
	UsuarioMembresiaService usuarioMembresiaService;
	
	
	@PreAuthorize("hasAnyRole('ADMIN' , 'RECEPCIONISTA')")
	@GetMapping
	public ResponseEntity<?> lista(){
		try {
			return ResponseEntity.ok(usuarioMembresiaService.lista());
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No se encontro listado");
		}
	}
	@PreAuthorize("hasAnyRole('ADMIN' , 'RECEPCIONISTA','CLIENTE')")
	@GetMapping("/activa/{usuarioId}")
	public ResponseEntity<?> membresiasActivas (@PathVariable Integer usuarioId){
		try {
			return ResponseEntity.ok(usuarioMembresiaService.membresiasActivas(usuarioId));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No se encontro listado");
		}
	}
	@PreAuthorize("hasAnyRole('ADMIN' , 'RECEPCIONISTA','CLIENTE')")
	@GetMapping("/cedula/{cedula}")
	public ResponseEntity<?> usuarioMembresiaFindByCedula (@PathVariable String cedula){
		try {
			return ResponseEntity.ok(usuarioMembresiaService.findByCedulaUsuario(cedula));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No se encontro listado");
		}
	}
	@PreAuthorize("hasAnyRole('ADMIN' , 'RECEPCIONISTA')")
	@GetMapping("/{id}")
	public ResponseEntity<?>findById(@PathVariable Integer id){
		try {
			return ResponseEntity.ok(usuarioMembresiaService.findById(id));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No se encontro membresia");
		}
	}
	@PreAuthorize("hasAnyRole('ADMIN' , 'RECEPCIONISTA')")
	@PostMapping("/save")
	public ResponseEntity<?>save(@RequestBody UsuarioMembresia usuarioMembresia){
		try {
			return ResponseEntity.ok(usuarioMembresiaService.saveUsuarioMembresia(usuarioMembresia));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No se encontro membresia");
		}
	}
	

}
