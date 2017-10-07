package controller;

import java.util.List;

import javax.ejb.Remote;

import dto.CiudadDto;
import dto.ConfiguracionDto;
import dto.DepartamentoDto;
import dto.HumedadDto;
import dto.WeatherResponse;


@Remote
public interface IndexControllerRemote
{
	public DepartamentoDto consultarDepartamento(int id);
	
	public List<CiudadDto> consultarTodasLasCiudades();
	
	public List<HumedadDto> consultarHumedades(List<String> ciudadesSeleccionadas);

	public void guardarInfoClima(Integer humedad, Double temperatura, CiudadDto ciudad);
	
	public ConfiguracionDto consultarConfiguracion();
}
