package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.edu.upc.entity.Botella;

public interface BotellaRepository extends JpaRepository<Botella, Integer> {

	@Query("from Botella b where b.nombreTipoBotella like %:nombreTipoBotella%")
	List<Botella> findByNombreTipoBotella(@Param("nombreTipoBotella") String nombreTipoBotella);

}
