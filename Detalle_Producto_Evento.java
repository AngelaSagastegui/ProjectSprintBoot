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
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "detalle")
public class Detalle_Producto_Evento implements Serializable {


	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idDetalle;

	@ManyToOne
	@JoinColumn(name = "idProducto")
	@NotNull
	private Producto producto;

	@ManyToOne
	@JoinColumn(name = "idEvento")
	private Evento evento;

	@Min(2)
	@Max(10000)
	@Column(name = "cantidadDetalle", nullable = false)
	private int cantidadDetalle;

	@NotEmpty(message = "Ingresa el detalle de producto que estara en el evento")
	@Column(name = "descripcionDetalle", nullable = false, length = 60)
	private String descripcionDetalle;

	public Detalle_Producto_Evento() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Detalle_Producto_Evento(int idDetalle, @NotNull Producto producto, Evento evento,
			@Min(2) @Max(10000) int cantidadDetalle,
			@NotEmpty(message = "Ingresa el detalle de producto que estara en el evento") String descripcionDetalle) {
		super();
		this.idDetalle = idDetalle;
		this.producto = producto;
		this.evento = evento;
		this.cantidadDetalle = cantidadDetalle;
		this.descripcionDetalle = descripcionDetalle;
	}

	public int getIdDetalle() {
		return idDetalle;
	}

	public void setIdDetalle(int idDetalle) {
		this.idDetalle = idDetalle;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public int getCantidadDetalle() {
		return cantidadDetalle;
	}

	public void setCantidadDetalle(int cantidadDetalle) {
		this.cantidadDetalle = cantidadDetalle;
	}

	public String getDescripcionDetalle() {
		return descripcionDetalle;
	}

	public void setDescripcionDetalle(String descripcionDetalle) {
		this.descripcionDetalle = descripcionDetalle;
	}


	
}
