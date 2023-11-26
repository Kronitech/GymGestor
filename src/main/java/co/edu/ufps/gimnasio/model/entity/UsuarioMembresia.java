package co.edu.ufps.gimnasio.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="usuario_membresia")
public class UsuarioMembresia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="usuario_id")
	private Integer usuarioId;
	@Column(name="membresia_id")
	private Integer membresiaId;
	@Column(name="entrenador_id")
	private Integer entrenadorId;
	@Column(name="vendedor_id")
	private Integer vendedorId;
	private Integer precio;
	private Date fechaInicio;
	private Date fechaFin;
	private Date fechaRegistro;

}
