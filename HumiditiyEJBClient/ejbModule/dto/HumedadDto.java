package dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class HumedadDto implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

	private Boolean estado;

	private Timestamp fecha;

	private String valor;

	private String temperatura;

	private String origen;

	private CiudadDto ciudad;

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

	public Timestamp getFecha()
	{
		return fecha;
	}

	public void setFecha(Timestamp fecha)
	{
		this.fecha = fecha;
	}

	public String getValor()
	{
		return valor;
	}

	public void setValor(String valor)
	{
		this.valor = valor;
	}

	public String getTemperatura()
	{
		return temperatura;
	}

	public void setTemperatura(String temperatura)
	{
		this.temperatura = temperatura;
	}

	public String getOrigen()
	{
		return origen;
	}

	public void setOrigen(String origen)
	{
		this.origen = origen;
	}

	public CiudadDto getCiudad()
	{
		return ciudad;
	}

	public void setCiudad(CiudadDto ciudad)
	{
		this.ciudad = ciudad;
	}

}
