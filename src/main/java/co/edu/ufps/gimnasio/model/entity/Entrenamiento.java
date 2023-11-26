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
@Table(name="entrenamiento")
public class Entrenamiento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="usuario_id")
	private Integer usuarioId;
	@Column(name="rutina_id")
	private Integer rutinaId;
	@Column(name="ejercicio_id")
	private Integer ejercicioId;
	private Integer series;
	private Integer repeciones;
	private Integer peso;
	@Column(name="fecha_registro")
	private Date fechaRegistro;

}
