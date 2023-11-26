package co.edu.ufps.gimnasio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.ufps.gimnasio.model.entity.CodigoRecuperacion;

public interface CodigoRecuperacionRepository extends JpaRepository<CodigoRecuperacion, Integer>{
	
	List<CodigoRecuperacion> findByUsuarioId(Integer usuarioId);

}
