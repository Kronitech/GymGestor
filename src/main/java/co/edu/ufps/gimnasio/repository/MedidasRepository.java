package co.edu.ufps.gimnasio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.ufps.gimnasio.model.entity.Medidas;
import co.edu.ufps.gimnasio.model.entity.Usuario;

public interface MedidasRepository extends JpaRepository<Medidas, Integer> {
	List<Medidas> findByUsuario(Usuario usuario);
	
	
}
