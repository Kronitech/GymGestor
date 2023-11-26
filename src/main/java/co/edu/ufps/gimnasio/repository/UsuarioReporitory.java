package co.edu.ufps.gimnasio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.ufps.gimnasio.model.entity.Usuario;

public interface UsuarioReporitory extends JpaRepository<Usuario, Integer>{
	Optional<Usuario> findByEmail(String email);
	Optional<Usuario> findByCedula(String cedula);
}
