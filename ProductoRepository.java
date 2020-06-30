package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
	@Query("from Producto p where p.nombreProducto like %:nombreProducto%")
	List<Producto> buscarNombre(@Param("nombreProducto") String nombreProducto);
	
	@Query("from Producto p where p.categoria.nombreCategoria like %:nombreCategoria%")
	List<Producto> buscarCategoria(@Param("nombreCategoria") String nombreCategoria);	
	
	@Query("from Producto p where p.tipo.nombreTipo like %:nombreTipo%")
	List<Producto> buscarTipo(@Param("nombreTipo") String nombreTipo);	

}
