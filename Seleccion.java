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
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "seleccion")
public class Seleccion implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idSeleccion;

	@NotEmpty(message = "Ingresa el nombre de la seleccion")
	@Column(name = "nombreSeleccion", nullable = false, length = 30, unique = true)
	private String nombreSeleccion;

	@NotEmpty(message = "Ingresar la descripci\\u00f3n de la Seleccion")
	@Column(name = "descripcionSeleccion", nullable = false, length = 60)
	private String descripcionSeleccion;

	@ManyToOne
	@JoinColumn(name = "idTipoBotella")
	private Botella botella;

	public Seleccion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Seleccion(int idSeleccion, @NotEmpty(message = "Ingresa el nombre de la seleccion") String nombreSeleccion,
			@NotEmpty(message = "Ingresar la descripci\\u00f3n de la Seleccion") String descripcionSeleccion,
			Botella botella) {
		super();
		this.idSeleccion = idSeleccion;
		this.nombreSeleccion = nombreSeleccion;
		this.descripcionSeleccion = descripcionSeleccion;
		this.botella = botella;
	}

	public int getIdSeleccion() {
		return idSeleccion;
	}

	public void setIdSeleccion(int idSeleccion) {
		this.idSeleccion = idSeleccion;
	}

	public String getNombreSeleccion() {
		return nombreSeleccion;
	}

	public void setNombreSeleccion(String nombreSeleccion) {
		this.nombreSeleccion = nombreSeleccion;
	}

	public String getDescripcionSeleccion() {
		return descripcionSeleccion;
	}

	public void setDescripcionSeleccion(String descripcionSeleccion) {
		this.descripcionSeleccion = descripcionSeleccion;
	}

	public Botella getBotella() {
		return botella;
	}

	public void setBotella(Botella botella) {
		this.botella = botella;
	}

	
	
	
}
