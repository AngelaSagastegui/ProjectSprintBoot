package pe.edu.upc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="Producto")
public class Producto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idProducto;
	
	@NotEmpty(message="No puede estar vacio")
	@NotBlank(message="No puede estar en blanco")
	@Column(name="nombreProducto", length=60, nullable=false)
	private String nombreProducto;
	

	@NotEmpty(message="No puede estar vacio")
	@NotBlank(message="No puede estar en blanco")
	@Column(name = "denominacion",  length=60, nullable=false)
	private String denominacion;
	
	

	@NotEmpty(message="No puede estar vacio")
	@NotBlank(message="No puede estar en blanco")
	@Column(name = "gradoAlcohol",  length=60, nullable=false)
	private String gradoAlcohol;
	

	@NotEmpty(message="No puede estar vacio")
	@NotBlank(message="No puede estar en blanco")
	@Column(name = "cantidad",  length=60, nullable=false)
	private String cantidad;


	public Producto() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Producto(int idProducto,
			@NotEmpty(message = "No puede estar vacio") @NotBlank(message = "No puede estar en blanco") String nombreProducto,
			@NotEmpty(message = "No puede estar vacio") @NotBlank(message = "No puede estar en blanco") String anioCosecha,
			@NotEmpty(message = "No puede estar vacio") @NotBlank(message = "No puede estar en blanco") String denominacion,
			@NotEmpty(message = "No puede estar vacio") @NotBlank(message = "No puede estar en blanco") String gradoAlcohol,
			@NotEmpty(message = "No puede estar vacio") @NotBlank(message = "No puede estar en blanco") String cantidad) {
		super();
		this.idProducto = idProducto;
		this.nombreProducto = nombreProducto;
		this.denominacion = denominacion;
		this.gradoAlcohol = gradoAlcohol;
		this.cantidad = cantidad;
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



	public String getDenominacion() {
		return denominacion;
	}


	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}


	public String getGradoAlcohol() {
		return gradoAlcohol;
	}


	public void setGradoAlcohol(String gradoAlcohol) {
		this.gradoAlcohol = gradoAlcohol;
	}


	public String getCantidad() {
		return cantidad;
	}


	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}
	
	




	


	
}
