package com.practice.hibernate.demo;

import com.practice.hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteStudentDemo {

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
           // Student myStudent = session.get(Student.class, studentId);
           // session.delete(myStudent);
            System.out.println("Deleting student id=2");
            session.createQuery("delete from Student  where id=2").executeUpdate();
            session.getTransaction().commit();

        }
        finally {
            factory.close();
        }
    }
}
