package org.example.dao;

import org.example.configuration.SessionFactoryUtil;
import org.example.dto.ClientDto;
import org.example.entity.Client;
import org.example.dto.EmployeeDto;
import org.example.entity.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PersonDao {
    // ------------CLIENT--------------
    public static void createClient(ClientDto clientDto) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Client client = new Client();
            client.setFirstName(clientDto.getFirstName());
            client.setLastName(clientDto.getLastName());
            client.setTelephoneNumber(clientDto.getTelephoneNumber());
            client.setBirthDate(clientDto.getBirthDate());
            session.persist(client);
            transaction.commit();
        }
    }

    public static List<ClientDto> getClients() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "SELECT new org.example.dto.ClientDto(c.id, c.firstName, c.lastName, c.telephoneNumber, c.birthDate) FROM Client c",
                            ClientDto.class)
                    .getResultList();
        }
    }

    public static ClientDto getClient(long id) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "SELECT new org.example.dto.ClientDto(c.id, c.firstName, c.lastName, c.telephoneNumber, c.birthDate) FROM Client c " +
                                    "WHERE c.id = :id", ClientDto.class)
                    .setParameter("id", id)
                    .getSingleResult();
        }
    }

    public static void updateClient(long id, ClientDto clientDto) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Client client = session.find(Client.class, id);
            client.setFirstName(clientDto.getFirstName());
            client.setLastName(clientDto.getLastName());
            client.setTelephoneNumber(clientDto.getTelephoneNumber());
            client.setBirthDate(clientDto.getBirthDate());
            session.persist(client);
            transaction.commit();
        }
    }

    public static void deleteClient(long id) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Client client = session.find(Client.class, id);
            session.remove(client);
            transaction.commit();
        }
    }
    //------------------EMPLOYEE-------------

    public static void createEmployee(EmployeeDto employeeDto) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Employee employee = new Employee();
            employee.setFirstName(employeeDto.getFirstName());
            employee.setLastName(employeeDto.getLastName());
            employee.setCategory(employeeDto.getCategory());
            session.persist(employee);
            transaction.commit();
        }
    }

    public static List<EmployeeDto> getEmployees() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "SELECT new org.example.dto.EmployeeDto(e.id,e.firstName,e.lastName, e.category) FROM Employee e",
                            EmployeeDto.class)
                    .getResultList();
        }
    }

    public static EmployeeDto getEmployee(long id) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "SELECT new org.example.dto.EmployeeDto(e.id,e.firstName,e.lastName, e.category) FROM Employee e " +
                                    "WHERE e.id = :id", EmployeeDto.class)
                    .setParameter("id", id)
                    .getSingleResult();
        }
    }

    public static void updateEmployee(long id, EmployeeDto employeeDto) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Employee employee = session.find(Employee.class, id);
            employee.setFirstName(employeeDto.getFirstName());
            employee.setLastName(employeeDto.getLastName());
            employee.setCategory(employeeDto.getCategory());
            session.persist(employee);
            transaction.commit();
        }
    }

    public static void deleteEmployee(long id) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Employee employee = session.find(Employee.class, id);
            session.remove(employee);
            transaction.commit();
        }
    }
}
