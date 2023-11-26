package co.edu.ufps.gimnasio.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.gimnasio.model.entity.EjercicioEquipamiento;
import co.edu.ufps.gimnasio.repository.EjercicioEquipamientoRepository;
import co.edu.ufps.gimnasio.service.EjercicioEquipamientoService;
@Service
public class EjercicioEquipamientoServiceImpl implements EjercicioEquipamientoService{
	@Autowired
	EjercicioEquipamientoRepository ejercicioEquipamientoRepository;
	@Autowired
	

	@Override
	public List<EjercicioEquipamiento> lista() {
		return ejercicioEquipamientoRepository.findAll();
	}

	@Override
	public EjercicioEquipamiento findById(Integer id) {
		Optional<EjercicioEquipamiento>ejercicio=ejercicioEquipamientoRepository.findById(id);
		if(ejercicio.isPresent()) {
			return ejercicio.get();
		}
		return null;
	}

	@Override
	public EjercicioEquipamiento saveEjercicio(EjercicioEquipamiento ejercicioEquipamiento) {
		
		return ejercicioEquipamientoRepository.save(ejercicioEquipamiento);
	}

	@Override
	public EjercicioEquipamiento updateEjercicioEquipamiento(EjercicioEquipamiento ejercicioEquipamiento) {
		
		return null;
	}

	@Override
	public EjercicioEquipamiento deleteEjercicioEquipamiento(Integer id) {
		
		Optional<EjercicioEquipamiento>ejercicio=ejercicioEquipamientoRepository.findById(id);
		if(ejercicio.isPresent()) {
			ejercicioEquipamientoRepository.deleteById(id);
			return ejercicio.get();
		}
		return null;
	}

}
