package rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import controller.IndexControllerRemote;
import dto.CiudadDto;
import dto.DepartamentoDto;
import dto.WeatherResponse;

@Stateless
@Path("/humedad")
public class humedadService
{
	@EJB
	IndexControllerRemote indexController;

	@GET
	@Path("/saludar")
	public String saludar()
	{
		return "Hola Mundo";
	}
	
	@GET
	@Path("/actualizar")
	public String actualizarData()
	{
		Client client = ClientBuilder.newClient();
		List<CiudadDto> ciudades = indexController.consultarTodasLasCiudades();
		
		for(CiudadDto ciudad : ciudades)
		{
			System.out.print("http://api.openweathermap.org/data/2.5/weather?appid=146818143f31b332ccc0f1c23cc4ccf1&q=" + ciudad.getNombre());
			
			WebTarget target = client
					.target("http://api.openweathermap.org/data/2.5/weather")
					.queryParam("appid", "146818143f31b332ccc0f1c23cc4ccf1")
					.queryParam("q", ciudad.getNombre());

			WeatherResponse apiResponse = target.request().get(WeatherResponse.class);
			
			indexController.guardarInfoClima(apiResponse.getMain().getHumidity(),apiResponse.getMain().getTemp(), ciudad);
		}
		
		return "<h1>Info guardada!</h1>";
	}

}