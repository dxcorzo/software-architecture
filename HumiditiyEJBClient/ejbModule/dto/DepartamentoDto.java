package dto;

import java.io.Serializable;
import java.util.List;

public class DepartamentoDto implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Integer id;

	private Boolean estado;

	private String nombre;
	
	private List<CiudadDto> ciudades;

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

	public String getNombre()
	{
		return nombre;
	}

	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}

	public List<CiudadDto> getCiudades()
	{
		return ciudades;
	}

	public void setCiudades(List<CiudadDto> ciudades)
	{
		this.ciudades = ciudades;
	}
}
