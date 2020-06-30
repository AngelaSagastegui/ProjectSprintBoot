package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entity.Categoria;
import pe.edu.upc.repository.CategoriaRepository;
import pe.edu.upc.service.ICategoriaService;

@Service
public class CategoriaServiceImpl implements ICategoriaService {

	@Autowired
	private CategoriaRepository cR;

	@Override
	@Transactional
	public Integer insertar(Categoria categoria) {

		int rpta = 0;
		if (rpta == 0) {
			cR.save(categoria);
		}
		return rpta;

	}

	@Override
	@Transactional
	public void modificar(Categoria categoria) {

		cR.save(categoria);
	}

	@Override
	@Transactional
	public void eliminar(int idCategoria) {

		cR.deleteById(idCategoria);

	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Categoria> listarId(int idCategoria) {
		return cR.findById(idCategoria);

	}

	@Override
	public List<Categoria> listar() {
		return cR.findAll();
	}

	@Override
	public List<Categoria> findByNombreCategoria(String nombreCategoria) {
		return cR.findByNombreCategoria( nombreCategoria);
	}

}
