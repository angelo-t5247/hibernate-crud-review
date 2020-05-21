package com.gelo.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.gelo.hibernate.model.Student;

public class DeleteStudentDemo {
	
	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
								 .configure()
								 .addAnnotatedClass(Student.class)
								 .buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();
		
		
		try {
			int studentId = 5;
			
			// now get a new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();			
			
			// retrieve student based on the id: primary key
			System.out.println("\nGetting student with id: " + studentId);
			
			Student myStudent = session.get(Student.class, studentId);
			
			// delete the student
			// System.out.println("Deleting student: " + myStudent);
			// session.delete(myStudent);
			
			// delete student id=6
			System.out.println("Deleting student id=6");
			session.createQuery("delete from Student where id=6").executeUpdate();
			
			// commit the transaction
			session.getTransaction().commit();
			
		}
		
		finally {
			factory.close();
		}
		
	}
}
