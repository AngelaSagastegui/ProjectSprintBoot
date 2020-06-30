package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entity.Seleccion;
import pe.edu.upc.repository.SeleccionRepository;
import pe.edu.upc.service.ISeleccionService;

@Service
public class SeleccionServiceImpl implements ISeleccionService {

	@Autowired
	private SeleccionRepository cR;

	@Override
	@Transactional
	public Integer insert(Seleccion seleccion) {
		int rpta = 0;
		if (rpta == 0) {
			cR.save(seleccion);
		}
		return rpta;
	}

	@Override
	@Transactional
	public void modify(Seleccion seleccion) {
		cR.save(seleccion);
	}

	@Override
	@Transactional
	public void delete(int idSeleccion) {
		cR.deleteById(idSeleccion);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Seleccion> listId(int idSeleccion) {
		return cR.findById(idSeleccion);
	}

	@Override
	public List<Seleccion> list() {
		return cR.findAll();
	}

	@Override
	public List<Seleccion> buscarNombre(String nombreSeleccion) {
		return cR.buscarNombre(nombreSeleccion);
	}
	
	@Override
	public List<Seleccion> buscarTipoBotella(String nombreTipoBotella) {
		return cR.buscarTipoBotella(nombreTipoBotella);
	}

}
