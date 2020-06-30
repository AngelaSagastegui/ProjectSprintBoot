package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entity.Detalle_Producto_Evento;
import pe.edu.upc.repository.DetalleRepository;
import pe.edu.upc.service.IDetalle_Producto_EventoService;

@Service
public class Detalle_Producto_EventoServiceImpl implements IDetalle_Producto_EventoService {

	@Autowired
	private DetalleRepository dR;

	@Override
	@Transactional
	public Integer insertar(Detalle_Producto_Evento detalle_producto_evento) {
		int rpta = 0;
		if (rpta == 0) {
			dR.save(detalle_producto_evento);
		}
		return rpta;
	}

	@Override
	@Transactional
	public void modificar(Detalle_Producto_Evento detalle_producto_evento) {
		dR.save(detalle_producto_evento);
	}

	@Override
	@Transactional
	public void eliminar(int idDetalle) {
		dR.deleteById(idDetalle);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Detalle_Producto_Evento> listarId(int idDetalle) {

		return dR.findById(idDetalle);
	}

	@Override
	public List<Detalle_Producto_Evento> listar() {
		return dR.findAll();
	}

	@Override
	public List<Detalle_Producto_Evento> buscarDetalle(int cantidadDetalle) {
		return dR.buscarDetalle(cantidadDetalle);
	}

	@Override
	public List<Detalle_Producto_Evento> buscarProducto(String nombreProducto) {
		return dR.buscarProducto(nombreProducto);
	}
	
	@Override
	public List<Detalle_Producto_Evento> buscarEvento(String nombreEvento) {
		return dR.buscarEvento(nombreEvento);
	}

}
