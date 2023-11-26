package co.edu.ufps.gimnasio.service.impl;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import co.edu.ufps.gimnasio.model.entity.Corporativo;
import co.edu.ufps.gimnasio.repository.CorporativoRepository;
import co.edu.ufps.gimnasio.service.AWSS3Service;
import co.edu.ufps.gimnasio.service.CorporativoService;

@Service
public class CorporativoServiceImpl implements CorporativoService {
	
	@Autowired
	CorporativoRepository corporativoRepository;
	
	@Autowired
	AWSS3Service awss3Service;
	
	private static final String ruta="/corporativo/";

	@Override
	public Corporativo getCorporativo() {
		List<Corporativo>corporativo=corporativoRepository.findAll();
		if(!corporativo.isEmpty()) {
			return corporativo.get(0);
		}
		return null;
	}

	@Override
	public Corporativo updateCorporativo(Corporativo corporativo) {
		Optional<Corporativo>corporativoCurrent=corporativoRepository.findById(corporativo.getId());
		if(corporativoCurrent.isPresent()) {
			Corporativo corporativoReturn=corporativoCurrent.get();
			corporativoReturn.setEmail(corporativo.getEmail());
			corporativoReturn.setNombre(corporativo.getNombre());
			corporativoReturn.setTelefono(corporativo.getTelefono());
			corporativoReturn.setUbicacion(corporativo.getUbicacion());
			corporativoReturn.setAforo(corporativo.getAforo());;
			return corporativoRepository.save(corporativoReturn);
		}
		return null;
	}

	@Override
	public InputStream downloadLogo(String key) {
		
		return awss3Service.downloadFile(ruta, key);
	}

	@Override
	public Corporativo createFolderByLogo(MultipartFile file) {
		List<Corporativo>corporativo=corporativoRepository.findAll();
		if(!corporativo.isEmpty()) {
			Corporativo cor=corporativo.get(0);
			if(cor.getLogo()!=null) {
				awss3Service.deleteFile(ruta, cor.getLogo());
			}
			String logo=awss3Service.createFolderFile(ruta, file);
			cor.setLogo(logo);
			return corporativoRepository.save(cor);
		}
		return null;
	}

	@Override
	public InputStream downloadHorario(String key) {
		
		return awss3Service.downloadFile(ruta, key);
	}

	@Override
	public Corporativo createFolderByHorario(MultipartFile file) {
		List<Corporativo>corporativo=corporativoRepository.findAll();
		if(!corporativo.isEmpty()) {
			Corporativo cor=corporativo.get(0);
			if(cor.getHorario()!=null) {
				awss3Service.deleteFile(ruta, cor.getHorario());
			}
			String horario=awss3Service.createFolderFile(ruta, file);
			cor.setHorario(horario);
			return corporativoRepository.save(cor);
		}
		return null;
	}

}
