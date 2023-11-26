package co.edu.ufps.gimnasio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.ufps.gimnasio.model.entity.RolUsuario;

public interface RolUsuarioRepository extends JpaRepository<RolUsuario, Integer> {
	List<RolUsuario> findByRolId(Integer rolId);
}
