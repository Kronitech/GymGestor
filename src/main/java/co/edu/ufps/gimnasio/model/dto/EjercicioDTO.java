package co.edu.ufps.gimnasio.model.dto;

import java.util.List;

import co.edu.ufps.gimnasio.model.entity.Equipamiento;
import lombok.Data;

@Data

public class EjercicioDTO {
	private Integer id;
	private String nombre;
	private String descripcion;
	private String tipoEjercicio;
	private String musculaturaTrabajada;
	private String instrucciones;
	private String video;
	private List<Equipamiento>equipamientos;

}
