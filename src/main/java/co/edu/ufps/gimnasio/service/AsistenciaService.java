package co.edu.ufps.gimnasio.service;

import java.util.Date;
import java.util.List;

import co.edu.ufps.gimnasio.model.entity.Asistencia;

public interface AsistenciaService {

	public List<Asistencia> lista();

	public boolean asistenciaRegistrada(Integer usuario);

	public Asistencia saveAsistencia(Asistencia asistencia);

	public Asistencia updateAsistencia(Asistencia asistencia);
	
	public List<Integer> datosAsistencia();
	
	public List<Asistencia>findByUsuarioId(Integer id);

}
