package pe.edu.upc.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "evento")
public class Evento implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idEvento;

	@NotEmpty(message = "Ingresa el nombre del Evento")
	@Column(name = "nombreEvento", nullable = false, length = 70, unique = true)
	private String nombreEvento;


	@NotEmpty(message = "Ingresa el nombre del Patrocinador del evento")
	@Column(name = "nombrePatrocinador", nullable = false, length = 30)
	private String nombrePatrocinador;

	@Min(0)
	@Max(1000)
	@Column(name = "aforoPersonas", nullable = false)
	private int aforoPersonas;

	@NotEmpty(message="Ingresa la hora del evento")
	@Column(name="horaEvento", nullable = false, length = 20)
	private String horaEvento;
	
	@NotNull
	@Future(message="No puedes seleccionar un dia que NO existe")
	@Temporal(TemporalType.DATE)
	@Column(name="diaEvento")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date diaEvento;

	public Evento() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Evento(int idEvento, @NotEmpty(message = "Ingresa el nombre del Evento") String nombreEvento,
			@NotEmpty(message = "Ingresa el nombre del Patrocinador del evento") String nombrePatrocinador,
			@Min(0) @Max(1000) int aforoPersonas, @NotEmpty(message = "Ingresa la hora del evento") String horaEvento,
			@NotNull @Future(message = "No puedes seleccionar un dia que NO existe") Date diaEvento) {
		super();
		this.idEvento = idEvento;
		this.nombreEvento = nombreEvento;
		this.nombrePatrocinador = nombrePatrocinador;
		this.aforoPersonas = aforoPersonas;
		this.horaEvento = horaEvento;
		this.diaEvento = diaEvento;
	}

	public int getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(int idEvento) {
		this.idEvento = idEvento;
	}

	public String getNombreEvento() {
		return nombreEvento;
	}

	public void setNombreEvento(String nombreEvento) {
		this.nombreEvento = nombreEvento;
	}

	public String getNombrePatrocinador() {
		return nombrePatrocinador;
	}

	public void setNombrePatrocinador(String nombrePatrocinador) {
		this.nombrePatrocinador = nombrePatrocinador;
	}

	public int getAforoPersonas() {
		return aforoPersonas;
	}

	public void setAforoPersonas(int aforoPersonas) {
		this.aforoPersonas = aforoPersonas;
	}

	public String getHoraEvento() {
		return horaEvento;
	}

	public void setHoraEvento(String horaEvento) {
		this.horaEvento = horaEvento;
	}

	public Date getDiaEvento() {
		return diaEvento;
	}

	public void setDiaEvento(Date diaEvento) {
		this.diaEvento = diaEvento;
	}

	

}
