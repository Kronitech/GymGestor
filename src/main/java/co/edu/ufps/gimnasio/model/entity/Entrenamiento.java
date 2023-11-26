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
	@Column(name="asistencia_id")
	private Integer asistenciaId;
	private Integer series;
	private Integer repeticiones;
	private Integer peso;
	@Column(name="fecha_registro")
	private Date fechaRegistro;

}
