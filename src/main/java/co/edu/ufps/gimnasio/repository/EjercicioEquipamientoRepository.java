package co.edu.ufps.gimnasio.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import co.edu.ufps.gimnasio.model.entity.EjercicioEquipamiento;



public interface EjercicioEquipamientoRepository extends JpaRepository<EjercicioEquipamiento, Integer>{
	boolean existsByEjercicioIdAndEquipamientoId(Integer ejercicioId, Integer equipamientoId);
	List<EjercicioEquipamiento> findByEjercicioId(Integer ejercicioId);
	@Modifying
    @Transactional
    @Query("DELETE FROM EjercicioEquipamiento ee WHERE ee.ejercicioId = :ejercicioId AND ee.equipamientoId = :equipamientoId")
    void deleteByEjercicioIdAndEquipamientoId(Integer ejercicioId, Integer equipamientoId);

}
