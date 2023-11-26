package co.edu.ufps.gimnasio.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import co.edu.ufps.gimnasio.model.dto.UsuarioDTO;
import co.edu.ufps.gimnasio.model.entity.CodigoRecuperacion;
import co.edu.ufps.gimnasio.model.entity.Usuario;
import co.edu.ufps.gimnasio.repository.CodigoRecuperacionRepository;
import co.edu.ufps.gimnasio.repository.UsuarioReporitory;
import co.edu.ufps.gimnasio.service.CodigoRecuperacionService;

@Service
public class CodigoSeguridadServiceImpl implements CodigoRecuperacionService {

	@Autowired
	CodigoRecuperacionRepository codigoRecuperacionRepository;
	@Autowired
	UsuarioReporitory usuarioReporitory;
	

	@Override
	public CodigoRecuperacion saveCodigoRecuperacion(CodigoRecuperacion codigoRecuperacion) {

		return null;
	}

	@Override
	public CodigoRecuperacion verificarCodigo(CodigoRecuperacion codigoRecuperacion, String email) {

		Optional<Usuario> usuario = usuarioReporitory.findByEmail(email.toUpperCase());
		if (usuario.isPresent()) {
			List<CodigoRecuperacion> codigos = codigoRecuperacionRepository.findByUsuarioId(usuario.get().getId());
			System.err.println(codigos.size());
			if (!codigos.isEmpty()) {
				int size = codigos.size();
				if (codigos.get(size - 1).getCodigo().equals(codigoRecuperacion.getCodigo())) {
					CodigoRecuperacion codigoReturn = codigos.get(size - 1);
					codigoReturn.setEstado(true);
					return codigoRecuperacionRepository.save(codigoReturn);

				}else {
					//System.err.println(codigoRecuperacion.toString());
					return codigoRecuperacion;
				}
			} 
		}

		return null;
	}

	@Override
	public boolean cambiarPassword(Integer codigoId, Usuario usuario) {
		Optional<CodigoRecuperacion>codigo=codigoRecuperacionRepository.findById(codigoId);
		if(codigo.isPresent()) {
			Optional<Usuario> usuarioCurrent=usuarioReporitory.findById(codigo.get().getUsuarioId());
			if(usuarioCurrent.isPresent() ) {
				Usuario usuarioReturn=usuarioCurrent.get();
				usuarioReturn.setPassword(new BCryptPasswordEncoder().encode(usuario.getPassword()));
				usuarioReporitory.save(usuarioReturn);
				return true;
				
			}
		}
		return false;
	}

}
