package co.edu.ufps.gimnasio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.ufps.gimnasio.model.entity.CodigoRecuperacion;
import co.edu.ufps.gimnasio.model.entity.Usuario;
import co.edu.ufps.gimnasio.service.CodigoRecuperacionService;

@RestController
@RequestMapping("/codigo/recuperar")
@CrossOrigin
public class CodigoRecuperacionController {
	
	@Autowired
	CodigoRecuperacionService codigoRecuperacionService;
	
	@PostMapping("/email/{email}")
	public ResponseEntity<?> validarCodigo(@PathVariable String email,@RequestBody CodigoRecuperacion codigoRecuperacion){
		try {
			return ResponseEntity.ok(codigoRecuperacionService.verificarCodigo(codigoRecuperacion, email));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Error para validar  el codigo ");
			// TODO: handle exception
		}
	}
	@PostMapping("/codigo/{id}")
	public ResponseEntity<?> validarCodigo(@PathVariable Integer id,@RequestBody Usuario usuario){
		try {
			return ResponseEntity.ok(codigoRecuperacionService.cambiarPassword(id, usuario));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Error para validar  el cambio ");
			// TODO: handle exception
		}
	}

}
