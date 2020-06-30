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
@Table(name = "personalizacion")
public class Personalizacion implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPersonalizacion;

	@NotEmpty(message = "Ingresa el nombre del Autor del Vino")
	@Column(name = "nombreAutorVino", nullable = false, length = 30)
	private String nombreAutorVino;

	@NotEmpty(message = "Ingresa el nombre del Vino a crear")
	@Column(name = "nombreVino", nullable = false, length = 30, unique = true)
	private String nombreVino;

	@Min(1)
	@Max(20)
	@Column(name = "cantBotellaVino", nullable = false)
	private int cantBotellaVino;

	@ManyToOne
	@JoinColumn(name="idSeleccion")
	private Seleccion seleccion;

	public Personalizacion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Personalizacion(int idPersonalizacion,
			@NotEmpty(message = "Ingresa el nombre del Autor del Vino") String nombreAutorVino,
			@NotEmpty(message = "Ingresa el nombre del Vino a crear") String nombreVino,
			@Min(1) @Max(20) int cantBotellaVino, pe.edu.upc.entity.Seleccion seleccion) {
		super();
		this.idPersonalizacion = idPersonalizacion;
		this.nombreAutorVino = nombreAutorVino;
		this.nombreVino = nombreVino;
		this.cantBotellaVino = cantBotellaVino;
		this.seleccion = seleccion;
	}

	public int getIdPersonalizacion() {
		return idPersonalizacion;
	}

	public void setIdPersonalizacion(int idPersonalizacion) {
		this.idPersonalizacion = idPersonalizacion;
	}

	public String getNombreAutorVino() {
		return nombreAutorVino;
	}

	public void setNombreAutorVino(String nombreAutorVino) {
		this.nombreAutorVino = nombreAutorVino;
	}

	public String getNombreVino() {
		return nombreVino;
	}

	public void setNombreVino(String nombreVino) {
		this.nombreVino = nombreVino;
	}

	public int getCantBotellaVino() {
		return cantBotellaVino;
	}

	public void setCantBotellaVino(int cantBotellaVino) {
		this.cantBotellaVino = cantBotellaVino;
	}

	public Seleccion getSeleccion() {
		return seleccion;
	}

	public void setSeleccion(Seleccion seleccion) {
		this.seleccion = seleccion;
	}
	
	

}
