package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Personalizacion;

public interface IPersonalizacionService {

	public Integer insertar(Personalizacion personalizacion);

	public void modificar(Personalizacion personalizacion);

	public void eliminar(int idPersonalizacion);

	Optional<Personalizacion> listarId(int idPersonalizacion);

	List<Personalizacion> listar();

	List<Personalizacion> buscarNombre(String nombreVino);
	
	List<Personalizacion> buscarSeleccion(String nombreSeleccion);

}
