package co.edu.ufps.gimnasio.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="usuario_membresia")
public class UsuarioMembresia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	@JoinColumn(name="usuario_id")
	private Usuario usuarioId;
	@Column(name="membresia_id")
	private Integer membresiaId;
	@Column(name="entrenador_id")
	private Integer entrenadorId;
	@ManyToOne
	@JoinColumn(name="vendedor_id")
	private Usuario vendedorId;
	private Integer precio;
	private Date fechaInicio;
	private Date fechaFin;
	private Date fechaRegistro;

}
