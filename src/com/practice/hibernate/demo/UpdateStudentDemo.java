package com.practice.hibernate.demo;

import com.practice.hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UpdateStudentDemo {

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
            int studentId = 1;
            System.out.println("\nGetting student with id: " + studentId);
            Student myStudent = session.get(Student.class, studentId);
            System.out.println("\nGet complete: " + myStudent);
            System.out.println("\nUpdating student...");
            myStudent.setFirstName("Zina");
            session.getTransaction().commit();

            session = factory.getCurrentSession();
            session.beginTransaction();
            System.out.println("Updating email for all students");
            session.createQuery("update Student set email='123@gmail.com'").executeUpdate();
            session.getTransaction().commit();



        }
        finally {
            factory.close();
        }
    }
}
