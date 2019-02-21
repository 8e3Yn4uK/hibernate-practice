package com.practice.hibernate.demo;

import com.practice.hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class QueryStudentDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                                .configure("hibernate.cfg.xml")
                                .addAnnotatedClass(Student.class)
                                .buildSessionFactory();
        // create a session
        Session session = factory.getCurrentSession();

        try{
            session.beginTransaction();
            List<Student> theStudents = session.createQuery("from Student").list();
            displayStudents(theStudents);
            theStudents = session.createQuery("from Student s where s.lastName='Nesta'").list();
            System.out.println("\nThe students who have last name of Nesta");
            displayStudents(theStudents);
            System.out.println("\nThe students who have last name Nesta or first name Cristiano");
            theStudents = session.createQuery("from Student s where s.lastName='Nesta' OR s.firstName='Cristiano'").list();
            displayStudents(theStudents);
            System.out.println("\nThe students where email like '%gmail.com");
            theStudents = session.createQuery("from Student s where s.email like '%gmail.com'").list();
            displayStudents(theStudents);


            session.getTransaction().commit();
        }
        finally {
            factory.close();
        }
    }

    private static void displayStudents(List<Student> theStudents) {
        for (Student tempStudent : theStudents){
            System.out.println(tempStudent);
        }
    }
}
