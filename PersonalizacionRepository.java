package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Personalizacion;

@Repository
public interface PersonalizacionRepository extends JpaRepository<Personalizacion, Integer> {
	@Query("from Personalizacion p where p.nombreVino like %:nombreVino%")
	List<Personalizacion> buscarNombre(@Param("nombreVino") String nombreVino);
	
	@Query("from Personalizacion p where p.seleccion.nombreSeleccion like %:nombreSeleccion%")
	List<Personalizacion> buscarSeleccion(@Param("nombreSeleccion") String nombreSeleccion);	
}
