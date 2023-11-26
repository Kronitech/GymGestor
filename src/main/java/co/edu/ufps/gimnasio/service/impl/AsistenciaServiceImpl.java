package co.edu.ufps.gimnasio.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.gimnasio.model.entity.Asistencia;
import co.edu.ufps.gimnasio.repository.AsistenciaRepository;
import co.edu.ufps.gimnasio.service.AsistenciaService;

@Service
public class AsistenciaServiceImpl implements AsistenciaService {

	@Autowired
	AsistenciaRepository asistenciaRepository;

	@Override
	public List<Asistencia> lista() {
		return asistenciaRepository.findAll();
	}

	@Override
	public boolean asistenciaRegistrada(Integer usuario) {
		Date fecha = new Date();
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		String fechaFormateada = formato.format(fecha);
		return asistenciaRepository.asistenciaRegistrada(usuario, fechaFormateada);
	}

	@Override
	public Asistencia saveAsistencia(Asistencia asistencia) {
		Date fecha = new Date();
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		String fechaFormateada = formato.format(fecha);
		asistencia.setFechaRegistro(fechaFormateada);
		return asistenciaRepository.save(asistencia);
	}

	@Override
	public Asistencia updateAsistencia(Asistencia asistencia) {

		return null;
	}

	@Override
	public List<Integer> datosAsistencia() {
		Date fecha = new Date();
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		String fechaFormateada = formato.format(fecha);
		Integer[] horas = { 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 };
		List<Integer> asistentes = new ArrayList<>();
		List<Asistencia>asistencias=asistenciaRepository.findByFechaRegistro(fechaFormateada);
		// Inicializa el array de asistentes con ceros
	
		for (int i = 0; i < horas.length; i++) {
			
			
			int n=asistenciaRepository.contarRegistrosPorFechaYHora(fechaFormateada, horas[i]);
			
			asistentes.add(n);
		}

		return (asistentes);
	}

}
