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

import co.edu.ufps.gimnasio.model.entity.Usuario;
import co.edu.ufps.gimnasio.service.MailService;



@RestController
@RequestMapping("/mail")
@CrossOrigin
public class EmailController {
	
	@Autowired
	MailService mailService;
	
	@PreAuthorize("hasAnyRole('ADMIN' , 'RECEPCIONISTA')")
	@PostMapping("/new/{id}")
	public ResponseEntity<?> correoBienvenida(@PathVariable Integer id){
		
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(mailService.usuarioNuevo(id));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		
	}
	@PreAuthorize("hasAnyRole('ADMIN' , 'RECEPCIONISTA')")
	@PostMapping("/comprobante/{id}")
	public ResponseEntity<?> correoComprobanteMembresia(@PathVariable Integer id){
		
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(mailService.membresiaNueva(id));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		
	}
	
	@GetMapping("/recuperar/{email}")
	public ResponseEntity<?> correoBienvenida(@PathVariable String email){
		
		try {
			if(mailService.recuperarClave(email)) {
				return ResponseEntity.ok(true);
			}else {
				return ResponseEntity.status(HttpStatus.CONFLICT).body("no se puedo enviar el email");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		
	}
	
	

}
