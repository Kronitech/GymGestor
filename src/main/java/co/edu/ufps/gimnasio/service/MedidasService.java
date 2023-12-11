package co.edu.ufps.gimnasio.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import co.edu.ufps.gimnasio.model.entity.Medidas;

@Repository
public interface MedidasService {
	
	public List<Medidas>mediasUsuario(Integer id);
	
	public Medidas saveMedidas(Medidas medidas);
	
	public Medidas updateMedias(Medidas medidas);

}
