package co.edu.ufps.gimnasio.model.entity;

import java.util.Date;
import java.util.List;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="asistencia")
public class Asistencia {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="usuario_id")
	private Integer usuarioId;
	private Integer hora;
	@Column(name="fecha_registro")
	private String fechaRegistro;
	
	@OneToMany(mappedBy = "asistenciaId" ,fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	
	private List<Entrenamiento>entrenamientos;
	
	

}
