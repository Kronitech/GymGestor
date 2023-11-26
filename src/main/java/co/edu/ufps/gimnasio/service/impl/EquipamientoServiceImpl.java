package co.edu.ufps.gimnasio.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.gimnasio.model.entity.Equipamiento;
import co.edu.ufps.gimnasio.repository.EquipamientoRepository;
import co.edu.ufps.gimnasio.service.EquipamientoService;

@Service
public class EquipamientoServiceImpl implements EquipamientoService{

	@Autowired
	EquipamientoRepository equipamientoRepository;
	
	@Override
	public List<Equipamiento> equipamientos() {
		return equipamientoRepository.findAll();
	}

	@Override
	public Equipamiento findByIdEquipamiento(Integer id) {
		Optional<Equipamiento>equipamiento=equipamientoRepository.findById(id);
		if(equipamiento.isPresent()) {
			return equipamiento.get();
		}
		return null;
	}

	@Override
	public Equipamiento saveEquipamiento(Equipamiento equipamiento) {
		equipamiento.setNombre(equipamiento.getNombre().toUpperCase());
		return equipamientoRepository.save(equipamiento);
	}

	@Override
	public Equipamiento updateByIdEquipamiento(Equipamiento equipamiento) {
		Optional<Equipamiento>equipamientoCurrent=equipamientoRepository.findById(equipamiento.getId());
		if(equipamientoCurrent.isPresent()) {
			Equipamiento equipamientoReturn=equipamientoCurrent.get();
			equipamientoReturn.setNombre(equipamiento.getNombre());
			return equipamientoRepository.save(equipamientoReturn);
		}
		return null;
	}

	@Override
	public void deleteEquipamiento(Integer id) {
		Optional<Equipamiento>equipamiento=equipamientoRepository.findById(id);
		if(equipamiento.isPresent()) {
			equipamientoRepository.deleteById(id);
		}
		
	}

}
