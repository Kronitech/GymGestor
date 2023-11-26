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
@Table(name="ejercicio")
public class Ejercicio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private String descripcion;
	@Column(name="tipo_ejercicio")
	private String tipoEjercicio;
	@Column(name="musculatura_trabajar")
	private String musculaturaTrabajada;
	private String instrucciones;

}
