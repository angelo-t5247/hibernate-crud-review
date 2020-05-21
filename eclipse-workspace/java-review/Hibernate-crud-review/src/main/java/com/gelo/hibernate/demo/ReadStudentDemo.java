package com.gelo.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.gelo.hibernate.model.Student;

public class ReadStudentDemo {
	
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
			System.out.println("Creating new student object...");
			Student student = new Student("Road", "Runner", "road.runner@gmail.com");
			
			// start the transaction
			session.beginTransaction();
			
			// save the student object
			System.out.println("Saving the object");
			session.save(student);
			
			// commit the transaction
			session.getTransaction().commit();
			
			// find out the student's id: primary key
			System.out.println("Saved student. Generated id: " + student.getId());
				
			
			// now get a new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();			
			
			// retrieve student based on the id: primary key
			System.out.println("\nGetting student with id: " + student.getId());
			
			Student readStudent = session.get(Student.class, student.getId());
			
			System.out.println("Get complete: " + readStudent);
			
			// commit the transaction
			session.getTransaction().commit();
			
		}
		
		finally {
			factory.close();
		}
		
	}
}
