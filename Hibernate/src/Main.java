import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class Main {
	public static void main(String[] args) {
		try {
			Configuration config = new Configuration();
			config.addAnnotatedClass(Country.class);
			config.configure();
			SessionFactory factory = config.buildSessionFactory();
//			showCountries(factory);

			showCountry(factory, "SUB");
			addCountry(factory);
			showCountry(factory, "SUB");
			updateCountry(factory, "SUB", 97);
			showCountry(factory, "SUB");
			deleteCountry(factory, "SUB");
			showCountry(factory, "SUB");
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/** Adds the fictional country of Suburbia to the world.country table. */
	private static void addCountry(SessionFactory factory) {
		// Create a Country object for the fictional country of Suburbia.
		Country suburbia = new Country();
		suburbia.setCode("SUB");
		suburbia.setName("Suburbia");
		suburbia.setContinent("North America");
		suburbia.setRegion("Western USA");
		suburbia.setPopulation(1L);
		suburbia.setSurfaceArea(0.25);

		// Add Suburbia to the database.
		Session session = factory.getCurrentSession();
		Transaction transac = session.beginTransaction();
		session.save(suburbia);
		transac.commit();
	}

	/** Shows one country from the world.country table. */
	private static void showCountry(SessionFactory factory, String code) {
		Session session = factory.getCurrentSession();
		Transaction transac = session.beginTransaction();
		Query query = session.createQuery("FROM Country WHERE code = :code");
		query.setParameter("code", code);
		Country country = (Country)query.uniqueResult();
		transac.commit();
		System.out.println(country);
	}

	/** Updates the population of a country in the world.country table. */
	private static void updateCountry(
			SessionFactory factory, String code, long population) {
		Session session = factory.getCurrentSession();
		Transaction transac = session.beginTransaction();
		Query query = session.createQuery("FROM Country WHERE code = :code");
		query.setParameter("code", code);
		Country country = (Country)query.uniqueResult();
		if (country != null) {
			country.setPopulation(population);
			session.update(country);
		}
		transac.commit();
	}

	/** Deletes a country from the world.country table. */
	private static void deleteCountry(SessionFactory factory, String code) {
		Session session = factory.getCurrentSession();
		Transaction transac = session.beginTransaction();
		Query query = session.createQuery("FROM Country WHERE code = :code");
		query.setParameter("code", code);
		Country country = (Country)query.uniqueResult();
		if (country != null) {
			session.delete(country);
		}
		transac.commit();
	}

	/** Shows all countries in the world.country table. */
	private static void showCountries(SessionFactory factory) {
		Session session = factory.getCurrentSession();
		Transaction transac = session.beginTransaction();
		Query query = session.createQuery("FROM Country");
		List allCountries = query.list();
		for (int i = 0, size = allCountries.size();  i < size;  ++i) {
			Country c = (Country)allCountries.get(i);
			System.out.println(c);
		}
		transac.commit();
	}
}
