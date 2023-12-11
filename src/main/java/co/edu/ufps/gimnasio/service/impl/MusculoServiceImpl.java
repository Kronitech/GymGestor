package co.edu.ufps.gimnasio.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.gimnasio.model.entity.Musculo;
import co.edu.ufps.gimnasio.repository.MusculoRepository;
import co.edu.ufps.gimnasio.service.MusculoService;

@Service
public class MusculoServiceImpl  implements MusculoService{
	
	@Autowired
	MusculoRepository musculoRepository;
	@Override
	public List<Musculo> lista() {
		// TODO Auto-generated method stub
		return musculoRepository.findAll();
	}

	@Override
	public Musculo saveMusculo(Musculo musculo) {
		musculo.setNombre(musculo.getNombre().toUpperCase());
		return musculoRepository.save(musculo);
	}

	@Override
	public Musculo updateMusculo(Musculo musculo) {
		// TODO Auto-generated method stub
		return null;
	}

}
