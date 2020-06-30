package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Tipo;

@Repository
public interface TipoRepository extends JpaRepository<Tipo, Integer> {
	@Query("from Tipo t where t.nombreTipo like %:nombreTipo%")
	List<Tipo> buscarNombre(@Param("nombreTipo") String nombreTipo);
}
