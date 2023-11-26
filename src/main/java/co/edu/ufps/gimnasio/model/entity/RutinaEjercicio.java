package co.edu.ufps.gimnasio.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="rutina_ejercicio")
public class RutinaEjercicio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="rutina_id")
	private Integer rutinaId;
	@Column(name="ejercicio_id")
	private Integer ejercicoId;
	

}
