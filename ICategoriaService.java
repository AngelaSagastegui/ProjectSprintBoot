package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Categoria;

public interface ICategoriaService {

	public Integer insertar(Categoria categoria);

	public void modificar(Categoria categoria);

	public void eliminar(int idCategoria);

	Optional<Categoria> listarId(int idCategoria);

	List<Categoria> listar();

	List<Categoria> findByNombreCategoria(String nombreCategoria);

}
