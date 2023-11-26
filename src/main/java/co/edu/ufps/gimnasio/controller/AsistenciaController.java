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

import co.edu.ufps.gimnasio.model.entity.Asistencia;
import co.edu.ufps.gimnasio.service.AsistenciaService;

@RestController
@RequestMapping("/asistencia")
@CrossOrigin
public class AsistenciaController {
	@Autowired
	AsistenciaService asistenciaService;
	
	@GetMapping
	public ResponseEntity<?> lista(){
		try {
			return ResponseEntity.ok(asistenciaService.lista());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Error obtener asistencias");
		}
	}
	@GetMapping("/datos")
	public ResponseEntity<?> asistentenDia(){
		try {
			return ResponseEntity.ok(asistenciaService.datosAsistencia());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Error obtener asistencias");
		}
	}
	@GetMapping("/registrada/{id}")
	public ResponseEntity<?> asistenciaRegistrada(@PathVariable Integer id){
		try {
			return ResponseEntity.ok(asistenciaService.asistenciaRegistrada(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Error obtener asistencias");
		}
	}
	
	@PostMapping("/save")
	public ResponseEntity<?> saveAsistencia(@RequestBody Asistencia asistencia){
		try {
			return ResponseEntity.ok(asistenciaService.saveAsistencia(asistencia));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Error obtener asistencias");
		}
	}
	

}
