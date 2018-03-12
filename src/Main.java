import java.util.List; 
import java.util.Date;
import java.util.Iterator; 

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;

import java.math.BigDecimal;
import java.util.*;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {
	private static SessionFactory factory;
	public static void main(String[] args) {

		try {
			factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		} catch (Throwable ex) { 
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex); 
		}
		BigDecimal price = new BigDecimal("1115.37");

		Main me = new Main();


		me.addEmployee("Adidas", "Sepatu", "Running", 15000);

//		me.listEmployees();
//		me.listEmployee2();

	}

	/* Method to  READ all the employees */
	public void listEmployees( ){
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("FROM Product where category = :category");
			query.setParameter("category", "Soccer");
			List employees = query.list(); 
			for (Iterator iterator = employees.iterator(); iterator.hasNext();){
				Product employee = (Product) iterator.next(); 
				System.out.print("Name: " + employee.getName()); 
				System.out.print("  Description: " + employee.getDescription()); 
				System.out.println("  Price: " + employee.getPrice()); 
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		} finally {
			session.close(); 
		}
	}

	public void listEmployee2()
	{
		Session session = factory.openSession();
				
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			List employees = session.createCriteria(Product.class)
					.addOrder(Order.asc("Category"))
					.list();
			
			for (Iterator iterator = employees.iterator(); iterator.hasNext();){
				Product employee = (Product) iterator.next(); 
				System.out.print("Name: " + employee.getName()); 
				System.out.print("  Description: " + employee.getDescription()); 
				System.out.println("  Price: " + employee.getPrice()); 
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		} finally {
			session.close(); 
		}
	}
	
	/* Method to CREATE an employee in the database */
	public Integer addEmployee(String fname, String lname, String cat, int salary){
		Session session = factory.openSession();
		Transaction tx = null;
		Integer employeeID = null;

		try {
			tx = session.beginTransaction();
			Employee employee = new Employee(fname, lname, salary);
			employeeID = (Integer) session.save(employee); 
			tx.commit();
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		} finally {
			session.close(); 
		}
		return employeeID;
	}

}
