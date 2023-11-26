package co.edu.ufps.gimnasio.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.edu.ufps.gimnasio.model.entity.Asistencia;

public interface AsistenciaRepository  extends JpaRepository<Asistencia, Integer>{
	
	// Método para verificar si la asistencia está registrada para un usuario y una fecha específica
    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM Asistencia a WHERE a.usuarioId = :usuarioId AND a.fechaRegistro = :fecha")
    boolean asistenciaRegistrada(@Param("usuarioId") Integer usuarioId, @Param("fecha") String fecha);
    
    List<Asistencia>findByFechaRegistro(String fechaRegistro);
    
    @Query("SELECT COUNT(a) FROM Asistencia a WHERE a.fechaRegistro = :fecha AND a.hora = :hora")
    int contarRegistrosPorFechaYHora(@Param("fecha") String fecha, @Param("hora") Integer hora);
}
