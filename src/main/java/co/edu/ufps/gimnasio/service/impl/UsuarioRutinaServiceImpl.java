package co.edu.ufps.gimnasio.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.gimnasio.model.entity.UsuarioRutina;
import co.edu.ufps.gimnasio.repository.UsuarioRutinaRepository;
import co.edu.ufps.gimnasio.service.UsuarioRutinaService;
@Service
public class UsuarioRutinaServiceImpl implements UsuarioRutinaService {
	
	@Autowired
	UsuarioRutinaRepository usuarioRutinaRepository;
	
	
	@Override
	public List<UsuarioRutina> listaRutinasUsuario(Integer id) {
		return usuarioRutinaRepository.findByUsuarioId(id);
	}

	@Override
	public UsuarioRutina saveUsuarioRutina(UsuarioRutina usuarioRutina) {
//		if(usuarioRegistradoRutina(usuarioRutina.getUsuarioId(), usuarioRutina.getRutinaId())) {
			usuarioRutina.setFechaRegistro(new Date());
			return usuarioRutinaRepository.save(usuarioRutina);
//		}
//		return null;
	}

	@Override
	public boolean usuarioRegistradoRutina(Integer usuarioId, Integer rutinaId) {
	
		return usuarioRegistradoRutina(usuarioId, rutinaId);
	}

}
