package co.edu.ufps.gimnasio.service;

import java.util.List;

import co.edu.ufps.gimnasio.model.entity.Equipamiento;

public interface EquipamientoService {
	
	public List<Equipamiento>equipamientos();
	public Equipamiento findByIdEquipamiento(Integer id);
	public Equipamiento saveEquipamiento(Equipamiento equipamiento);
	public Equipamiento updateByIdEquipamiento(Equipamiento equipamiento);
	public void deleteEquipamiento(Integer id);
	

}
