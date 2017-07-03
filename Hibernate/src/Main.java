// Hibernate is a library of Java code that enables a Java program to
// read, write, and update data in a relational database.

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

			// Force this program to exit.
			System.exit(0);
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

		// Use Hibernate to add Suburbia to the database.
		Session session = factory.getCurrentSession();
		Transaction transac = session.beginTransaction();
		session.save(suburbia);
		transac.commit();
	}

	/** Updates the population of a country in the world.country table. */
	private static void updateCountry(
			SessionFactory factory, String code, long population) {
		// Use Hibernate to get from the database, the data for the
		// country with the given code.
		Session session = factory.getCurrentSession();
		Transaction transac = session.beginTransaction();
		Query query = session.createQuery("FROM Country WHERE code = :code");
		query.setParameter("code", code);
		Country country = (Country)query.uniqueResult();

		// If that country exists in the database, change the country's
		// population and then use Hibernate to write the new population
		// to the database.
		if (country != null) {
			country.setPopulation(population);
			session.update(country);
		}
		transac.commit();
	}

	/** Deletes a country from the world.country table. */
	private static void deleteCountry(SessionFactory factory, String code) {
		// Use Hibernate to get from the database, the data for the
		// country with the given code.
		Session session = factory.getCurrentSession();
		Transaction transac = session.beginTransaction();
		Query query = session.createQuery("FROM Country WHERE code = :code");
		query.setParameter("code", code);
		Country country = (Country)query.uniqueResult();

		// If that country exists in the database, use Hibernate to
		// delete that country.
		if (country != null) {
			session.delete(country);
		}
		transac.commit();
	}

	/** Shows one country from the world.country table. */
	private static void showCountry(SessionFactory factory, String code) {
		// Use Hibernate to get from the database, the data for the
		// country with the given code.
		Session session = factory.getCurrentSession();
		Transaction transac = session.beginTransaction();
		Query query = session.createQuery("FROM Country WHERE code = :code");
		query.setParameter("code", code);
		Country country = (Country)query.uniqueResult();
		transac.commit();

		// Display the country for the user to see.
		System.out.println(country);
	}

	/** Shows all countries in the world.country table. */
	private static void showCountries(SessionFactory factory) {
		// Use Hibernate to get from the database a list of all countries.
		Session session = factory.getCurrentSession();
		Transaction transac = session.beginTransaction();
		Query query = session.createQuery("FROM Country");
		List allCountries = query.list();
		transac.commit();

		// Display all the countries for the user to see.
		for (int i = 0, size = allCountries.size();  i < size;  ++i) {
			Country c = (Country)allCountries.get(i);
			System.out.println(c);
		}
	}
}
