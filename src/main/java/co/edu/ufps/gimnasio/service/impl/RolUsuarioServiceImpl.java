package co.edu.ufps.gimnasio.service.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.gimnasio.model.dto.RolUsuarioDTO;
import co.edu.ufps.gimnasio.model.entity.RolUsuario;
import co.edu.ufps.gimnasio.repository.RolUsuarioRepository;
import co.edu.ufps.gimnasio.service.RolUsuarioService;

@Service
public class RolUsuarioServiceImpl implements RolUsuarioService {
	
	@Autowired
	RolUsuarioRepository rolUsuarioRepository;
	@Autowired
	ModelMapper modelMapper;

	@Override
	public List<RolUsuarioDTO> listaRol(Integer id) {
		
			List<RolUsuario>lista=rolUsuarioRepository.findByRolId(id);
			List<RolUsuarioDTO>listaUsuarios=modelMapper.map(lista, new TypeToken<List<RolUsuarioDTO>>() {}.getType());
			
			return listaUsuarios;
		
		
	}

}
