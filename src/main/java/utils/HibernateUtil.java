package utils;

import java.io.File;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.metamodel.MetadataSources;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {
	private static SessionFactory _sessionFactory = null;

	public static synchronized void buildSessionFactory(String configFile_)
		throws HibernateException
	{
		try {
			File configFile = new File(configFile_);
			// Create the SessionFactory from hibernate.cfg.xml
			Configuration config = new Configuration();
			ServiceRegistry serviceRegistry;
			MetadataSources metadataSources;

			config.configure(configFile);
			serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();

			_sessionFactory = config.buildSessionFactory(serviceRegistry);
		} catch(HibernateException e) {
			throw e;
		}
	}

	public static synchronized SessionFactory getSessionFactory()
	{
		return _sessionFactory;
	}
}
