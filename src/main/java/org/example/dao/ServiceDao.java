package org.example.dao;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.example.configuration.SessionFactoryUtil;

import org.example.dto.*;
import org.example.entity.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

public class ServiceDao {

    public static void createPassengerService(PassengerServiceDto passengerServiceDto){
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            PassengerService passengerService = new PassengerService();
            passengerService.setDepLocation(passengerServiceDto.getDepLocation());
            passengerService.setArrLocation(passengerServiceDto.getArrLocation());
            passengerService.setDepDate(passengerServiceDto.getDepDate());
            passengerService.setArrDate(passengerServiceDto.getArrDate());
            passengerService.setRequiredCategory(passengerServiceDto.getRequiredCategory());
            passengerService.setRequiredVehicleType(passengerServiceDto.getRequiredVehicleType());
            passengerService.setServicePrice(passengerServiceDto.getServicePrice());
            passengerService.setPassengerCount(passengerServiceDto.getPassengerCount());
            session.persist(passengerService);
            transaction.commit();
        }
    }

    public static List<PassengerServiceDto> getPassengerServices(){
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            return session.createQuery(
                    "SELECT new PassengerServiceDto(ps.id, ps.depLocation, ps.arrLocation, ps.depDate, ps.arrDate, ps.requiredCategory, ps.requiredVehicleType, ps.servicePrice, ps.passengerCount) " +
                               "FROM PassengerService ps", PassengerServiceDto.class)
                    .getResultList();
        }
    }

    public static PassengerServiceDto getPassengerService(long psId){
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            return session.createQuery(
                            "SELECT new PassengerServiceDto(ps.id, ps.depLocation, ps.arrLocation, ps.depDate, ps.arrDate, ps.requiredCategory, ps.requiredVehicleType, ps.servicePrice, ps.passengerCount) " +
                                    "FROM PassengerService ps " +
                                    "WHERE ps.id = :id", PassengerServiceDto.class)
                    .setParameter("id", psId)
                    .getSingleResult();
        }
    }

    public static void updatePassengerService(long psId, PassengerServiceDto passengerServiceDto){
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            PassengerService passengerService = session.find(PassengerService.class, psId);
            passengerService.setDepLocation(passengerServiceDto.getDepLocation());
            passengerService.setArrLocation(passengerServiceDto.getArrLocation());
            passengerService.setDepDate(passengerServiceDto.getDepDate());
            passengerService.setArrDate(passengerServiceDto.getArrDate());
            passengerService.setRequiredCategory(passengerServiceDto.getRequiredCategory());
            passengerService.setRequiredVehicleType(passengerServiceDto.getRequiredVehicleType());
            passengerService.setServicePrice(passengerServiceDto.getServicePrice());
            passengerService.setPassengerCount(passengerServiceDto.getPassengerCount());
            session.persist(passengerService);
            transaction.commit();
        }
    }

    public static void deletePassengerService(long psId){
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            PassengerService passengerService = session.find(PassengerService.class, psId);
            session.remove(passengerService);
            transaction.commit();
        }
    }

    public static void createTransportService(TransportServiceDto transportServiceDto){
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            TransportService transportService = new TransportService();
            transportService.setDepLocation(transportServiceDto.getDepLocation());
            transportService.setArrLocation(transportServiceDto.getArrLocation());
            transportService.setDepDate(transportServiceDto.getDepDate());
            transportService.setArrDate(transportServiceDto.getArrDate());
            transportService.setRequiredCategory(transportServiceDto.getRequiredCategory());
            transportService.setRequiredVehicleType(transportServiceDto.getRequiredVehicleType());
            transportService.setServicePrice(transportService.getServicePrice());
            transportService.setWeight(transportServiceDto.getWeight());

            session.persist(transportService);
            transaction.commit();
        }
    }

    public static List<TransportServiceDto> getTransportServices(){
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                    "SELECT new org.example.dto.TransportServiceDto(" +
                            "ts.id, ts.depLocation, ts.arrLocation, ts.depDate, ts.arrDate, ts.requiredCategory, ts.requiredVehicleType, ts.servicePrice, ts.weight) " +
                            "FROM TransportService ts",
                    TransportServiceDto.class).getResultList();
        }
    }

    public static TransportServiceDto getTransportService(long tsId){
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "SELECT new org.example.dto.TransportServiceDto(" +
                                    "ts.id, ts.depLocation, ts.arrLocation, ts.depDate, ts.arrDate, ts.requiredCategory, ts.requiredVehicleType, ts.servicePrice, ts.weight) " +
                                    "FROM TransportService ts " +
                                    "WHERE ts.id = :id",
                            TransportServiceDto.class)
                    .setParameter("id", tsId)
                    .getSingleResult();
        }
    }

    public static void updateTransportService(long tsId, TransportServiceDto transportServiceDto){
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            TransportService transportService = session.find(TransportService.class, tsId);

            transportService.setDepLocation(transportServiceDto.getDepLocation());
            transportService.setArrLocation(transportServiceDto.getArrLocation());
            transportService.setDepDate(transportServiceDto.getDepDate());
            transportService.setArrDate(transportServiceDto.getArrDate());
            transportService.setRequiredCategory(transportServiceDto.getRequiredCategory());
            transportService.setRequiredVehicleType(transportServiceDto.getRequiredVehicleType());
            transportService.setServicePrice(transportService.getServicePrice());
            transportService.setWeight(transportServiceDto.getWeight());

            transaction.commit();
        }
    }

    public static void deleteTransportService(long tsId){
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            TransportService transportService = session.find(TransportService.class, tsId);
            session.remove(transportService);
            transaction.commit();
        }
    }

    public static void assignServiceExecutive(long serviceId, long companyId, long employeeId, long vehicleId){
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Service service = session.find(Service.class, serviceId);
            Company company = session.find(Company.class, companyId);
            Employee employee = session.find(Employee.class, employeeId);
            Vehicle vehicle = session.find(Vehicle.class, vehicleId);

            if(employee.getCategory() != service.getRequiredCategory() || vehicle.getType() != service.getRequiredVehicleType()){
                throw new IllegalStateException("Employee does not have required category or vehicle is not suitable for the service");
            }
            if(employee.getCompany().getId() != companyId){
                throw new IllegalStateException("Driver is not an employee of this company");
            }
            if(vehicle.getCompany().getId() != companyId){
                throw new IllegalStateException("Vehicle does not belong to this company");
            }

            service.setCompany(company);
            service.setEmployee(employee);
            service.setVehicle(vehicle);
            session.persist(service);
            transaction.commit();
        }
    }

    public static List<ServiceDto> sortServicesByDestination(){
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            return session.createQuery(
                            "SELECT new GetServiceDto(s.id, s.depLocation, s.arrLocation, s.depDate, s.arrDate) " +
                                    "FROM Service s " +
                                    "ORDER BY s.arrLocation", ServiceDto.class)
                    .getResultList();
        }
    }


    public static void writeToFile(List<ServiceDto> services) {

        String FILE_PATH = "services.csv";

        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(FILE_PATH))) {

            writer.write("ID,Departure,Arrival,DepDate,ArrDate\n");

            for (ServiceDto s : services) {
                writer.write(
                        s.getId() + "," +
                                s.getDepLocation() + "," +
                                s.getArrLocation() + "," +
                                s.getDepDate() + "," +
                                s.getArrDate() + ","
                );
                writer.newLine();
            }

        } catch (IOException e) {
            throw new RuntimeException("File write error", e);
        }
    }

    public static Long getCountOfServicesDone() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Long> cq = cb.createQuery(Long.class);
            Root<Service> root = cq.from(Service.class);

            cq.select(cb.count(root))
                    .where(cb.lessThan(root.get("arrDate"), LocalDate.now()));

            return session.createQuery(cq).getSingleResult();
        }
    }


    public static Double getPriceSumOfServicesDone() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Double> cq = cb.createQuery(Double.class);
            Root<Payment> root = cq.from(Payment.class);

            Join<Payment, Service> service = root.join("service");

            cq.select(cb.sum(root.get("price")))
                    .where(cb.lessThan(service.get("arrDate"), LocalDate.now()));

            return session.createQuery(cq).getSingleResult();
        }
    }


    public static List<GetEmployeeServiceCountDto> getEmployeesServiceCount() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<GetEmployeeServiceCountDto> cq = cb.createQuery(GetEmployeeServiceCountDto.class);

            Root<Service> root = cq.from(Service.class);
            Join<Service, Employee> employee = root.join("employee");
            cq.select(cb.construct(
                            GetEmployeeServiceCountDto.class,
                            employee.get("id"),
                            employee.get("firstName"),
                            employee.get("lastName"),
                            cb.count(root)
                    ))
                    .where(cb.lessThan(root.get("arrDate"), LocalDate.now()))
                    .groupBy(employee.get("id"), employee.get("firstName"), employee.get("lastName"));

            return session.createQuery(cq).getResultList();
        }
    }

    public static List<GetEmployeeServiceIncomeDto> getEmployeesServiceIncomeSum(){
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            LocalDate today = LocalDate.now();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<GetEmployeeServiceIncomeDto> cq = cb.createQuery(GetEmployeeServiceIncomeDto.class);

            Root<Payment> root = cq.from(Payment.class);
            Join<Payment, Service> service = root.join("service");
            Join<Service, Employee> employee = service.join("employee");

            cq.select(cb.construct(
                    GetEmployeeServiceIncomeDto.class,
                    employee.get("id"),
                    employee.get("firstName"),
                    employee.get("lastName"),
                    cb.sum(root.get("price"))
            )
            ).where(
                    cb.lessThan(service.get("arrDate"), today)
                    ).groupBy(employee.get("id"),
                            employee.get("firstName"),
                            employee.get("lastName"));

            return session.createQuery(cq).getResultList();
        }

    }

    public static ServiceDto getService(long id){
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "SELECT new org.example.dto.ServiceDto(" +
                                    "s.id, s.depLocation, s.arrLocation, s.depDate, s.arrDate) " +
                                    "FROM Service s " +
                                    "WHERE s.id = :id",
                            ServiceDto.class)
                    .setParameter("id", id)
                    .getSingleResult();
        }
    }



}
