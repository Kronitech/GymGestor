package co.edu.ufps.gimnasio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.ufps.gimnasio.model.entity.Entrenador;

public interface EntrenadorRepository extends JpaRepository<Entrenador, Integer>{
	
	Optional<Entrenador>findByUsuarioId(Integer usuarioId);

}
