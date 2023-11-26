package co.edu.ufps.gimnasio.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.edu.ufps.gimnasio.model.entity.Usuario;
import co.edu.ufps.gimnasio.model.entity.UsuarioMembresia;

public interface UsuarioMembresiaRepository extends JpaRepository<UsuarioMembresia, Integer> {
	List<UsuarioMembresia> findByUsuarioId(Usuario usuario);
	
	 @Query("SELECT um FROM UsuarioMembresia um WHERE um.fechaFin >= :fechaFin")
	    List<UsuarioMembresia> findUsuariosMembresiaByFechaFin(@Param("fechaFin") Date fechaFin);
	 @Query("SELECT um FROM UsuarioMembresia um WHERE um.fechaRegistro >= :fechaInicio AND um.fechaRegistro <= :fechaFin")
	    List<UsuarioMembresia> findUsuariosMembresiaByFechaRegistro(@Param("fechaInicio") Date fechaInicio,@Param("fechaFin") Date fechaFin);
}
