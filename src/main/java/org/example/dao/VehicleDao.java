package org.example.dao;

import org.example.configuration.SessionFactoryUtil;
import org.example.dto.VehicleDto;
import org.example.entity.Employee;
import org.example.entity.Vehicle;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class VehicleDao {
    public static void createVehicle(VehicleDto vehicleDto){
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            Vehicle vehicle = new Vehicle();
            vehicle.setRegNumber(vehicleDto.getRegNumber());
            vehicle.setType(vehicleDto.getType());
            session.persist(vehicle);
            transaction.commit();
        }
    }

    public static List<VehicleDto> getVehicles(){
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            return session.createQuery(
                    "SELECT new VehicleDto(v.id, v.regNumber, v.type) FROM Vehicle v",
                    VehicleDto.class)
                    .getResultList();
        }
    }

    public static VehicleDto getVehicle(long vehicleId){
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            return session.createQuery(
                            "SELECT new VehicleDto(v.id, v.regNumber, v.type) FROM Vehicle v " +
                                    "WHERE v.id = :id:",
                            VehicleDto.class)
                    .setParameter("id", vehicleId)
                    .getSingleResult();
        }
    }

    public static void updateVehicle(long vehicleId, VehicleDto vehicleDto){
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Vehicle vehicle = session.find(Vehicle.class, vehicleId);
            vehicle.setRegNumber(vehicleDto.getRegNumber());
            vehicle.setType(vehicleDto.getType());
            session.persist(vehicle);
            transaction.commit();
        }
    }

    public static void deleteVehicle(long vehicleId){
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Vehicle vehicle = session.find(Vehicle.class, vehicleId);
            session.remove(vehicle);
            transaction.commit();
        }
    }
}
