package co.edu.ufps.gimnasio.service;

import java.util.List;

import co.edu.ufps.gimnasio.model.dto.RutinaDTO;
import co.edu.ufps.gimnasio.model.entity.Rutina;

public interface RutinaService {
	
	public List<Rutina>finAllRutinas();
	public Rutina findById(Integer id);
	public Rutina saveRutina(RutinaDTO rutina);
	public Rutina updateRutina(RutinaDTO rutina);
	public Rutina deleteRutina(Integer id);

}
