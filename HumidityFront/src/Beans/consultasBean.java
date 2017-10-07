package Beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import controller.IndexControllerRemote;
import dto.CiudadDto;
import dto.ConfiguracionDto;
import dto.DepartamentoDto;
import dto.HumedadDto;

@ManagedBean(name = "consultasBean")
public class consultasBean
{
	@EJB
	IndexControllerRemote indexController;

	private ConfiguracionDto configuracion;
	private DepartamentoDto departamento;
	private List<HumedadDto> humedades;
	private List<CiudadDto> ciudades;
	private List<String> ciudadesSeleccionadas;

	public ConfiguracionDto getConfiguracion()
	{
		return configuracion;
	}

	public void setConfiguracion(ConfiguracionDto configuracion)
	{
		this.configuracion = configuracion;
	}
	
	public List<HumedadDto> getHumedades()
	{
		return humedades;
	}

	public void setHumedades(List<HumedadDto> humedades)
	{
		this.humedades = humedades;
	}

	public DepartamentoDto getDepartamento()
	{
		return departamento;
	}

	public void setDepartamento(DepartamentoDto departamento)
	{
		this.departamento = departamento;
	}

	public List<CiudadDto> getCiudades()
	{
		return ciudades;
	}

	public void setCiudades(List<CiudadDto> ciudades)
	{
		this.ciudades = ciudades;
	}

	public List<String> getCiudadesSeleccionadas()
	{
		return ciudadesSeleccionadas;
	}

	public void setCiudadesSeleccionadas(List<String> ciudadesSeleccionadas)
	{
		this.ciudadesSeleccionadas = ciudadesSeleccionadas;
	}

	@PostConstruct
	public void init()
	{
		// if (!FacesContext.getCurrentInstance().isPostback())
		// {
		setCiudades(indexController.consultarTodasLasCiudades());
		setConfiguracion(indexController.consultarConfiguracion());
		// }
	}

	public void consultarHumedades()
	{
		setHumedades(indexController.consultarHumedades(ciudadesSeleccionadas));
	}
}
