package pe.edu.upc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name= "Evento")
public class Evento implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idEvento;
	
	@NotEmpty(message="No puede estar vacio")
	@NotBlank(message="No puede estar en blanco")
	@Column(name="dniEvento", length=60, nullable=false)
	private String dniEvento;
	
	
	@NotEmpty(message="No puede estar vacio")
	@NotBlank(message="No puede estar en blanco")
	@Column(name="nombrePatrocinador", length=60, nullable=false)
	private String nombrePatrocinador;
	
	
	@NotEmpty(message="No puede estar vacio")
	@NotBlank(message="No puede estar en blanco")
	@Column(name="nombreEvento", length=60, nullable=false)
	private String nombreEvento;
	
	
	@NotEmpty(message="No puede estar vacio")
	@NotBlank(message="No puede estar en blanco")
	@Column(name="cantPersonas", length=60, nullable=false)
	private String cantPersonas;
	
	/*poner un booleano de poner en que parte se hara el evento fuera o dentro*/
	@NotEmpty(message="No puede estar vacio")
	@NotBlank(message="No puede estar en blanco")
	@Column(name="horaEvento", length=60, nullable=false)
	private String horaEvento;
	
	@NotEmpty(message="No puede estar vacio")
	@NotBlank(message="No puede estar en blanco")
	@Column(name="descripcionEvento", length=60, nullable=false)
	private String descripcionEvento;
	
	private Date diaEvento;

	public Evento() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Evento(int idEvento,
			@NotEmpty(message = "No puede estar vacio") @NotBlank(message = "No puede estar en blanco") String dniEvento,
			@NotEmpty(message = "No puede estar vacio") @NotBlank(message = "No puede estar en blanco") String nombrePatrocinador,
			@NotEmpty(message = "No puede estar vacio") @NotBlank(message = "No puede estar en blanco") String nombreEvento,
			@NotEmpty(message = "No puede estar vacio") @NotBlank(message = "No puede estar en blanco") String cantPersonas,
			@NotEmpty(message = "No puede estar vacio") @NotBlank(message = "No puede estar en blanco") String horaEvento,
			@NotEmpty(message = "No puede estar vacio") @NotBlank(message = "No puede estar en blanco") String descripcionEvento,
			Date diaEvento) {
		super();
		this.idEvento = idEvento;
		this.dniEvento = dniEvento;
		this.nombrePatrocinador = nombrePatrocinador;
		this.nombreEvento = nombreEvento;
		this.cantPersonas = cantPersonas;
		this.horaEvento = horaEvento;
		this.descripcionEvento = descripcionEvento;
		this.diaEvento = diaEvento;
	}

	public int getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(int idEvento) {
		this.idEvento = idEvento;
	}

	public String getDniEvento() {
		return dniEvento;
	}

	public void setDniEvento(String dniEvento) {
		this.dniEvento = dniEvento;
	}

	public String getNombrePatrocinador() {
		return nombrePatrocinador;
	}

	public void setNombrePatrocinador(String nombrePatrocinador) {
		this.nombrePatrocinador = nombrePatrocinador;
	}

	public String getNombreEvento() {
		return nombreEvento;
	}

	public void setNombreEvento(String nombreEvento) {
		this.nombreEvento = nombreEvento;
	}

	public String getCantPersonas() {
		return cantPersonas;
	}

	public void setCantPersonas(String cantPersonas) {
		this.cantPersonas = cantPersonas;
	}

	public String getHoraEvento() {
		return horaEvento;
	}

	public void setHoraEvento(String horaEvento) {
		this.horaEvento = horaEvento;
	}

	public String getDescripcionEvento() {
		return descripcionEvento;
	}

	public void setDescripcionEvento(String descripcionEvento) {
		this.descripcionEvento = descripcionEvento;
	}

	public Date getDiaEvento() {
		return diaEvento;
	}

	public void setDiaEvento(Date diaEvento) {
		this.diaEvento = diaEvento;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	;
	
	
}
