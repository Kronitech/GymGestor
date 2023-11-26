package co.edu.ufps.gimnasio.model.dto;

import java.util.List;


import co.edu.ufps.gimnasio.model.entity.Ejercicio;
import lombok.Data;

@Data
public class RutinaDTO {
	
	private Integer id;
	private String nombre;
	private String descripcion;
	private String duracion;
	private List<Ejercicio>ejercicios;

}
