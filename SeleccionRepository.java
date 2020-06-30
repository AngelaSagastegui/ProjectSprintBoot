package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Seleccion;

@Repository
public interface SeleccionRepository extends JpaRepository<Seleccion, Integer> {
	@Query("from Seleccion s where s.nombreSeleccion like %:nombreSeleccion%")
	List<Seleccion> buscarNombre(@Param("nombreSeleccion") String nombreSeleccion);
	
	@Query("from Seleccion s where s.botella.nombreTipoBotella like %:nombreTipoBotella%")
	List<Seleccion> buscarTipoBotella(@Param("nombreTipoBotella") String nombreTipoBotella);
}
