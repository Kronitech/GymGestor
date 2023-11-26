package co.edu.ufps.gimnasio.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import co.edu.ufps.gimnasio.model.entity.RutinaEjercicio;

public interface RutinaEjercicioRepository extends JpaRepository<RutinaEjercicio, Integer>{
	
	List<RutinaEjercicio> findByRutinaId(Integer rutinaId);
	@Modifying
    @Transactional
    @Query("DELETE FROM RutinaEjercicio ee WHERE ee.rutinaId = :rutinaId AND ee.ejercicioId = :ejercicioId")
   
	void deleteByRutinaIdAndEjercicioId(Integer rutinaId,Integer ejercicioId);

}
