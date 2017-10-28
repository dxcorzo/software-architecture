package Beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import controller.IndexControllerRemote;
import dto.HumedadDto;

@ManagedBean(name = "pearsonBean")
public class pearsonBean
{
	@EJB
	IndexControllerRemote indexController;

	public double coeficiente;

	public double getCoeficiente()
	{
		return coeficiente;
	}

	public void setCoeficiente(double coeficiente)
	{
		this.coeficiente = coeficiente;
	}

	@PostConstruct
	public void init()
	{
		List<HumedadDto> humedades = indexController.consultarHumedades();

		int[] xs = new int[humedades.size()];
		int[] ys = new int[humedades.size()];

		for(int i = 0; i < humedades.size(); i++)
		{
			xs[i] = humedades.get(i).getCiudad().getId(); 
			ys[i] = Integer.parseInt(humedades.get(i).getValor());
		}
		
		setCoeficiente(Correlation(xs, ys));
	}

	// tomado de
	// https://stackoverflow.com/questions/28428365/how-to-find-correlation-between-two-integer-arrays-in-java
	private static double Correlation(int[] xs, int[] ys)
	{
		double sx = 0.0;
		double sy = 0.0;
		double sxx = 0.0;
		double syy = 0.0;
		double sxy = 0.0;

		int n = xs.length;

		for (int i = 0; i < n; ++i)
		{
			double x = xs[i];
			double y = ys[i];

			sx += x;
			sy += y;
			sxx += x * x;
			syy += y * y;
			sxy += x * y;
		}

		// covariation
		double cov = sxy / n - sx * sy / n / n;
		// standard error of x
		double sigmax = Math.sqrt(sxx / n - sx * sx / n / n);
		// standard error of y
		double sigmay = Math.sqrt(syy / n - sy * sy / n / n);

		// correlation is just a normalized covariation
		return cov / sigmax / sigmay;
	}
}
