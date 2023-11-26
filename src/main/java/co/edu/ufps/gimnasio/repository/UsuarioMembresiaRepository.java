package co.edu.ufps.gimnasio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.ufps.gimnasio.model.entity.UsuarioMembresia;

public interface UsuarioMembresiaRepository extends JpaRepository<UsuarioMembresia, Integer> {
	List<UsuarioMembresia> findByUsuarioId(Integer usuarioId);
}
