package co.edu.ufps.gimnasio.service;

import java.io.InputStream;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import co.edu.ufps.gimnasio.model.dto.UsuarioDTO;
import co.edu.ufps.gimnasio.model.entity.Publicidad;

public interface PublicidadService {
	
	
	public List<Publicidad>listaPublicidad();
	
	public InputStream downloadImagenPerfil( String key);

	public Publicidad createFolderByIdUsuario(MultipartFile file);
	
	public boolean deleteBykey(String key);
	

}
