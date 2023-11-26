package co.edu.ufps.gimnasio.model.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	@Column(name="instrucciones",columnDefinition = "TEXT")
	private String instrucciones;
	private String video;
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinTable(name="ejercicio_equipamiento",joinColumns = @JoinColumn(name="ejercicio_id",referencedColumnName = "id"),inverseJoinColumns = @JoinColumn(name="equipamiento_id",referencedColumnName = "id"))
	private Set<Equipamiento>equipamientos=new HashSet<>();
	
	

}
