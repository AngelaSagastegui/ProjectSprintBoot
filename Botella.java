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
@Table(name = "botella")
public class Botella implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTipoBotella;

	@NotEmpty(message = "Ingresa el nombre del Tipo de Botella")
	@Column(name = "nombreTipoBotella", nullable = false, length = 20, unique = true)
	private String nombreTipoBotella;

	public Botella() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Botella(int idTipoBotella,
			@NotEmpty(message = "Ingresa el nombre del Tipo de Botella") String nombreTipoBotella) {
		super();
		this.idTipoBotella = idTipoBotella;
		this.nombreTipoBotella = nombreTipoBotella;
	}

	public int getIdTipoBotella() {
		return idTipoBotella;
	}

	public void setIdTipoBotella(int idTipoBotella) {
		this.idTipoBotella = idTipoBotella;
	}

	public String getNombreTipoBotella() {
		return nombreTipoBotella;
	}

	public void setNombreTipoBotella(String nombreTipoBotella) {
		this.nombreTipoBotella = nombreTipoBotella;
	}


	

}
