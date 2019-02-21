package com.practice.hibernate.demo;

import com.practice.hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ReadStudentDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                                .configure("hibernate.cfg.xml")
                                .addAnnotatedClass(Student.class)
                                .buildSessionFactory();
        // create a session
        Session session = factory.getCurrentSession();

        try{
            // use the session object to save Java object
            System.out.println("Creating new student object...");
            Student tempStudent = new Student("Hernan", "Chrespo", "chrespo@gmail.com");
            session.beginTransaction();
            System.out.println("Saving the student...");
            System.out.println(tempStudent);
            session.save(tempStudent);
            session.getTransaction().commit();

            System.out.println("Saved student. Generated id: " + tempStudent.getId());

            // get a new session and start transaction
            session = factory.getCurrentSession();
            session.beginTransaction();

            // retrieve student based on the id
            System.out.println("\nGetting student with id " + tempStudent.getId());
            Student myStudent = session.get(Student.class, tempStudent.getId());
            System.out.println("\nGet complete: " + myStudent);
            session.getTransaction().commit();

            System.out.println("Done!");
        }
        finally {
            factory.close();
        }
    }
}
