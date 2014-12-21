package com.managementsystem.persistence.configuration;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configurable(value = "persistenceConfig")
@EnableTransactionManagement
@PropertySource(value = { "classpath:persistence-mysql.properties" })
public class PersistenceConfig {

	@Autowired
	private Environment env;

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(getDataSource());
		sessionFactory
				.setPackagesToScan(new String[] { "com.managementsystem.entity" });
		sessionFactory.setHibernateProperties(hibernateProperties());

		return sessionFactory;
	}

	private Properties hibernateProperties() {
		Properties properties = new Properties();

		properties.setProperty(org.hibernate.cfg.Environment.DIALECT,
				env.getProperty("hibernate.dialect"));
		properties.setProperty(org.hibernate.cfg.Environment.FORMAT_SQL,
				env.getProperty("hibernate.format_sql"));
		properties.setProperty(org.hibernate.cfg.Environment.HBM2DDL_AUTO,
				env.getProperty("hibernate.hbm2ddl.auto"));
		properties.setProperty(
				org.hibernate.cfg.Environment.GLOBALLY_QUOTED_IDENTIFIERS,
				env.getProperty("hibernate.globally_quoted_identifiers"));
		properties.setProperty(
				org.hibernate.cfg.Environment.C3P0_ACQUIRE_INCREMENT,
				env.getProperty("hibernate.c3p0.acquire_increment"));
		properties.setProperty(
				org.hibernate.cfg.Environment.C3P0_IDLE_TEST_PERIOD,
				env.getProperty("hibernate.c3p0.idle_test_period"));
		properties.setProperty(org.hibernate.cfg.Environment.C3P0_MAX_SIZE,
				env.getProperty("hibernate.c3p0.max_size"));
		properties.setProperty(
				org.hibernate.cfg.Environment.C3P0_MAX_STATEMENTS,
				env.getProperty("hibernate.c3p0.max_statements"));
		properties.setProperty(org.hibernate.cfg.Environment.C3P0_MIN_SIZE,
				env.getProperty("hibernate.c3p0.min_size"));
		properties.setProperty(org.hibernate.cfg.Environment.C3P0_TIMEOUT,
				env.getProperty("hibernate.c3p0.timeout"));
		return properties;
	}

	@Bean
	public DataSource getDataSource() {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		try {
			dataSource.setDriverClass(env.getProperty("jdbc.driverClassName"));
			dataSource.setJdbcUrl(env.getProperty("jdbc.url"));
			dataSource.setUser(env.getProperty("jdbc.username"));
			dataSource.setPassword(env.getProperty("jdbc.username"));
			dataSource.setMinPoolSize(5);
			dataSource.setAcquireIncrement(5);
			dataSource.setMaxPoolSize(20);
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataSource;
	}

	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory);
		return transactionManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
		return new PersistenceExceptionTranslationPostProcessor();
	}
}
