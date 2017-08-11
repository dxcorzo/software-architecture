package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the departamentos database table.
 * 
 */
@Entity
@Table(name="departamentos")
@NamedQuery(name="Departamento.findAll", query="SELECT d FROM Departamento d")
public class Departamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private Boolean estado;

	private String nombre;

	//bi-directional many-to-one association to Ciudade
	@OneToMany(mappedBy="departamento")
	private List<Ciudad> ciudades;

	public Departamento() {
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

	public List<Ciudad> getCiudades() {
		return this.ciudades;
	}

	public void setCiudades(List<Ciudad> ciudades) {
		this.ciudades = ciudades;
	}

	public Ciudad addCiudade(Ciudad ciudade) {
		getCiudades().add(ciudade);
		ciudade.setDepartamento(this);

		return ciudade;
	}

	public Ciudad removeCiudade(Ciudad ciudad) {
		getCiudades().remove(ciudad);
		ciudad.setDepartamento(null);

		return ciudad;
	}

}