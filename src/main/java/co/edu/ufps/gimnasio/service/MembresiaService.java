package co.edu.ufps.gimnasio.service;

import java.util.List;

import co.edu.ufps.gimnasio.model.entity.Membresia;

public interface MembresiaService {
	
	public List<Membresia>membresias();
	
	public List<Membresia>membresiasActivas();
	
	public Membresia finByIdMembresia(Integer id);
	
	public Membresia saveMembresia(Membresia membresia);
	
	public Membresia updateMembresia(Membresia membresia,Integer id);
	
	public void deleteById(Integer id);

}
