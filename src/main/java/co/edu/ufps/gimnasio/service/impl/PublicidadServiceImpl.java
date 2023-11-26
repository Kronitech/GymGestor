package co.edu.ufps.gimnasio.service.impl;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import co.edu.ufps.gimnasio.model.dto.UsuarioDTO;
import co.edu.ufps.gimnasio.model.entity.Publicidad;
import co.edu.ufps.gimnasio.repository.PublicidadRepository;
import co.edu.ufps.gimnasio.service.AWSS3Service;
import co.edu.ufps.gimnasio.service.PublicidadService;

@Service
public class PublicidadServiceImpl implements PublicidadService {
	@Autowired
	PublicidadRepository publicidadRepository;
	@Autowired
	AWSS3Service awss3Service;
	private static final String ruta="/publcidad/";

	@Override
	public InputStream downloadImagenPerfil(String key) {
		
		
		return awss3Service.downloadFile(ruta, key);
	}

	@Override
	public Publicidad createFolderByIdUsuario(MultipartFile file) {
		
		String key=awss3Service.createFolderFile(ruta, file);
		Publicidad publicidad=new Publicidad();
		publicidad.setFoto(key);
		return publicidadRepository.save(publicidad) ;
	}

	@Override
	public List<Publicidad> listaPublicidad() {
		
		return publicidadRepository.findAll();
	}

	@Override
	public boolean deleteBykey(String key) {
		Optional<Publicidad>publicidad=publicidadRepository.findByFoto(key);
		if(publicidad.isPresent()) {
			
			publicidadRepository.deleteById(publicidad.get().getId());
			return awss3Service.deleteFile(ruta, key);
			
		}
		
		return false;
	}

}
