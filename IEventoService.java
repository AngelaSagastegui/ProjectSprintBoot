package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.model.Evento;

public interface IEventoService {
	public boolean insertar(Evento evento);
	public boolean modificar(Evento evento);
	public void eliminar(int idEvento);
	public Optional<Evento> listarId(int idEvento);
	List<Evento> listar();
	List<Evento> buscarNombre(String nombreEvento);
}
