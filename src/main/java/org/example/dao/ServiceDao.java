package org.example.dao;
import org.example.configuration.SessionFactoryUtil;

import org.example.dto.PassengerServiceDto;
import org.example.dto.TransportServiceDto;
import org.example.entity.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
                            "ts.id, ts.depLocation, ts.arrLocation, ts.depDate, ts.arrDate, ts.requiredCategory, ts.requiredVehicleType, ts.weight) " +
                            "FROM TransportService ts",
                    TransportServiceDto.class).getResultList();
        }
    }

    public static TransportServiceDto getTransportService(long tsId){
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "SELECT new org.example.dto.TransportServiceDto(" +
                                    "ts.id, ts.depLocation, ts.arrLocation, ts.depDate, ts.arrDate, ts.requiredCategory, ts.requiredVehicleType, ts.weight) " +
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

}
