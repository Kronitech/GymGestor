package co.edu.ufps.gimnasio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.ufps.gimnasio.model.entity.Rutina;

public interface RutinaRepository extends JpaRepository<Rutina, Integer>{
	
	List<Rutina> findByEstado(Boolean estado);

}
