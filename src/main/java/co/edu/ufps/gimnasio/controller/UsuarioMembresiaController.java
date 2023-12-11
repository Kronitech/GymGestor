package co.edu.ufps.gimnasio.controller;

import java.io.FileNotFoundException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.ufps.gimnasio.model.entity.UsuarioMembresia;
import co.edu.ufps.gimnasio.service.UsuarioMembresiaService;
import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping("/usuario/membresia")
@CrossOrigin
public class UsuarioMembresiaController {

	@Autowired
	UsuarioMembresiaService usuarioMembresiaService;

	@PreAuthorize("hasAnyRole('ADMIN' , 'RECEPCIONISTA')")
	@GetMapping
	public ResponseEntity<?> lista() {
		try {
			return ResponseEntity.ok(usuarioMembresiaService.lista());
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No se encontro listado");
		}
	}

	@PreAuthorize("hasAnyRole('ADMIN' , 'RECEPCIONISTA','CLIENTE')")
	@GetMapping("/activa/{usuarioId}")
	public ResponseEntity<?> membresiasActivas(@PathVariable Integer usuarioId) {
		try {
			return ResponseEntity.ok(usuarioMembresiaService.membresiasActivas(usuarioId));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No se encontro listado");
		}
	}
	@PreAuthorize("hasAnyRole('ADMIN' , 'RECEPCIONISTA','CLIENTE')")
	@PutMapping("/update/{id}")
	public ResponseEntity<?> membresiasUpdate(@RequestBody UsuarioMembresia usuarioMembresia,@PathVariable Integer id) {
		try {
			return ResponseEntity.ok(usuarioMembresiaService.updateUsuarioMembresia(usuarioMembresia,id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No se encontro listado");
		}
	}

	@PreAuthorize("hasAnyRole('ADMIN' , 'ENTRENADOR')")
	@GetMapping("/activa/{usuarioId}/entrenador")
	public ResponseEntity<?> membresiasActivasEntrenador(@PathVariable Integer usuarioId) {
		try {
			return ResponseEntity.ok(usuarioMembresiaService.listaClientesEntrenador(usuarioId));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No se encontro listado");
		}
	}

	@PreAuthorize("hasAnyRole('ADMIN' , 'RECEPCIONISTA','CLIENTE')")
	@GetMapping("/cedula/{cedula}")
	public ResponseEntity<?> usuarioMembresiaFindByCedula(@PathVariable String cedula) {
		try {
			return ResponseEntity.ok(usuarioMembresiaService.findByCedulaUsuario(cedula));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No se encontro listado");
		}
	}

	@PreAuthorize("hasAnyRole('ADMIN' , 'RECEPCIONISTA')")
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Integer id) {
		try {
			return ResponseEntity.ok(usuarioMembresiaService.findById(id));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No se encontro membresia");
		}
	}

	@PreAuthorize("hasAnyRole('ADMIN' , 'RECEPCIONISTA')")
	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody UsuarioMembresia usuarioMembresia) {
		try {
			return ResponseEntity.ok(usuarioMembresiaService.saveUsuarioMembresia(usuarioMembresia));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No se encontro membresia");
		}
	}

	@PreAuthorize("hasAnyRole('ADMIN' , 'RECEPCIONISTA')")
	@GetMapping(value = "/informe/{fechaInicio}/{fechaFin}", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> listaInforme(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaInicio,
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFin)
			throws JRException, FileNotFoundException {
		// Genera el PDF
		byte[] pdf = usuarioMembresiaService.informeMembresias(fechaInicio, fechaFin);
		// Devuelve el PDF como una respuesta HTTP
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF);
		headers.setContentDispositionFormData("attachment", "documento.pdf");
		headers.setContentLength(pdf.length);

		return new ResponseEntity<>(pdf, headers, HttpStatus.OK);

	}
	@PreAuthorize("hasAnyRole('ADMIN' , 'RECEPCIONISTA')")
	@GetMapping(value = "/comprobante/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> listaInforme(@PathVariable Integer id)
			throws JRException, FileNotFoundException {
		// Genera el PDF
		byte[] pdf = usuarioMembresiaService.comprobantePago(id);
		// Devuelve el PDF como una respuesta HTTP
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF);
		headers.setContentDispositionFormData("attachment", "documento.pdf");
		headers.setContentLength(pdf.length);

		return new ResponseEntity<>(pdf, headers, HttpStatus.OK);

	}

	@GetMapping("/export-pdf")
	public ResponseEntity<byte[]> exportPdf() throws JRException, FileNotFoundException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF);
		headers.setContentDispositionFormData("petsReport", "petsReport.pdf");
		return ResponseEntity.ok().headers(headers).body(usuarioMembresiaService.informeMembresias());
	}
	
}
