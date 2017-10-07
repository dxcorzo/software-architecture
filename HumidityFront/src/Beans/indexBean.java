package Beans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import controller.IndexControllerRemote;
import dto.DepartamentoDto;

@ManagedBean(name = "indexBean")
public class indexBean
{
	public String consulta()
	{
		return "consultah.xhtml";
	}

}
