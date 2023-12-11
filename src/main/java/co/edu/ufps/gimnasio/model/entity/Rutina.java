package co.edu.ufps.gimnasio.model.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
@Table(name="rutina")
public class Rutina {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	@Column(name="descripcion" ,columnDefinition = "TEXT")
	private String descripcion;
	private String duracion;
	private String musculo;
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinTable(name="rutina_ejercicio",joinColumns = @JoinColumn(name="rutina_id",referencedColumnName = "id"),inverseJoinColumns = @JoinColumn(name="ejercicio_id",referencedColumnName = "id"))
	private List<Ejercicio>ejercicios=new  ArrayList<>();
	/*
	 @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinTable(name="ejercicio_equipamiento",joinColumns = @JoinColumn(name="ejercicio_id",referencedColumnName = "id"),inverseJoinColumns = @JoinColumn(name="equipamiento_id",referencedColumnName = "id"))
	private Set<Equipamiento>equipamientos=new HashSet<>();
	
	 */
	

}
