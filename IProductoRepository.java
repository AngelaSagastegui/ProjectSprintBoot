package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.edu.upc.model.Producto;

public interface IProductoRepository extends JpaRepository<Producto, Integer> {
	@Query("from Producto p where p.nombreProducto like %:nombreProducto%")
	List<Producto> buscarNombre(@Param("nombreProducto") String nombreProducto);

}
