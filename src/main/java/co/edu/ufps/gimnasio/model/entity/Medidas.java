package co.edu.ufps.gimnasio.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "medidas")
public class Medidas {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

//    @Column(name="usuario_id")
//    private Integer usuarioId;

	// Si la relación con Usuario está descomentada, usa las siguientes líneas
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	//@Column(name = "entrenador_id")
	//private Integer entrenador;

	// Si la relación con Entrenador está descomentada, usa las siguientes líneas
	@ManyToOne
	@JoinColumn(name = "entrenador_id")
    private Usuario entrenador;

	private Double peso;
	private Double estatura;

	@Column(name = "tam_brazo")
	private Double tamBrazo;

	@Column(name = "tam_antebrazo")
	private Double tamAntebrazo;

	@Column(name = "tam_pierna")
	private Double tamPierna;

	@Column(name = "tam_gemelo")
	private Double tamGemelo;

	@Column(name = "tam_gluteo")
	private Double tamGluteo;

	@Column(name = "tam_pectoral")
	private Double tamPectoral;

	@Column(name = "fecha_registro")
	private Date fechaRegistro;
}
