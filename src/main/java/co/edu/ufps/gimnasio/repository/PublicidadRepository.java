package co.edu.ufps.gimnasio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.ufps.gimnasio.model.entity.Publicidad;

public interface PublicidadRepository extends JpaRepository<Publicidad, Integer> {
	
	Optional<Publicidad> findByFoto(String foto);

}
