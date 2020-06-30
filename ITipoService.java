package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Tipo;

public interface ITipoService {
	public Integer insertar(Tipo tipo);

	public void modificar(Tipo tipo);

	public void eliminar(int idTipo);
	
	Optional<Tipo> listarId(int idTipo);


	List<Tipo> listar();

	List<Tipo> buscarNombre(String nombreTipo);

}
