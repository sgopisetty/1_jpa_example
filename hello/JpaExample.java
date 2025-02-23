package hello;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;
import org.hibernate.cfg.Configuration;
import java.lang.*;

import java.util.List;

public class JpaExample
{
    public static void main(String[] args)
    {
        
        // System.out.println("Creating EntityManagerFactory...");
        // EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        // System.out.println("EntityManagerFactory created!");        
        
        
        
        
        //System.out.println("Creating EntityManagerFactory...");
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        //System.out.println("EntityManagerFactory created!");        

        //System.out.println(Thread.currentThread().getContextClassLoader().getResource("META-INF/persistence.xml"));

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager em = emf.createEntityManager();
        

        em.getTransaction().begin();
        
        Course javaCourse = new Course(3,true,"CSJAVA","Java Course");
        em.persist(javaCourse);
        em.getTransaction().commit();
    }

}

/*
set cp=.;D:\projects\learning\java_programming\1_jpa_example\sqljdbc_12.8\enu\jars\mssql-jdbc-12.8.1.jre11.jar;lib\*;
set dllPath=D:\projects\learning\java_programming\1_jpa_example\sqljdbc_12.8\enu\auth\x64
javac -cp "%cp%" hello/JpaExample.java hello/Course.java  && java -Djava.library.path=%dllPath% -cp %cp% hello/JpaExample


Download the following JAR files:
javax.persistence-api-2.2.jar
hibernate-core-5.4.30.Final.jar
hibernate-commons-annotations-5.1.0.Final.jar
sqlserver-9.4.0.jre8.jar


 */