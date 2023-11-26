package co.edu.ufps.gimnasio.service;

import java.util.List;

import co.edu.ufps.gimnasio.model.dto.UsuarioMembresiaDTO;
import co.edu.ufps.gimnasio.model.entity.UsuarioMembresia;

public interface UsuarioMembresiaService {
	
	public List<UsuarioMembresia>lista();
	
	public UsuarioMembresia findById(Integer id);
	
	public UsuarioMembresia saveUsuarioMembresia(UsuarioMembresia usuarioMembresia);
	
	public UsuarioMembresia updateUsuarioMembresia(UsuarioMembresia usuarioMembresia,Integer id);
	
	public void delete(Integer id);
	
	public List<UsuarioMembresia>membresiasActivas(Integer id);
	
	public UsuarioMembresiaDTO findByCedulaUsuario(String cedula);

}
