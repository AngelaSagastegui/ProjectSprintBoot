package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entity.Producto;
import pe.edu.upc.repository.ProductoRepository;
import pe.edu.upc.service.IProductoService;

@Service
public class ProductoServiceImpl implements IProductoService {

	@Autowired
	private ProductoRepository pR;

	@Override
	@Transactional
	public Integer insertar(Producto producto) 
	{
		int rpta = 0;
		if (rpta == 0) {
			pR.save(producto);
		}
		return rpta;

	}

	@Override
	@Transactional
	public void modificar(Producto producto) {
		pR.save(producto);
	}

	@Override
	@Transactional
	public void eliminar(int idProducto) {
		pR.deleteById(idProducto);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Producto> listarId(int idProducto) {
		return pR.findById(idProducto);
	}

	@Override
	public List<Producto> listar() {
		return pR.findAll();
	}
	@Override
	@Transactional(readOnly = true)
	public List<Producto> buscarNombre(String nombreProducto) {
		return pR.buscarNombre(nombreProducto);
	}

	@Override
	public List<Producto> buscarCategoria(String nombreCategoria) {
		return pR.buscarCategoria(nombreCategoria);
	}
	@Override
	public List<Producto> buscarTipo(String nombreTipo) {
		return pR. buscarTipo(nombreTipo);
	}

}
