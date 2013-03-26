package kr.pe.goodwilldd.example.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import kr.pe.goodwilldd.example.entity.Item;
import kr.pe.goodwilldd.example.entity.Order;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExampleService {

	private static final Log logger = LogFactory.getLog(ExampleService.class);
	
	@PersistenceContext(type=PersistenceContextType.EXTENDED)
	private EntityManager selectManager;
	
	/* 스레드에 안전하지 않음!! */
	@PersistenceContext(type=PersistenceContextType.TRANSACTION)
	private EntityManager entityManager;

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public void hibernateTest() throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Order order = new Order();
		order.getItems().add(new Item());
		session.save(order);
		session.flush();
//		assertNotNull(order.getId());
	}

	@Transactional
	public Order jpaTest() throws Exception {
		Order order = new Order();
		order.getItems().add(new Item());
		selectManager.persist(order);
		selectManager.flush();
		selectManager.clear();
		Order other = (Order) selectManager.find(Order.class, order.getId());
		
		return other;
	}
	
	@Transactional
	public Order jpaTest2() throws Exception {
		Order order = new Order();
		order.getItems().add(new Item());
		entityManager.persist(order);
		entityManager.flush();
		entityManager.clear();
		Order other = (Order) entityManager.find(Order.class, order.getId());
		
		return other;
	}
}
