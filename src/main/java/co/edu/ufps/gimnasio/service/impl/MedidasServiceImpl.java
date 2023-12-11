package co.edu.ufps.gimnasio.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.apigateway.model.Op;

import co.edu.ufps.gimnasio.model.entity.Medidas;
import co.edu.ufps.gimnasio.model.entity.Usuario;
import co.edu.ufps.gimnasio.repository.MedidasRepository;
import co.edu.ufps.gimnasio.repository.UsuarioReporitory;
import co.edu.ufps.gimnasio.service.MedidasService;

@Service
public class MedidasServiceImpl implements MedidasService {

	
	
	@Autowired
	MedidasRepository medidasRepository;
	@Autowired
	UsuarioReporitory usuarioReporitory;

	@Override
	public List<Medidas> mediasUsuario(Integer id) {
		Optional<Usuario>user=usuarioReporitory.findById(id);
		if(user.isPresent()) {
			return medidasRepository.findByUsuario(user.get());
		}
		return null;
		
	}

	@Override
	public Medidas saveMedidas(Medidas medidas) {
		Date fecha=new Date();
		medidas.setFechaRegistro(fecha);
		return medidasRepository.save(medidas);
	}

	@Override
	public Medidas updateMedias(Medidas medidas) {
		// TODO Auto-generated method stub
		return null;
	}
}
