package co.edu.ufps.gimnasio.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.gimnasio.model.entity.Entrenamiento;
import co.edu.ufps.gimnasio.repository.EntrenamientoRepository;
import co.edu.ufps.gimnasio.service.EntrenamientoService;
@Service

public class EntrenamientoServiceImpl  implements EntrenamientoService{
	
	@Autowired
	EntrenamientoRepository entrenamientoRepository;
	

	@Override
	public List<Entrenamiento> entrenamientos() {
		return entrenamientoRepository.findAll();
	}

	@Override
	public Entrenamiento saveEntrenamiento(Entrenamiento entrenamiento) {
		entrenamiento.setFechaRegistro(new Date());
		System.err.println(entrenamiento.toString());
		return entrenamientoRepository.save(entrenamiento);
	}

	@Override
	public Entrenamiento updateEntrenamiento(Entrenamiento entrenamiento) {
		// TODO Auto-generated method stub
		return null;
	}

}
