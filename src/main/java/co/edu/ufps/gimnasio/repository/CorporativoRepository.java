package co.edu.ufps.gimnasio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.ufps.gimnasio.model.entity.Corporativo;

public interface CorporativoRepository extends JpaRepository<Corporativo, Integer> {
	

}
