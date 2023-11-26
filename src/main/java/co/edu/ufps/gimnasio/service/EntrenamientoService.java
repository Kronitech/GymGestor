package co.edu.ufps.gimnasio.service;

import java.util.List;

import co.edu.ufps.gimnasio.model.entity.Entrenamiento;

public interface EntrenamientoService {
	List<Entrenamiento>entrenamientos();
	Entrenamiento saveEntrenamiento(Entrenamiento entrenamiento);
	Entrenamiento updateEntrenamiento(Entrenamiento entrenamiento);

}
