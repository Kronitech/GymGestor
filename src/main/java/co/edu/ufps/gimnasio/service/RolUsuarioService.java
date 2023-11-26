package co.edu.ufps.gimnasio.service;

import java.util.List;

import co.edu.ufps.gimnasio.model.dto.RolUsuarioDTO;
import co.edu.ufps.gimnasio.model.entity.Usuario;

public interface RolUsuarioService {
	
	public List<RolUsuarioDTO>listaRol(Integer id);
	

}
