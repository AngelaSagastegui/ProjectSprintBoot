package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import pe.edu.upc.model.Evento;
import pe.edu.upc.repository.IEventoRepository;
import pe.edu.upc.service.IEventoService;

public class EventoServiceImpl implements IEventoService {
	@Autowired
	private IEventoRepository dEvento;
	
	@Override
	@Transactional
	public boolean insertar(Evento evento) {
		Evento objEvento = dEvento.save(evento);
		if (objEvento == null)
			return false;
		else 
			return true;
	}
	
	@Override
	@Transactional
	public boolean modificar(Evento evento) {
		boolean flag = false;
		try {
			dEvento.save(evento);
			flag = true;
		}
		catch(Exception ex) {
			System.out.println("Sucedio un roche....");
		}
		return flag;
	}

	@Override
	@Transactional
	public void eliminar(int idEvento) {
		dEvento.deleteById(idEvento);		
	}

	@Override
	@Transactional
	public Optional<Evento> listarId(int idEvento) {
		return dEvento.findById(idEvento);
	}

	@Override
	@Transactional
	public List<Evento> listar() {
		return dEvento.findAll();
	}

	@Override
	@Transactional
	public List<Evento> buscarNombre(String nombreEvento) {
		return dEvento.buscarNombre(nombreEvento);
	}


}
