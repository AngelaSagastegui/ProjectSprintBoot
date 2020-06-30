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
@Table(name = "categoria")
public class Categoria implements Serializable {


	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCategoria;


	@NotEmpty(message = "Ingresa el nombre de la categoria")
	@Column(name = "nombreCategoria", nullable = false, length = 20, unique = true)
	private String nombreCategoria;

	@NotEmpty(message = "Ingresa la descripcion  de la categoria")
	@Column(name = "descripcionCategoria", nullable = false, length = 60)
	private String descripcionCategoria;

	public Categoria() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Categoria(int idCategoria, @NotEmpty(message = "Ingresa el nombre de la categoria") String nombreCategoria,
			@NotEmpty(message = "Ingresa la descripcion  de la categoria") String descripcionCategoria) {
		super();
		this.idCategoria = idCategoria;
		this.nombreCategoria = nombreCategoria;
		this.descripcionCategoria = descripcionCategoria;
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNombreCategoria() {
		return nombreCategoria;
	}

	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}

	public String getDescripcionCategoria() {
		return descripcionCategoria;
	}

	public void setDescripcionCategoria(String descripcionCategoria) {
		this.descripcionCategoria = descripcionCategoria;
	}
	
	
}
