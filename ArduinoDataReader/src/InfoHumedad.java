import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Humedad;

public class InfoHumedad 
{
	EntityManager entityManager = null;
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("ArduinoDataReader");

	public List<model.Departamento> ConsultarDepartamentos()
	{
		entityManager = emf.createEntityManager();
		entityManager.getEntityManagerFactory().getCache().evictAll();

		return entityManager.createNamedQuery("Departamento.findAll", model.Departamento.class).getResultList();		
	}
	
	public List<model.Ciudad> ConsultarCiudades(int IdDepartamento)
	{
		return null;		
	}
	
	public boolean Registro(String valor)
	{
		entityManager = emf.createEntityManager();
		entityManager.getEntityManagerFactory().getCache().evictAll();
		entityManager.getTransaction().begin();
		
		Humedad humedadRegistrar = new Humedad();
		humedadRegistrar.setValor(valor);
		
		entityManager.persist(humedadRegistrar);
		entityManager.getTransaction().commit();
		
		entityManager.close();
		
		return true;
	}	
}
