package co.edu.ufps.gimnasio.service;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;

import co.edu.ufps.gimnasio.model.dto.UsuarioDTO;
import co.edu.ufps.gimnasio.model.dto.UsuarioMembresiaDTO;
import co.edu.ufps.gimnasio.model.entity.UsuarioMembresia;
import net.sf.jasperreports.engine.JRException;

public interface UsuarioMembresiaService {
	
	public List<UsuarioMembresia>lista();
	
	public UsuarioMembresia findById(Integer id);
	
	public UsuarioMembresia saveUsuarioMembresia(UsuarioMembresia usuarioMembresia);
	
	public UsuarioMembresia updateUsuarioMembresia(UsuarioMembresia usuarioMembresia,Integer id);
	
	public void delete(Integer id);
	
	public List<UsuarioMembresia>membresiasActivas(Integer id);
	
	public UsuarioMembresiaDTO findByCedulaUsuario(String cedula);
	
	public List<UsuarioDTO> listaClientesEntrenador(Integer usuarioId);
	
	public List<UsuarioMembresia> membresiasUsuarioById(Integer id);
	
	public byte[] informeMembresias(Date fechaInicio,Date fechaFin)throws JRException, FileNotFoundException;
	
	 public byte[] informeMembresias()  throws JRException, FileNotFoundException;
	 
	 public byte [] comprobantePago(Integer id)throws JRException, FileNotFoundException;
}
