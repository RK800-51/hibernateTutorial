package org.example;

import org.example.businesslogic.HibernateUtil;
import org.example.entity.Address;
import org.example.entity.Employee;
import org.example.entity.Project;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class App {

    public static void main( String[] args )
    {
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        Address address = new Address();
        address.setCountry("DC");
        address.setCity("Gotham City");
        address.setStreet("Arkham street 1");
        address.setPostCode("0987");


        Employee employee = new Employee();
        employee.setFirstName("James");
        employee.setLastName("Gordon");

        Calendar calendar = Calendar.getInstance();
        calendar.set(1939, Calendar.MAY, 1);

        employee.setBirthday(new Date(calendar.getTime().getTime()));
        employee.setAddress(address);

        Project project = new Project();
        project.setTitle("5678");

        Set<Project> projects = new HashSet<Project>();
        projects.add(project);
        employee.setProjects(projects);

        session.save(address);
        session.save(employee);
        session.save(project);

        session.getTransaction().commit();
        HibernateUtil.shutdown();
    }

}