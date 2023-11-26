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
@Table(name="ejercicio_equipamiento")
public class EjercicioEquipamiento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="ejercicio_id")
	private Integer ejercicioId;
	@Column(name="equipamiento_id")
	private Integer equipamientoId;

}
