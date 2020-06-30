package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.Personalizacion;
import pe.edu.upc.repository.PersonalizacionRepository;
import pe.edu.upc.service.IPersonalizacionService;

@Service
public class PersonalizacionServiceImpl implements IPersonalizacionService {

	@Autowired
	private PersonalizacionRepository eR;

	@Override
	@Transactional
	public Integer insertar(Personalizacion personalizacion) {
		int rpta = 0;
		if (rpta == 0) {
			eR.save(personalizacion);
		}
		return rpta;
	}

	@Override
	@Transactional
	public void modificar(Personalizacion personalizacion) {
		eR.save(personalizacion);
	}

	@Override
	@Transactional
	public void eliminar(int idPersonalizacion) {
		eR.deleteById(idPersonalizacion);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Personalizacion> listarId(int idPersonalizacion) {
		return eR.findById(idPersonalizacion);
	}

	@Override
	public List<Personalizacion> listar() {
		return eR.findAll();
	}

	@Override
	public List<Personalizacion> buscarNombre(String nombreVino) {
		return eR.buscarNombre(nombreVino);
	}
	
	@Override
	public List<Personalizacion> buscarSeleccion(String nombreSeleccion) {
		return eR.buscarSeleccion(nombreSeleccion);
	}

}
