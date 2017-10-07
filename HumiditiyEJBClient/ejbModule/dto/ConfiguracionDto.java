package dto;

import java.io.Serializable;

public class ConfiguracionDto implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
