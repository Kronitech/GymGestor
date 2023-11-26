package co.edu.ufps.gimnasio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import co.edu.ufps.gimnasio.service.PublicidadService;

@RestController
@RequestMapping("/publicidad")
@CrossOrigin
public class PublicidadController {
	
	@Autowired
	PublicidadService publicidadService;
	
	@GetMapping
	public ResponseEntity<?>lista(){
		try {
			return ResponseEntity.ok(publicidadService.listaPublicidad());
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay publicidad");
		}
	}
	
	@PostMapping("/upload")
	public ResponseEntity<?> uploadImagenPublicidad(
			@RequestPart(value = "file") MultipartFile file) {
		try {

			return ResponseEntity.ok(publicidadService.createFolderByIdUsuario( file));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		}
	}

	@GetMapping(value = "/download")
	public ResponseEntity<Resource> downloadImagenPublcidad(
			@RequestParam("key") String key) {
		InputStreamResource resource = new InputStreamResource(
				publicidadService.downloadImagenPerfil( key));
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + key + "\"")
				.body(resource);
	}
	
	@DeleteMapping("/delete/{key}")
	public ResponseEntity<?>lista(@PathVariable String key){
		try {
			return ResponseEntity.ok(publicidadService.deleteBykey(key));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay publicidad");
		}
	}
	

}
