package co.edu.ufps.gimnasio.service;

import java.util.List;

import co.edu.ufps.gimnasio.model.entity.Musculo;

public interface MusculoService {
	
	public List<Musculo>lista();
	public Musculo saveMusculo(Musculo musculo);
	public Musculo updateMusculo(Musculo musculo);

}
