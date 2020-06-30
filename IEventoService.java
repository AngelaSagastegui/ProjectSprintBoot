package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Evento;

public interface IEventoService {

	public Integer insertar(Evento evento);
	
	public void eliminar(int idEvento);

	public void modificar(Evento evento);

	Optional<Evento> listarId(int idEvento);

	List<Evento> listar();

	List<Evento>  buscarNombre(String nombreEvento);

	List<Evento> buscarPatrocinador( String nombrePatrocinador);
	

}
