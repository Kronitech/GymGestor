package co.edu.ufps.gimnasio.service;

import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

import co.edu.ufps.gimnasio.model.entity.Corporativo;

public interface CorporativoService {
	
	public Corporativo getCorporativo();
	
	public Corporativo updateCorporativo(Corporativo corporativo);
	
	public InputStream downloadLogo( String key);

	public Corporativo createFolderByLogo(MultipartFile file);
	
	public InputStream downloadHorario( String key);

	public Corporativo createFolderByHorario(MultipartFile file);
	
	

}
