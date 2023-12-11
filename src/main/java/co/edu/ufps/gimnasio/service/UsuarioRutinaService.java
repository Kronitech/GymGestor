package co.edu.ufps.gimnasio.service;

import java.util.List;

import co.edu.ufps.gimnasio.model.entity.UsuarioRutina;

public interface UsuarioRutinaService {
	
	public List<UsuarioRutina>listaRutinasUsuario(Integer id);
	public UsuarioRutina saveUsuarioRutina(UsuarioRutina usuarioRutina);
	public boolean usuarioRegistradoRutina(Integer usuarioId,Integer rutinaId);
	

}
