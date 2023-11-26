package co.edu.ufps.gimnasio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import co.edu.ufps.gimnasio.model.entity.Corporativo;
import co.edu.ufps.gimnasio.service.CorporativoService;

@RestController
@RequestMapping("/corporativo")
@CrossOrigin
public class CorporativoController {
	@Autowired
	CorporativoService corporativoService;
	
	
	@GetMapping
	public ResponseEntity<?> getCorporativo(){
		try {
			return ResponseEntity.ok(corporativoService.getCorporativo());
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No se encontro la informacion");
		}
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateCorporativo(@RequestBody Corporativo corporativo){
		try {
			return ResponseEntity.ok(corporativoService.updateCorporativo(corporativo));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No se actualizo la informacion");
		}
	}
	@GetMapping(value = "/download/logo")
	public ResponseEntity<Resource> downloadImagenPublcidad(
			@RequestParam("key") String key) {
		InputStreamResource resource = new InputStreamResource(
				corporativoService.downloadLogo( key));
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + key + "\"")
				.body(resource);
	}
	@PostMapping("/upload/logo")
	public ResponseEntity<?> uploadImagenPublicidad(
			@RequestPart(value = "file") MultipartFile file) {
		try {

			return ResponseEntity.ok(corporativoService.createFolderByLogo( file));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		}
	}
	@GetMapping(value = "/download/horario")
	public ResponseEntity<Resource> downloadImagenHorario(
			@RequestParam("key") String key) {
		InputStreamResource resource = new InputStreamResource(
				corporativoService.downloadHorario( key));
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + key + "\"")
				.body(resource);
	}
	@PostMapping("/upload/horario")
	public ResponseEntity<?> uploadImagenHorario(
			@RequestPart(value = "file") MultipartFile file) {
		try {

			return ResponseEntity.ok(corporativoService.createFolderByHorario( file));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		}
	}

}
