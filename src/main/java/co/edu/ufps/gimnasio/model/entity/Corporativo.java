package co.edu.ufps.gimnasio.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data

public class Corporativo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private String logo;
	private String telefono;
	private String email;
	private String ubicacion;
	private String horario;
	private Integer aforo;
	
}
