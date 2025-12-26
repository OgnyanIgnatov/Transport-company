package org.example.dao;

import org.example.configuration.SessionFactoryUtil;
import org.example.dto.ClientDto;
import org.example.dto.PassengerServiceDto;
import org.example.dto.PaymentDto;
import org.example.entity.*;
import org.example.dto.EmployeeDto;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
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
            employee.setTelephoneNumber(employeeDto.getTelephoneNumber());
            employee.setIDNumber(employeeDto.getIDNumber());
            employee.setCategory(employeeDto.getCategory());
            employee.setSalary(employeeDto.getSalary());
            session.persist(employee);
            transaction.commit();
        }
    }

    public static List<EmployeeDto> getEmployees() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "SELECT new org.example.dto.EmployeeDto(e.id, e.firstName, e.lastName, e.telephoneNumber, e.IDNumber, e.category, e.salary) " +
                                    "FROM Employee e",
                            EmployeeDto.class)
                    .getResultList();
        }
    }

    public static EmployeeDto getEmployee(long id) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "SELECT new org.example.dto.EmployeeDto(e.id, e.firstName, e.lastName, e.telephoneNumber, e.IDNumber, e.category, e.salary) " +
                                    "FROM Employee e " +
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
            employee.setTelephoneNumber(employeeDto.getTelephoneNumber());
            employee.setIDNumber(employeeDto.getIDNumber());
            employee.setCategory(employeeDto.getCategory());
            employee.setSalary(employeeDto.getSalary());
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

    public static void assignSalaryToEmployee(double salary, long employeeId){
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Employee employee = session.find(Employee.class, employeeId);
            employee.setSalary(salary);
            session.persist(employee);
            transaction.commit();
        }
    }

    public static void buyTicketForPassengerService(long psId, long clientId){
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            PassengerService passengerService = session.find(PassengerService.class, psId);
            Client client = session.find(Client.class, clientId);

            if(passengerService.getPassengerCount() == 0) throw new IllegalStateException("Passenger Count is maximum");
            long passengerCount = passengerService.getPassengerCount();
            passengerService.setPassengerCount(passengerCount - 1);

            PaymentDto paymentDto = new PaymentDto();
            paymentDto.setPaymentDate(LocalDate.now());
            paymentDto.setPrice(passengerService.getServicePrice());
            paymentDto.setServiceId(passengerService.getId());
            paymentDto.setClientId(client.getId());

            PaymentDao.createPayment(paymentDto);
            session.persist(passengerService);
            transaction.commit();
        }
    }

    public static void buyTicketForTransportService(long tsId, long clientId, double packageWeight){
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            TransportService transportService = session.find(TransportService.class, tsId);
            Client client = session.find(Client.class, clientId);

            if(transportService.getWeight() > 44) throw new IllegalStateException("Vehicle has reached max capacity");
            double vehicleWeight = transportService.getWeight();
            transportService.setWeight(vehicleWeight - packageWeight);

            PaymentDto paymentDto = new PaymentDto();
            paymentDto.setPaymentDate(LocalDate.now());
            paymentDto.setPrice(transportService.getServicePrice());
            paymentDto.setServiceId(transportService.getId());
            paymentDto.setClientId(client.getId());

            PaymentDao.createPayment(paymentDto);
            session.persist(transportService);
            transaction.commit();
        }
    }

    public static List<EmployeeDto> sortEmployeesByCategory(){
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "SELECT new org.example.dto.EmployeeDto(e.id, e.firstName, e.lastName, e.telephoneNumber, e.IDNumber, e.category, e.salary) " +
                                    "FROM Employee e " +
                                    "ORDER BY e.category",
                            EmployeeDto.class)
                    .getResultList();
        }
    }

    public static List<EmployeeDto> sortEmployeesBySalary(){
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "SELECT new org.example.dto.EmployeeDto(e.id, e.firstName, e.lastName, e.telephoneNumber, e.IDNumber, e.category, e.salary) " +
                                    "FROM Employee e " +
                                    "ORDER BY e.salary",
                            EmployeeDto.class)
                    .getResultList();
        }
    }
}
