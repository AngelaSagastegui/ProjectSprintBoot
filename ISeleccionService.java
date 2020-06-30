package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Seleccion;

public interface ISeleccionService {
	public Integer insert(Seleccion seleccion);

	public void modify(Seleccion seleccion);

	public void delete(int idSeleccion);

	Optional<Seleccion> listId(int idSeleccion);

	List<Seleccion> list();

	List<Seleccion> buscarNombre(String nombreSeleccion);
	
	List<Seleccion> buscarTipoBotella(String nombreTipoBotella);
}
