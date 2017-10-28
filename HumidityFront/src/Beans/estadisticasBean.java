package Beans;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import org.primefaces.component.chart.Chart;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import controller.IndexControllerRemote;
import dto.CiudadDto;
import dto.ConfiguracionDto;
import dto.DepartamentoDto;
import dto.HumedadDto;

@ManagedBean(name = "estadisticasBean")
public class estadisticasBean
{

	@EJB
	IndexControllerRemote indexController;

	private LineChartModel dateModel;
	private LineChartModel trendModel;

	private DepartamentoDto departamento;
	private List<HumedadDto> humedades;
	private List<CiudadDto> ciudades;
	private String ciudadMarcada;

	public LineChartModel getDateModel()
	{
		return dateModel;
	}

	public LineChartModel getTrendModel()
	{
		return trendModel;
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

	public String getCiudadMarcada()
	{
		return this.ciudadMarcada;
	}

	public void setCiudadMarcada(String ciudadMarcada)
	{
		this.ciudadMarcada = ciudadMarcada;
	}

	@PostConstruct
	public void init()
	{
		setCiudades(indexController.consultarTodasLasCiudades());

		setCiudadMarcada("1");

		CargarGraficas();
	}

	public void CargarGraficas()
	{
		setHumedades(indexController.consultarHumedades(getCiudadMarcada()));
		
		consultarEstadisticaDiaMes();
		consultarTendencia();
	}
	
	public void consultarEstadisticaDiaMes()
	{
		dateModel = new LineChartModel();
		LineChartSeries series1 = new LineChartSeries();
		series1.setLabel("Series 1");

		for (HumedadDto hum : humedades)
		{
			Date fecha = new Date(hum.getFecha().getTime());
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String strDate = dateFormat.format(fecha);

			System.out.println("series1.set(\"" + strDate + "\", " + hum.getValor() + ");");

			series1.set(strDate, Integer.parseInt(hum.getValor()));
		}

		dateModel.addSeries(series1);

		dateModel.setTitle("Estadistica Día/Mes");
		dateModel.setZoom(true);
		dateModel.getAxis(AxisType.Y).setLabel("Humedad");
		DateAxis axis = new DateAxis("Fecha");
		axis.setTickAngle(-50);
		// axis.setMax("2017-10-10");
		axis.setTickFormat("%b %#d, %y");

		dateModel.getAxes().put(AxisType.X, axis);
	}

	public void consultarTendencia()
	{
		trendModel = new LineChartModel();
		trendModel.setExtender("trendLineExtender");

		LineChartSeries series1 = new LineChartSeries();
		series1.setLabel("Series 1");

		for (HumedadDto hum : humedades)
		{
			Date fecha = new Date(hum.getFecha().getTime());
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String strDate = dateFormat.format(fecha);

			System.out.println("series1.set(\"" + strDate + "\", " + hum.getValor() + ");");

			series1.set(strDate, Integer.parseInt(hum.getValor()));
		}

		trendModel.addSeries(series1);

		trendModel.setTitle("Tendencia");
		trendModel.setZoom(true);
		trendModel.getAxis(AxisType.Y).setLabel("Humedad");
		DateAxis axis = new DateAxis("Fecha");
		axis.setTickAngle(-50);
		// axis.setMax("2017-02-01");
		axis.setTickFormat("%b %#d, %y");

		trendModel.getAxes().put(AxisType.X, axis);
	}
}
