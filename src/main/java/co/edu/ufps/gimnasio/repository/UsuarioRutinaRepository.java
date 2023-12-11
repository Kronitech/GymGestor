package co.edu.ufps.gimnasio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.edu.ufps.gimnasio.model.entity.UsuarioRutina;

public interface UsuarioRutinaRepository extends JpaRepository<UsuarioRutina, Integer>{
	List<UsuarioRutina> findByUsuarioId(Integer usuarioId);
	
    @Query("SELECT COUNT(ur) > 0 FROM UsuarioRutina ur WHERE ur.usuarioId = :usuarioId AND ur.rutinaId = :rutinaId")
    boolean existsByUsuarioIdAndRutinaId(@Param("usuarioId") Integer usuarioId, @Param("rutinaId") Integer rutinaId);

	
	

}
