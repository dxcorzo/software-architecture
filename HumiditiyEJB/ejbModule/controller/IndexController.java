package controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dto.CiudadDto;
import dto.ConfiguracionDto;
import dto.DepartamentoDto;
import dto.HumedadDto;
import dto.WeatherResponse;
import model.Ciudad;
import model.Configuracion;
import model.Departamento;
import model.Humedad;

/**
 * Session Bean implementation class IndexController
 */
@Stateless
@LocalBean
public class IndexController implements IndexControllerRemote
{
	@PersistenceContext
	EntityManager entityManager;

	private final int DEFAULT_API_KEY_ID = 1;

	/**
	 * Default constructor.
	 */
	public IndexController()
	{
		// TODO Auto-generated constructor stub
	}

	@Override
	public DepartamentoDto consultarDepartamento(int id)
	{
		Departamento depto = entityManager.find(Departamento.class, id);

		DepartamentoDto deptoDto = new DepartamentoDto();

		deptoDto.setId(depto.getId());
		deptoDto.setNombre(depto.getNombre());
		deptoDto.setEstado(depto.getEstado());

		return deptoDto;
	}

	@Override
	public List<CiudadDto> consultarTodasLasCiudades()
	{
		List<Ciudad> ciudades = entityManager.createNamedQuery("Ciudad.findAll", model.Ciudad.class).getResultList();
		List<CiudadDto> retorno = new ArrayList<CiudadDto>();

		for (Ciudad ciudad : ciudades)
		{
			DepartamentoDto deptoDto = new DepartamentoDto();
			CiudadDto ciudadDto = new CiudadDto();
			ciudadDto.setId(ciudad.getId());
			ciudadDto.setNombre(ciudad.getNombre());
			ciudadDto.setEstado(ciudad.getEstado());

			deptoDto.setNombre(ciudad.getDepartamento().getNombre());
			ciudadDto.setDepartamento(deptoDto);

			retorno.add(ciudadDto);
		}

		return retorno;
	}

	@Override
	public List<HumedadDto> consultarHumedades(String ciudadSeleccionada)
	{
		return consultarHumedades(new ArrayList<String>(Arrays.asList(ciudadSeleccionada)));
	}

	@Override
	public List<HumedadDto> consultarHumedades(List<String> ciudadesSeleccionadas)
	{
		List<HumedadDto> retorno = new ArrayList<HumedadDto>();

		for (String idCiudad : ciudadesSeleccionadas)
		{
			List<Humedad> humedades = entityManager.createNamedQuery("Humedad.findAllFilter", model.Humedad.class)
			        .setParameter("ciudad", Integer.parseInt(idCiudad)).getResultList();

			for (Humedad item : humedades)
			{
				HumedadDto humedad = new HumedadDto();
				CiudadDto ciudadDto = new CiudadDto();
				DepartamentoDto depto = new DepartamentoDto();

				humedad.setId(item.getId());
				humedad.setFecha(item.getFecha());
				humedad.setEstado(item.getEstado());
				humedad.setTemperatura(item.getTemperatura());
				humedad.setValor(item.getValor());
				humedad.setOrigen(item.getOrigen());

				depto.setId(item.getCiudad().getDepartamento().getId());
				depto.setNombre(item.getCiudad().getDepartamento().getNombre());

				ciudadDto.setNombre(item.getCiudad().getNombre());
				ciudadDto.setEstado(item.getCiudad().getEstado());
				ciudadDto.setId(item.getCiudad().getId());
				ciudadDto.setDepartamento(depto);

				humedad.setCiudad(ciudadDto);

				retorno.add(humedad);
			}
		}

		return retorno;
	}

	@Override
	public List<HumedadDto> consultarHumedades()
	{
		List<HumedadDto> retorno = new ArrayList<HumedadDto>();

		List<Humedad> humedades = entityManager.createNamedQuery("Humedad.findAll", model.Humedad.class)
		        .getResultList();

		for (Humedad item : humedades)
		{
			HumedadDto humedad = new HumedadDto();
			CiudadDto ciudadDto = new CiudadDto();
			DepartamentoDto depto = new DepartamentoDto();

			humedad.setId(item.getId());
			humedad.setFecha(item.getFecha());
			humedad.setEstado(item.getEstado());
			humedad.setTemperatura(item.getTemperatura());
			humedad.setValor(item.getValor());
			humedad.setOrigen(item.getOrigen());

			depto.setId(item.getCiudad().getDepartamento().getId());
			depto.setNombre(item.getCiudad().getDepartamento().getNombre());

			ciudadDto.setNombre(item.getCiudad().getNombre());
			ciudadDto.setEstado(item.getCiudad().getEstado());
			ciudadDto.setId(item.getCiudad().getId());
			ciudadDto.setDepartamento(depto);

			humedad.setCiudad(ciudadDto);

			retorno.add(humedad);
		}

		return retorno;
	}

	@Override
	public void guardarInfoClima(Integer humedad, Double temperatura, CiudadDto ciudad)
	{
		Humedad humedadRegistrar = new Humedad();

		Ciudad ciudadGuardar = entityManager.find(Ciudad.class, ciudad.getId());

		humedadRegistrar.setOrigen("API");
		humedadRegistrar.setEstado(true);
		humedadRegistrar.setValor(humedad.toString());
		humedadRegistrar.setTemperatura(temperatura.toString());
		humedadRegistrar.setCiudad(ciudadGuardar);
		humedadRegistrar.setFecha(new Timestamp(System.currentTimeMillis()));

		entityManager.persist(humedadRegistrar);
	}

	@Override
	public ConfiguracionDto consultarConfiguracion()
	{
		Configuracion config = entityManager.find(Configuracion.class, DEFAULT_API_KEY_ID);
		ConfiguracionDto retorno = new ConfiguracionDto();

		retorno.setId(config.getId());
		retorno.setEstado(config.getEstado());
		retorno.setApiKey(config.getApiKey());

		return retorno;
	}
}
