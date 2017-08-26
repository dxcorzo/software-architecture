package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the humedades database table.
 * 
 */
@Entity
@Table(name="humedades")
@NamedQuery(name="Humedad.findAll", query="SELECT h FROM Humedad h")
public class Humedad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Basic(optional = true)
	private Boolean estado;

	@Basic(optional = true)
	private Timestamp fecha;

	@Basic(optional = false)
	private String valor;
	
	@Basic(optional = false)
	private String temperatura;

	//bi-directional many-to-one association to Ciudade
	@ManyToOne
	@JoinColumn(name="idciudad")
	private Ciudad ciudad;

	public Humedad() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getEstado() {
		return this.estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public Timestamp getFecha() {
		return this.fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public String getValor() {
		return this.valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public void setTemperatura(String temperatura) {
		this.temperatura = temperatura;
	}
	
	public String getTemperatura() {
		return this.temperatura;
	}
	

	public Ciudad getCiudad() {
		return this.ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

}