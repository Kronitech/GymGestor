package co.edu.ufps.gimnasio.service;

import java.util.List;

import co.edu.ufps.gimnasio.model.entity.EjercicioEquipamiento;

public interface EjercicioEquipamientoService {
	
	public List<EjercicioEquipamiento>lista();
	public EjercicioEquipamiento findById(Integer id);
	public EjercicioEquipamiento saveEjercicio(EjercicioEquipamiento ejercicioEquipamiento);
	public EjercicioEquipamiento updateEjercicioEquipamiento(EjercicioEquipamiento ejercicioEquipamiento);
	public EjercicioEquipamiento deleteEjercicioEquipamiento(Integer id);

}
