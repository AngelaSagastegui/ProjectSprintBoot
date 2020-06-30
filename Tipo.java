package pe.edu.upc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "tipo")
public class Tipo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTipo;

	@NotEmpty(message = "Ingresa el nombre del tipo")
	@Column(name = "nombreTipo", nullable = false, length = 30, unique = true)
	private String nombreTipo;

	@NotEmpty(message = "Ingresa la descripcion del tipo")
	@Column(name = "descripcionTipo", nullable = false, length = 20)
	private String descripcionTipo;

	public Tipo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Tipo(int idTipo, @NotEmpty(message = "Ingresa el nombre del tipo") String nombreTipo,
			@NotEmpty(message = "Ingresa la descripcion del tipo") String descripcionTipo) {
		super();
		this.idTipo = idTipo;
		this.nombreTipo = nombreTipo;
		this.descripcionTipo = descripcionTipo;
	}

	public int getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(int idTipo) {
		this.idTipo = idTipo;
	}

	public String getNombreTipo() {
		return nombreTipo;
	}

	public void setNombreTipo(String nombreTipo) {
		this.nombreTipo = nombreTipo;
	}

	public String getDescripcionTipo() {
		return descripcionTipo;
	}

	public void setDescripcionTipo(String descripcionTipo) {
		this.descripcionTipo = descripcionTipo;
	}

	

	


}
