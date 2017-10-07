package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "configuracion")
@NamedQuery(name = "Configuracion.findAll", query = "SELECT c FROM Configuracion c")
public class Configuracion implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private Boolean estado;

	private String apiKey;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Boolean getEstado()
	{
		return estado;
	}

	public void setEstado(Boolean estado)
	{
		this.estado = estado;
	}

	public String getApiKey()
	{
		return apiKey;
	}

	public void setApiKey(String apiKey)
	{
		this.apiKey = apiKey;
	}

}
