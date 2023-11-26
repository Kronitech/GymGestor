package co.edu.ufps.gimnasio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.ufps.gimnasio.model.entity.Entrenamiento;

public interface EntrenamientoRepository extends JpaRepository<Entrenamiento, Integer>{

}
