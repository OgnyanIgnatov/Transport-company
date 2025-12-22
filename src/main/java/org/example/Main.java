package org.example;

import jakarta.persistence.EnumType;
import org.example.configuration.SessionFactoryUtil;
import org.example.dao.CompanyDao;
import org.example.dao.PersonDao;
import org.example.dao.ServiceDao;
import org.example.dao.VehicleDao;
import org.example.dto.*;
import org.example.entity.*;
import org.hibernate.Session;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        session.close();

//        CompanyDto company = new CompanyDto();
//        company.setName("Travel7 OOD");
//        company.setIncome(643212);
//        CompanyDao.createCompany(company);
//
//        CompanyDto company2 = new CompanyDto();
//        company2.setName("Travel8 OOD");
//        company2.setIncome(21321);
//        CompanyDao.createCompany(company2);
//
//        CompanyDao.getCompanies().forEach(System.out::println);
//
//        CompanyDao.updateCompany(5, company2);
//
//        System.out.println(CompanyDao.getCompany(5));
//
//        CompanyDao.deleteCompany(2);
//
//        CompanyDao.getCompanies().forEach(System.out::println);
//
//        ClientDto clientDto = new ClientDto();
//        clientDto.setFirstName("Na Dgeto");
//        clientDto.setLastName("Brat Mu");
//        clientDto.setTelephoneNumber("0881181818");
//        clientDto.setBirthDate(LocalDate.parse("2004-08-27"));
//        PersonDao.createClient(clientDto);

//        PersonDao.getClients().forEach(System.out::println);
//        System.out.println(PersonDao.getClient(2));
//
//        ClientDto client1Dto = new ClientDto();
//        client1Dto.setFirstName("Sasho");
//        client1Dto.setLastName("Milenov");
//        client1Dto.setBirthDate(LocalDate.parse("2001-12-10"));
//        client1Dto.setTelephoneNumber("0846464646");
//        PersonDao.createClient(client1Dto);
//        PersonDao.updateClient(1,client1Dto);
//        System.out.println(PersonDao.getClient(1));
////
//        PersonDao.deleteClient(1);
//        PersonDao.getClients().forEach(System.out::println);

//        EmployeeDto employeeDto = new EmployeeDto();
//        employeeDto.setFirstName("Qnko");
//        employeeDto.setLastName("Reno");
//        employeeDto.setTelephoneNumber("0891919191");
//        employeeDto.setIDNumber("4212321531");
//        employeeDto.setCategory(EmployeeCategory.D);
//        PersonDao.createEmployee(employeeDto);

//        PassengerServiceDto passengerServiceDto = new PassengerServiceDto();
//        passengerServiceDto.setDepLocation("Burgas");
//        passengerServiceDto.setArrLocation("Sofia");
//        passengerServiceDto.setDepDate(LocalDate.parse("2025-12-25"));
//        passengerServiceDto.setArrDate(LocalDate.parse("2025-12-30"));
//        passengerServiceDto.setPassengerCount(32);
//        ServiceDao.createPassengerService(passengerServiceDto);

//        VehicleDto vehicleDto = new VehicleDto();
//        vehicleDto.setRegNumber("РВ0715ВХ");
//        vehicleDto.setType(VehicleType.CARRYING);
//        VehicleDao.createVehicle(vehicleDto);
//
//        ServiceDao.getTransportServices().forEach(System.out::println);
//        CompanyDao.hireToCompany(2, 7);
//        CompanyDao.hireToCompany(5,8);

//        CompanyDao.buyVehicleForCompany(2, 2);
//        PersonDao.assignSalaryToEmployee(1000,8);
//        ServiceDao.assignServiceExecutive(3, 2, 7, 2);

        PersonDao.buyTicketForPassengerService(1,2);

    }
}