package com.gelo.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.gelo.hibernate.model.Student;

public class PrimaryKeyDemo {
	
	public static void main(String[] args) {
		// create session factory
				SessionFactory factory = new Configuration()
										 .configure()
										 .addAnnotatedClass(Student.class)
										 .buildSessionFactory();

				// create session
				Session session = factory.getCurrentSession();
			
				
				try {
					// create a student object
					System.out.println("Creating 3 new students...");
					Student student = new Student("Lebron", "James", "lebron.james@gmail.com");
					Student student2 = new Student("James", "Harden", "james.harden@gmail.com");
					// start the transaction
					session.beginTransaction();
					
					// save the student object
					System.out.println("Saving the object");
					session.save(student2);
					
					// commit the transaction
					session.getTransaction().commit();
					
				}
				
				finally {
					factory.close();
				}
				
		}
}

