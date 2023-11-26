package co.edu.ufps.gimnasio.service.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.gimnasio.model.dto.UsuarioDTO;
import co.edu.ufps.gimnasio.model.entity.Entrenador;
import co.edu.ufps.gimnasio.model.entity.Usuario;
import co.edu.ufps.gimnasio.repository.EntrenadorRepository;
import co.edu.ufps.gimnasio.repository.UsuarioReporitory;
import co.edu.ufps.gimnasio.service.EntrenadorService;
@Service
public class EntrenadorServiceImpl  implements EntrenadorService{
	
	@Autowired
	UsuarioReporitory usuarioReporitory;
	@Autowired
	EntrenadorRepository entrenadorRepository;
	@Autowired
	ModelMapper modelMapper;

	@Override
	public UsuarioDTO findByIdEntrenador(Integer id) {
		
		Optional<Entrenador>entrenador=entrenadorRepository.findById(id);
		if(entrenador.isPresent()) {
			Optional<Usuario>usuario=usuarioReporitory.findById(entrenador.get().getUsuarioId());
			return modelMapper.map(usuario.get(), UsuarioDTO.class);
		}
		
		return null;
	}

}
