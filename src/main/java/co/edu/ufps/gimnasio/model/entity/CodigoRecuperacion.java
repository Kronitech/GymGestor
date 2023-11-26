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
@Table(name="codigo_recuperacion")
public class CodigoRecuperacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="usuario_id")
	private Integer usuarioId;
	private Integer codigo;
	private boolean estado;
	@Column(name="fecha_registro")
	private Date fechaRegistro;
}
