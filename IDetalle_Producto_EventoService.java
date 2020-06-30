package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Detalle_Producto_Evento;

public interface IDetalle_Producto_EventoService {

	public Integer insertar(Detalle_Producto_Evento detalle_producto_evento);

	public void modificar(Detalle_Producto_Evento detalle_producto_evento);

	public void eliminar(int idDetalle);

	Optional<Detalle_Producto_Evento> listarId(int idDetalle);

	List<Detalle_Producto_Evento> listar();

	List<Detalle_Producto_Evento> buscarDetalle(int cantidadDetalle);
	
	List<Detalle_Producto_Evento> buscarProducto(String nombreProducto);

	List<Detalle_Producto_Evento> buscarEvento(String nombreEvento);

}
