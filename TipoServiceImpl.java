package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entity.Tipo;
import pe.edu.upc.repository.TipoRepository;
import pe.edu.upc.service.ITipoService;

@Service
public class TipoServiceImpl implements ITipoService {

	@Autowired
	private TipoRepository rR;

	@Override
	@Transactional
	public Integer insertar(Tipo tipo) {
		int rpta = 0;
		if (rpta == 0) {
			rR.save(tipo);
		}
		return rpta;
	}

	@Override
	@Transactional
	public void modificar(Tipo tipo) {
		rR.save(tipo);
	}

	@Override
	@Transactional
	public void eliminar(int idTipo) {
		rR.deleteById(idTipo);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Tipo> listarId(int idRecurso) {
		return rR.findById(idRecurso);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Tipo> listar() {
		return rR.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Tipo>  buscarNombre(String nombreTipo) {
		return rR. buscarNombre(nombreTipo);
	}

	
}
