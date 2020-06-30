package pe.edu.upc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "producto")
public class Producto implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idProducto;

	@NotEmpty(message = "Ingresa el nombre del producto")
	@Column(name = "nombreProducto", nullable = false, length = 30, unique = true)
	private String nombreProducto;

	@Min(0)
	@Max(100)
	@Column(name = "gradoAlcohol", nullable = false)
	private int gradoAlcohol;

	@ManyToOne
	@JoinColumn(name="idCategoria", nullable=false)
	private Categoria categoria;

	@ManyToOne
	@JoinColumn(name="idTipo", nullable=false)
	private Tipo tipo;

	public Producto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Producto(int idProducto, @NotEmpty(message = "Ingresa el nombre del producto") String nombreProducto,
			@Min(0) @Max(100) int gradoAlcohol, Categoria categoria, Tipo tipo) {
		super();
		this.idProducto = idProducto;
		this.nombreProducto = nombreProducto;
		this.gradoAlcohol = gradoAlcohol;
		this.categoria = categoria;
		this.tipo = tipo;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public int getGradoAlcohol() {
		return gradoAlcohol;
	}

	public void setGradoAlcohol(int gradoAlcohol) {
		this.gradoAlcohol = gradoAlcohol;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	
	
	

}
