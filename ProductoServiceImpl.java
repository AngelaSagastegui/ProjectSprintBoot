package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.model.Producto;
import pe.edu.upc.repository.IProductoRepository;
import pe.edu.upc.service.IProductoService;

@Service
public class ProductoServiceImpl implements IProductoService{
	@Autowired
	private IProductoRepository dProducto;
	
	@Override
	@Transactional
	public boolean insertar(Producto producto) {
		Producto objProducto = dProducto.save(producto);
		if (objProducto == null)
			return false;
		else 
			return true;
	}
	
	@Override
	@Transactional
	public boolean modificar(Producto producto) {
		boolean flag = false;
		try {
			dProducto.save(producto);
			flag = true;
		}
		catch(Exception ex) {
			System.out.println("Sucedio un roche....");
		}
		return flag;
	}

	@Override
	@Transactional
	public void eliminar(int idProducto) {
		dProducto.deleteById(idProducto);		
	}

	@Override
	@Transactional
	public Optional<Producto> listarId(int idProducto) {
		return dProducto.findById(idProducto);
	}

	@Override
	@Transactional
	public List<Producto> listar() {
		return dProducto.findAll();
	}

	@Override
	@Transactional
	public List<Producto> buscarNombre(String nombreProducto) {
		return dProducto.buscarNombre(nombreProducto);
	}

}
