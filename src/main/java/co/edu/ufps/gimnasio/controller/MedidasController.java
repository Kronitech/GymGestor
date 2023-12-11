package co.edu.ufps.gimnasio.controller;

import java.util.List;

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

import co.edu.ufps.gimnasio.model.entity.Medidas;
import co.edu.ufps.gimnasio.service.MedidasService;

@RestController
@RequestMapping("/medidas")
@CrossOrigin
public class MedidasController {
	
	@Autowired
	MedidasService medidasService;
	
	@GetMapping("/{id}/usuario")
	public ResponseEntity<List<Medidas>> listaMedidasUsuario(@PathVariable Integer id){
		try {
			return ResponseEntity.ok(medidasService.mediasUsuario(id));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		}
		
		
	}
	@PostMapping("/save")
	public ResponseEntity<?> saveMedias(@RequestBody Medidas medias){
		try {
			return ResponseEntity.ok(medidasService.saveMedidas(medias));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("No se guardo la medida del usuario");
		}
	}

}
