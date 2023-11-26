package co.edu.ufps.gimnasio.model.entity;

import java.util.Date;
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
@Table(name="usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY )
	private Integer id;
	private String nombre;
	private String cedula;
	private String telefono;
	private String email;
	@Column(nullable = true)
	private String foto;
	private String password;
	@Column(name="fecha_nacimiento")
	private Date fechaNacimiento;
	@Column(name="fecha_registro")
	private Date fechaRegistro;
	private boolean estado;
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "rol_usuario", joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "rol_id", referencedColumnName = "id"))
	
	private Set<Rol>roles=new HashSet<>();

}
