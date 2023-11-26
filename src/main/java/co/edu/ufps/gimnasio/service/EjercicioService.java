package co.edu.ufps.gimnasio.service;

import java.util.List;

import co.edu.ufps.gimnasio.model.dto.EjercicioDTO;
import co.edu.ufps.gimnasio.model.entity.Ejercicio;

public interface EjercicioService {
	
	public List<Ejercicio>listaEjercicios();
	public Ejercicio findByIdEjercicio(Integer id);
	public Ejercicio saveEjercicio(EjercicioDTO ejercicio);
	public Ejercicio updateEjercicio(EjercicioDTO ejercicio);
	public Ejercicio deleteEjercicio(Integer id);

}
