package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;
import pe.edu.upc.entity.Botella;

public interface IBotellaService {

	public Integer insertar(Botella botella);

	public void modificar(Botella botella);

	public void eliminar(int idTipoBotella);

	Optional<Botella> listarId(int idTipoBotella);

	List<Botella> listar();

	List<Botella> findByNombreTipoBotella(String nombreTipoBotella);

}
