package hello;

import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;
import javax.sql.DataSource;
import javax.naming.NamingException;
import org.hibernate.cfg.Configuration;
import java.lang.*;
import java.util.ArrayList; 
import java.util.List;
import com.microsoft.sqlserver.jdbc.*;
import java.sql.*;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;

public class JpaExample
{
    public static void main(String[] args) throws SQLException, NamingException
    {
        int i = 1; //get connection from Hibernate

        //i = 2; //create connection from jdbc 

        if(i==1){
            //get connection from Hibernate
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
            EntityManager em = emf.createEntityManager();
            Session session = em.unwrap(Session.class);
            session.doWork(new Work(){
                public void execute(Connection conn) throws SQLException {

                    SQLServerDataTable dataTable = new SQLServerDataTable();
                    dataTable.addColumnMetadata("Id", java.sql.Types.INTEGER);
                    dataTable.addColumnMetadata("Code", java.sql.Types.VARCHAR);
                    dataTable.addColumnMetadata("Title", java.sql.Types.VARCHAR);   
                    
                    dataTable.addRow(1, null, null);
            
                    try (SQLServerCallableStatement stmt = (SQLServerCallableStatement) conn.prepareCall("{call InputUDT(?)}")) {
                        stmt.setStructured(1, "dbo.CourseUDT", dataTable);
                        ResultSet rs = stmt.executeQuery();
            
                        while(rs.next())
                        {
                            int Id = rs.getInt("Id");
                            boolean Active = rs.getBoolean("Active");
                
                            if(rs.wasNull())
                                System.out.println("[Active] was null");
                
                            System.out.printf("%d,%b\n", Id,Active);
                        }            
                    } 

                }                

            });
            System.err.println("Exiting from Hibernate/connection");
            return;
        }

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager em = emf.createEntityManager();
        

        /*
        emf.createEntityManager();
        em.getTransaction().begin();

        Course aCourse = new Course(4,true,"JVM101","JVM Basics");
        em.persist(aCourse);


        em.getTransaction().commit();
        em.close();
        emf.close();
        */
        
        /*
        List<CourseUDT> courses = new ArrayList<>();
        courses.add(new CourseUDT(1, null, null));

        StoredProcedureQuery query = em.createStoredProcedureQuery("InputUDT");
        query.registerStoredProcedureParameter("p1", hello.CourseUDT[].class, javax.persistence.ParameterMode.IN);

        query.setParameter("p1", courses.toArray());
        */

        String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=Univ;encrypt=false;integratedSecurity=true;trustServerCertificate=true;";
        Connection conn = DriverManager.getConnection(connectionUrl);

        SQLServerDataTable dataTable = new SQLServerDataTable();
        dataTable.addColumnMetadata("Id", java.sql.Types.INTEGER);
        dataTable.addColumnMetadata("Code", java.sql.Types.VARCHAR);
        dataTable.addColumnMetadata("Title", java.sql.Types.VARCHAR);   
        
        dataTable.addRow(1, null, null);

        try (SQLServerCallableStatement stmt = (SQLServerCallableStatement) conn.prepareCall("{call InputUDT(?)}")) {
            stmt.setStructured(1, "dbo.CourseUDT", dataTable);
            ResultSet rs = stmt.executeQuery();

            while(rs.next())
            {
                int Id = rs.getInt("Id");
                boolean Active = rs.getBoolean("Active");
    
                if(rs.wasNull())
                    System.out.println("[Active] was null");
    
                System.out.printf("%d,%b\n", Id,Active);
            }            
        }        
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