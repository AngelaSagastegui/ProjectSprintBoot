package pe.edu.upc.serviceimpl;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.entity.Botella;
import pe.edu.upc.repository.BotellaRepository;
import pe.edu.upc.service.IBotellaService;

@Service
public class BotellaServiceImpl implements IBotellaService {

	@Autowired
	private BotellaRepository eR;

	@Override
	@Transactional
	public Integer insertar(Botella botella) {
		int rpta = 0;
		if (rpta == 0) {
			eR.save(botella);
		}
		return rpta;
	}

	@Override
	@Transactional
	public void modificar(Botella botella) {
		eR.save(botella);

	}

	@Override
	@Transactional
	public void eliminar(int idTipoBotella) {
		eR.deleteById(idTipoBotella);

	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Botella> listarId(int idTipoBotella) {
		return eR.findById(idTipoBotella);
	}

	@Override
	public List<Botella> listar() {

		return eR.findAll();
	}

	@Override
	public List<Botella> findByNombreTipoBotella(String nombreTipoBotella) {
		// TODO Auto-generated method stub
		return eR.findByNombreTipoBotella(nombreTipoBotella);
	}

}