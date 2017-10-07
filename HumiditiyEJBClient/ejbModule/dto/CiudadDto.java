package dto;

import java.io.Serializable;

public class CiudadDto implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Integer id;

	private Boolean estado;

	private String nombre;

	private DepartamentoDto departamento;

	public DepartamentoDto getDepartamento()
	{
		return departamento;
	}

	public void setDepartamento(DepartamentoDto departamento)
	{
		this.departamento = departamento;
	}

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
}
