package co.edu.ufps.gimnasio.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.gimnasio.model.entity.Membresia;
import co.edu.ufps.gimnasio.repository.MembresiaRepository;
import co.edu.ufps.gimnasio.service.MembresiaService;

@Service
public class MembresiaServiceImpl implements MembresiaService{
	
	@Autowired
	MembresiaRepository membresiaRepository;

	@Override
	public List<Membresia> membresias() {
		return membresiaRepository.findAll();
	}

	@Override
	public Membresia finByIdMembresia(Integer id) {
		Optional<Membresia>membresia=membresiaRepository.findById(id);
		if(membresia.isPresent()) {
			return membresia.get();
		}
		return null;
	}

	@Override
	public Membresia saveMembresia(Membresia membresia) {
		
		
		return membresiaRepository.save(membresia);
	}

	@Override
	public Membresia updateMembresia(Membresia membresia, Integer id) {
		Optional<Membresia>membresiaCurrent=membresiaRepository.findById(id);
		if(membresiaCurrent.isPresent()) {
			Membresia membresiaReturn=membresiaCurrent.get();
			membresiaReturn.setDescripcion(membresia.getDescripcion());
			membresiaReturn.setDuracion(membresia.getDuracion());
			membresiaReturn.setNombre(membresia.getNombre());
			membresiaReturn.setPrecio(membresia.getPrecio());
			return membresiaRepository.save(membresiaReturn);
			
		}
		return null;
	}

	@Override
	public void deleteById(Integer id) {
		Optional<Membresia>membresia=membresiaRepository.findById(id);
		if(membresia.isPresent()) {
			membresiaRepository.deleteById(id);
		}
		
	}

}
