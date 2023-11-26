package co.edu.ufps.gimnasio.service;

import co.edu.ufps.gimnasio.model.dto.UsuarioDTO;
import co.edu.ufps.gimnasio.model.entity.CodigoRecuperacion;
import co.edu.ufps.gimnasio.model.entity.Usuario;

public interface CodigoRecuperacionService {
	public CodigoRecuperacion saveCodigoRecuperacion(CodigoRecuperacion codigoRecuperacion);
	
	public CodigoRecuperacion verificarCodigo(CodigoRecuperacion codigoRecuperacion,String email);
	public boolean cambiarPassword(Integer codigoId,UsuarioDTO usuario);
	 
	

}
