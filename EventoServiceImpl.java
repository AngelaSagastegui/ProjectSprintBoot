package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entity.Evento;
import pe.edu.upc.repository.EventoRepository;
import pe.edu.upc.service.IEventoService;

@Service
public class EventoServiceImpl implements IEventoService {

	@Autowired
	private EventoRepository eR;


	@Override
	@Transactional
	public Integer insertar(Evento evento) {
		int rpta = 0;
		if (rpta == 0) {
			eR.save(evento);
		}
		return rpta;
	}

	@Override
	@Transactional
	public void eliminar(int idEvento) {
		eR.deleteById(idEvento);
	}

	@Override
	@Transactional
	public void modificar(Evento evento) {
		eR.save(evento);
	}

	@Override
	public Optional<Evento> listarId(int idEvento) {
		return eR.findById(idEvento);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Evento> listar() {
		return eR.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Evento> buscarNombre(String nombreEvento) {
		return eR.buscarNombre(nombreEvento);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Evento> buscarPatrocinador( String nombrePatrocinador) {
		return eR.buscarPatrocinador(nombrePatrocinador);
	}


}
