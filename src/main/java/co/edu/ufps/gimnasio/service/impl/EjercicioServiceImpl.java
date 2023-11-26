package co.edu.ufps.gimnasio.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.gimnasio.model.dto.EjercicioDTO;
import co.edu.ufps.gimnasio.model.entity.Ejercicio;
import co.edu.ufps.gimnasio.model.entity.EjercicioEquipamiento;
import co.edu.ufps.gimnasio.model.entity.Equipamiento;
import co.edu.ufps.gimnasio.repository.EjercicioEquipamientoRepository;
import co.edu.ufps.gimnasio.repository.EjercicioRepository;
import co.edu.ufps.gimnasio.service.EjercicioEquipamientoService;
import co.edu.ufps.gimnasio.service.EjercicioService;

@Service
public class EjercicioServiceImpl implements EjercicioService {

	@Autowired
	EjercicioRepository ejercicioRepository;
	@Autowired
	EjercicioEquipamientoRepository ejercicioEquipamientoRepository;

	@Override
	public List<Ejercicio> listaEjercicios() {
		return ejercicioRepository.findAll();
	}

	@Override
	public Ejercicio findByIdEjercicio(Integer id) {
		Optional<Ejercicio> ejercicio = ejercicioRepository.findById(id);
		if (ejercicio.isPresent()) {
			return ejercicio.get();
		}
		return null;
	}

	@Override
	public Ejercicio saveEjercicio(EjercicioDTO ejercicio) {

		ModelMapper modelMapper = new ModelMapper();
		Ejercicio ejercicioCurrent = modelMapper.map(ejercicio, Ejercicio.class);
		ejercicioCurrent.setEquipamientos(null);
		Ejercicio ejercicioReturn = ejercicioRepository.save(ejercicioCurrent);
		List<Equipamiento> equipamientos = ejercicio.getEquipamientos();

		for (Equipamiento equipamiento : equipamientos) {

			EjercicioEquipamiento ejercicioEquipamiento = new EjercicioEquipamiento();
			ejercicioEquipamiento.setEjercicioId(ejercicioReturn.getId());
			ejercicioEquipamiento.setEquipamientoId(equipamiento.getId());
			ejercicioEquipamientoRepository.save(ejercicioEquipamiento);
		}

		return ejercicioReturn;
	}

	@Override
	public Ejercicio updateEjercicio(EjercicioDTO ejercicio) {
		Optional<Ejercicio> ejercicioCurrent = ejercicioRepository.findById(ejercicio.getId());
		if (ejercicioCurrent.isPresent()) {

			Ejercicio ejercicioReturn = ejercicioCurrent.get();
			ejercicioReturn.setNombre(ejercicio.getNombre());
			ejercicioReturn.setDescripcion(ejercicio.getDescripcion());
			ejercicioReturn.setInstrucciones(ejercicio.getInstrucciones());
			ejercicioReturn.setMusculaturaTrabajada(ejercicio.getMusculaturaTrabajada());
			ejercicioReturn.setTipoEjercicio(ejercicio.getTipoEjercicio());
			ejercicioReturn.setVideo(ejercicio.getVideo());

			List<Equipamiento> equipamientos = ejercicio.getEquipamientos();
			List<EjercicioEquipamiento> equipamientosOld = ejercicioEquipamientoRepository
					.findByEjercicioId(ejercicio.getId());
			
			// Crear una lista de IDs de los nuevos equipamientos
			List<Integer> nuevosEquipamientosIds = equipamientos.stream().map(Equipamiento::getId)
					.collect(Collectors.toList());

			// Crear una lista de IDs de los equipamientos antiguos
			List<Integer> equipamientosOldIds = equipamientosOld.stream().map(EjercicioEquipamiento::getEquipamientoId)
					.collect(Collectors.toList());

			// Identificar equipamientos a agregar (nuevos)
			List<Integer> equipamientosNuevos = nuevosEquipamientosIds.stream()
					.filter(id -> !equipamientosOldIds.contains(id)).collect(Collectors.toList());

			// Identificar equipamientos a eliminar (antiguos)
			List<Integer> equipamientosEliminar = equipamientosOldIds.stream()
					.filter(id -> !nuevosEquipamientosIds.contains(id)).collect(Collectors.toList());
			// Eliminar equipamientos antiguos
			for (Integer equipamientoId : equipamientosEliminar) {

				ejercicioEquipamientoRepository.deleteByEjercicioIdAndEquipamientoId(ejercicio.getId(), equipamientoId);
			}

			// Agregar nuevos equipamientos
			for (Integer equipamientoId : equipamientosNuevos) {
				EjercicioEquipamiento ejercicioEquipamiento = new EjercicioEquipamiento();
				ejercicioEquipamiento.setEjercicioId(ejercicioReturn.getId());
				ejercicioEquipamiento.setEquipamientoId(equipamientoId);
				ejercicioEquipamientoRepository.save(ejercicioEquipamiento);
			}
			
			return ejercicioRepository.save(ejercicioReturn);
		}
		return null;
	}

	@Override
	public Ejercicio deleteEjercicio(Integer id) {
		Optional<Ejercicio> ejercicio = ejercicioRepository.findById(id);
		if (ejercicio.isPresent()) {
			ejercicioRepository.deleteById(id);
			return ejercicio.get();

		}
		return null;
	}

}
