package co.edu.ufps.gimnasio.model.dto;

import java.util.Date;


import lombok.Data;

@Data
public class UsuarioMembresiasDTO {

	private Integer id;
	private UsuarioDTO usuarioId;
	private Integer membresiaId;
	private Integer entrenadorId;
	private UsuarioDTO vendedorId;
	private Integer precio;
	private Date fechaInicio;
	private Date fechaFin;
	private Date fechaRegistro;

}
