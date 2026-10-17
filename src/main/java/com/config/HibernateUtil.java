package com.config;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.model.Insurance;
import com.model.ServiceRecord;
import com.model.Vehicle;



public class HibernateUtil {
	
	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {

		try {

			Configuration configuration = new Configuration();

			// Database configuration
			configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
			configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/vehicle_management_system");
			configuration.setProperty("hibernate.connection.username", "root");
			configuration.setProperty("hibernate.connection.password", "Samir4076p");

			// Hibernate configuration
			configuration.setProperty("hibernate.hbm2ddl.auto", "update");
			configuration.setProperty("hibernate.show_sql", "true");

			// Register entity class
			configuration.addAnnotatedClass(Vehicle.class);
			configuration.addAnnotatedClass(Insurance.class);
			configuration.addAnnotatedClass(ServiceRecord.class);

			// Second Level Cache
			configuration.setProperty("hibernate.cache.use_second_level_cache", "true");
			configuration.setProperty("hibernate.cache.region.factory_class", "jcache");
			configuration.setProperty("hibernate.javax.cache.provider",
					"com.github.benmanes.caffeine.jcache.spi.CaffeineCachingProvider");

			// Build SessionFactory
			sessionFactory = configuration.buildSessionFactory();

		} catch (Exception e) {

			e.printStackTrace();
			throw new RuntimeException("Error creating SessionFactory", e);
		}

		return sessionFactory;
	}

}
