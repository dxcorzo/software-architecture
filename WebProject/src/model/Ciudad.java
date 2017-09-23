package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ciudades database table.
 * 
 */
@Entity
@Table(name="ciudades")
@NamedQuery(name="Ciudad.findAll", query="SELECT c FROM Ciudad c")
public class Ciudad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private Boolean estado;

	private String nombre;

	//bi-directional many-to-one association to Departamento
	@ManyToOne
	@JoinColumn(name="iddepartamento")
	private Departamento departamento;

	//bi-directional many-to-one association to Humedade
	@OneToMany(mappedBy="ciudad")
	private List<Humedad> humedades;

	public Ciudad() {
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

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Departamento getDepartamento() {
		return this.departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public List<Humedad> getHumedades() {
		return this.humedades;
	}

	public void setHumedades(List<Humedad> humedades) {
		this.humedades = humedades;
	}

	public Humedad addHumedade(Humedad humedad) {
		getHumedades().add(humedad);
		humedad.setCiudad(this);

		return humedad;
	}

	public Humedad removeHumedad(Humedad humedad) {
		getHumedades().remove(humedad);
		humedad.setCiudad(null);

		return humedad;
	}
	
	@Override
	public String toString()
	{
		return getNombre();
	}

}