package com.gelo.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.gelo.hibernate.model.Student;

public class QueryStudentDemo {
	
	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
								 .configure()
								 .addAnnotatedClass(Student.class)
								 .buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();
				
		try {
			
			// start the transaction
			session.beginTransaction();
			
			// query students
			List<Student> theStudents = session.createQuery("from Student").list();
									
			// display the students
			displayStudents(theStudents);
			
	// -------------------------------------------------
			
			// query student: lastName='tumz'
			theStudents = session.createQuery("from Student s where s.lastName='tumz'").list();
			
			// display the students
			System.out.println("Students who have a last name of tumz");
			displayStudents(theStudents);
			
	// -------------------------------------------------
			
			
			// query students: lastName='james' or firsName='gelo'
			theStudents = session.createQuery("from Student s where"
							+ " s.lastName='james' OR s.firstName='gelo'").list();
					
			// display the students
			System.out.println("Students who have a lastName of james or firsName is gelo");
			displayStudents(theStudents);
			
			
	// -------------------------------------------------
			
			// query students where email is LIKE '%gmail.com'
			theStudents = session.createQuery("from Student s where"
					 		+ " s.email LIKE '%gmail.com'").list();
			
			// display the students
			System.out.println("Students where email is LIKE '%gmail.com'");
			displayStudents(theStudents);
			
			
			
			// commit the transaction
			session.getTransaction().commit();
			
		}
		
		finally {
			factory.close();
		}
		
	}

	private static void displayStudents(List<Student> theStudents) {
		for(Student tempStudent: theStudents) {
			System.out.println(tempStudent);
		}
	}
}
