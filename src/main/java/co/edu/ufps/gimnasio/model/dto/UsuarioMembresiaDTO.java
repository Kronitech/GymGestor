package co.edu.ufps.gimnasio.model.dto;

import java.util.List;

import co.edu.ufps.gimnasio.model.entity.UsuarioMembresia;
import lombok.Data;
@Data
public class UsuarioMembresiaDTO {
	
	private UsuarioDTO usuario;
	private List<UsuarioMembresia> usuarioMembresias;

}
