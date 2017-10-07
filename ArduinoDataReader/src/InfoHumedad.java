import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Humedad;

public class InfoHumedad
{
	EntityManager entityManager = null;
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("ArduinoDataReader");

	public InfoHumedad()
	{
		entityManager = emf.createEntityManager();
	}

	public List<model.Departamento> ConsultarDepartamentos()
	{
		List<model.Departamento> retorno = entityManager.createNamedQuery("Departamento.findAll", model.Departamento.class).getResultList();
		//entityManager.close();
		
		return retorno;
	}

	public boolean RegistrarHumedad(String humedad, String temperatura, model.Ciudad ciudad)
	{
		entityManager.getEntityManagerFactory().getCache().evictAll();
		entityManager.getTransaction().begin();

		Humedad humedadRegistrar = new Humedad();

		humedadRegistrar.setOrigen("ARDUINO");
		humedadRegistrar.setEstado(true);
		humedadRegistrar.setValor(humedad);
		humedadRegistrar.setTemperatura(temperatura);
		humedadRegistrar.setCiudad(ciudad);
		humedadRegistrar.setFecha(new Timestamp(System.currentTimeMillis()));

		entityManager.persist(humedadRegistrar);
		entityManager.getTransaction().commit();

		//entityManager.close();

		return true;
	}
}
