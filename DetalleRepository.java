package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Detalle_Producto_Evento;

@Repository
public interface DetalleRepository extends JpaRepository<Detalle_Producto_Evento, Integer> {
	@Query("from Detalle_Producto_Evento d where d.cantidadDetalle like %:cantidadDetalle%")
	List<Detalle_Producto_Evento> buscarDetalle(@Param("cantidadDetalle") int cantidadDetalle);
	
	@Query("from Detalle_Producto_Evento d where d.producto.nombreProducto like %:nombreProducto%")
	List<Detalle_Producto_Evento> buscarProducto(@Param("nombreProducto") String nombreProducto);	
	
	@Query("from Detalle_Producto_Evento d where d.evento.nombreEvento like %:nombreEvento%")
	List<Detalle_Producto_Evento> buscarEvento(@Param("nombreEvento") String nombreEvento);	
	
}
