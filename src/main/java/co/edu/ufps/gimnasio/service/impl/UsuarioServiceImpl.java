package co.edu.ufps.gimnasio.service.impl;

import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import co.edu.ufps.gimnasio.model.dto.UsuarioDTO;
import co.edu.ufps.gimnasio.model.entity.Entrenador;
import co.edu.ufps.gimnasio.model.entity.Rol;
import co.edu.ufps.gimnasio.model.entity.RolUsuario;
import co.edu.ufps.gimnasio.model.entity.Usuario;
import co.edu.ufps.gimnasio.repository.EntrenadorRepository;
import co.edu.ufps.gimnasio.repository.RolRepository;
import co.edu.ufps.gimnasio.repository.RolUsuarioRepository;
import co.edu.ufps.gimnasio.repository.UsuarioReporitory;
import co.edu.ufps.gimnasio.service.AWSS3Service;
import co.edu.ufps.gimnasio.service.UsuarioService;
@Service
public class UsuarioServiceImpl  implements UsuarioService{
	
	@Autowired
	UsuarioReporitory usuarioReporitory;
	@Autowired
	RolRepository rolRepository;
	@Autowired
	RolUsuarioRepository rolUsuarioRepository;
	@Autowired
	EntrenadorRepository entrenadorReposiotry;
	
	@Autowired
	AWSS3Service awss3Service;
	
	@Autowired
	ModelMapper modelMapper;
	
	
	@Override
	public List<Usuario> listaUsuarios() {
		return usuarioReporitory.findAll();
	}

	@Override
	public UsuarioDTO findByIdUsuario(Integer id) {
		Optional<Usuario>usuario=usuarioReporitory.findById(id);
		if(usuario.isPresent()) {
			return modelMapper.map(usuario, UsuarioDTO.class);
		}
		return null;
	}

	

	@Override
	public UsuarioDTO updateByIdUsuario(Integer id, Usuario usuario) {
		Optional<Usuario>usuarioCurrent=usuarioReporitory.findById(id);
		if(usuarioCurrent.isPresent()) {
			 Usuario usuarioReturn=usuarioCurrent.get();
			 usuarioReturn.setCedula(usuario.getCedula());
			 //usuarioReturn.setEmail(usuario.getEmail());
			 usuarioReturn.setNombre(usuario.getNombre());
			 usuarioReturn.setTelefono(usuario.getTelefono());
			 usuarioReturn.setFechaNacimiento(usuario.getFechaNacimiento());
			// usuarioReturn.setEstado(usuario.isEstado());
			 Usuario usuarioSave= usuarioReporitory.save(usuarioReturn);
			 return modelMapper.map(usuarioSave, UsuarioDTO.class);
		}
		return null;
	}

	@Override
	public void deleteUsuario(Integer id) {
		Optional<Usuario>usuario=usuarioReporitory.findById(id);
		if(usuario.isPresent()) {
			usuarioReporitory.deleteById(id);
		}
		
	}

	@Override
	public UsuarioDTO saveCliente(Usuario usuario) {
		//Rol Cliente = ID 2
		UsuarioDTO usuarioReturn =saveUsuarios(usuario, 2);
		return usuarioReturn;
	}

	@Override
	public UsuarioDTO saveEntrenador(Usuario usuario) {
		//Rol Entrenador ID 3
		UsuarioDTO usuarioReturn =saveUsuarios(usuario, 3);
		Entrenador entrenador=new Entrenador();
		entrenador.setEspecialidad("entrenador");
		entrenador.setUsuarioId(usuarioReturn.getId());
		entrenadorReposiotry.save(entrenador);
		
		return usuarioReturn;
	}

	@Override
	public UsuarioDTO saveRecepcionista(Usuario usuario) {
		// Rol Recepcionista ID 4
		UsuarioDTO usuarioReturn =saveUsuarios(usuario, 4);
		return usuarioReturn;
	}
	
	public UsuarioDTO saveUsuarios(Usuario usuario,Integer id) {
		Optional<Rol>rol=rolRepository.findById(id);
		if(rol.isPresent()) {
			usuario.setEmail(usuario.getEmail().toUpperCase());
			usuario.setPassword(new BCryptPasswordEncoder().encode(usuario.getCedula()));
			usuario.setFechaRegistro(new Date());
			usuario.setEstado(true);
			Usuario usuarioReturn= usuarioReporitory.save(usuario);
			RolUsuario rolUsuario=new RolUsuario();
			rolUsuario.setRolId(rol.get().getId());
			rolUsuario.setUsuario(usuarioReturn);
			rolUsuarioRepository.save(rolUsuario);
			return modelMapper.map(usuarioReturn, UsuarioDTO.class);
		}
		return null;
	}

	@Override
	public InputStream downloadImagenPerfil(Integer usuarioId, String key) {
		// TODO Auto-generated method stub
		Optional<Usuario>usuario=usuarioReporitory.findById(usuarioId);
		if(usuario.isPresent()) {
			String ruta="GIMNASIO/PERFIL/"+usuario.get().getId()+"/";
			return awss3Service.downloadFile(ruta, key);
		}
		return null;
	}

	@Override
	public UsuarioDTO createFolderByIdUsuario(Integer id, MultipartFile file) {
		// TODO Auto-generated method stub
		Optional<Usuario>usuario=usuarioReporitory.findById(id);
		if(usuario.isPresent()) {
			String ruta="GIMNASIO/PERFIL/"+usuario.get().getId()+"/";
			if(usuario.get().getFoto()!=null) {
				awss3Service.deleteFile(ruta, usuario.get().getFoto());
			}
			String key=awss3Service.createFolderFile(ruta, file);
			
			usuario.get().setFoto(key);
			Usuario usuarioReturn=usuarioReporitory.save(usuario.get());
			return modelMapper.map(usuarioReturn, UsuarioDTO.class);
			
		}
		return null;
	}

	

}
