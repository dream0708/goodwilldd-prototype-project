package kr.pe.goodwilldd.hibernate;

import javax.sql.DataSource;

import kr.pe.goodwilldd.example.dto.Account;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseFactoryBean;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:root-context.xml")
public class HibernateConfigTest {

	private SessionFactory sessionFactory = new LocalSessionFactoryBuilder(
			datasource()).addAnnotatedClasses(Account.class)
			.buildSessionFactory();

	public DataSource datasource() {
		EmbeddedDatabaseFactoryBean bean = new EmbeddedDatabaseFactoryBean();
		ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
		databasePopulator.addScript(new ClassPathResource(
				"database/hibernate/config/schema.sql"));
		bean.setDatabasePopulator(databasePopulator);
		bean.afterPropertiesSet(); // necessary because
									// EmbeddedDatabaseFactoryBean is a
									// FactoryBean
		return bean.getObject();
	}

	@Test
	public void retrieveAccount() {
		Session session = sessionFactory.openSession(); // not part of a
														// transaction, so we
														// need to open a
														// session manually
		Query query = session.createQuery("from Account a where a.id=:id")
				.setInteger("id", 1);
		Account a = (Account) query.uniqueResult();
		session.close();
		Assert.assertEquals(a.getCashBalance(), 500.0, 0.01);
	}

	@Test
	@Transactional
	public void updateAccount() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Account a where a.id=:id")
				.setInteger("id", 1);
		Account a = (Account) query.uniqueResult();
		a.setName("foo");
	}
}
