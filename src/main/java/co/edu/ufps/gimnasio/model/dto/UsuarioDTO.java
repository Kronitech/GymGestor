package co.edu.ufps.gimnasio.model.dto;

import java.util.Date;
import java.util.Set;


import co.edu.ufps.gimnasio.model.entity.Rol;
import lombok.Data;

@Data
public class UsuarioDTO {
	
	private Integer id;
	private String nombre;
	private String cedula;
	private String telefono;
	private String email;
	private String foto;
	private Date fechaNacimiento;
	private Date fechaRegistro;
	private boolean estado;
	private Set<Rol> roles;
	

}
