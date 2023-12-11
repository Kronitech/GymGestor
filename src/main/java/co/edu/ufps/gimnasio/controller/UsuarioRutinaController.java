package co.edu.ufps.gimnasio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.ufps.gimnasio.model.entity.UsuarioRutina;
import co.edu.ufps.gimnasio.service.UsuarioRutinaService;

@RestController
@RequestMapping("/usuario/rutina")
@CrossOrigin
public class UsuarioRutinaController {
	@Autowired
	UsuarioRutinaService usuarioRutinaService;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> listaUsuarioRutinas(@PathVariable Integer id){
		try {
			return ResponseEntity.ok(usuarioRutinaService.listaRutinasUsuario(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ocurrio un error ");
			// TODO: handle exception
		}
	}
	@PostMapping("/save")
	public ResponseEntity<?> saveUsuarioRutinas(@RequestBody UsuarioRutina usuarioRutina){
		try {
			return ResponseEntity.ok(usuarioRutinaService.saveUsuarioRutina(usuarioRutina));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ocurrio un error ");
			// TODO: handle exception
		}
	}

}
