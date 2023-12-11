package co.edu.ufps.gimnasio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.ufps.gimnasio.model.entity.Membresia;

public interface MembresiaRepository extends JpaRepository<Membresia, Integer> {

	List<Membresia> findByEstado(Boolean estado);
}
