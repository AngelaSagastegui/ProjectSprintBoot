package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer> {

	@Query("from Evento e where e.nombreEvento like %:nombreEvento%")
	List<Evento> buscarNombre(@Param("nombreEvento") String nombreEvento);
	
	@Query("from Evento e where e.nombrePatrocinador like %:nombrePatrocinador%")
	List<Evento> buscarPatrocinador(@Param("nombrePatrocinador") String nombrePatrocinador);
}
