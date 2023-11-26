package co.edu.ufps.gimnasio.service;

import java.io.InputStream;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import co.edu.ufps.gimnasio.model.dto.UsuarioDTO;
import co.edu.ufps.gimnasio.model.entity.Usuario;

public interface UsuarioService {
	
	public List<Usuario>listaUsuarios();
	public UsuarioDTO findByIdUsuario(Integer id);
	public UsuarioDTO saveCliente(Usuario usuario);
	public UsuarioDTO saveEntrenador(Usuario usuario);
	public UsuarioDTO saveRecepcionista(Usuario usuario);
	public UsuarioDTO updateByIdUsuario(Integer id,Usuario usuario);
	public InputStream downloadImagenPerfil(Integer usuarioId, String key);

	public UsuarioDTO createFolderByIdUsuario(Integer id,MultipartFile file);
	public void deleteUsuario(Integer id);

}
