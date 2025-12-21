package org.example;

import org.example.configuration.SessionFactoryUtil;
import org.example.dao.CompanyDao;
import org.example.dao.PersonDao;
import org.example.dto.ClientDto;
import org.example.dto.CompanyDto;
import org.example.entity.Client;
import org.hibernate.Session;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        session.close();

//        CompanyDto company = new CompanyDto();
//        company.setName("Smotlio OOD");
//        company.setIncome(142000);
//        CompanyDao.createCompany(company);

//        CompanyDto company2 = new CompanyDto();
//        company2.setName("AAAAAABBBEEEEE");
//        company2.setIncome(203010);
//
//        CompanyDao.getCompanies().forEach(System.out::println);
//
//        CompanyDao.updateCompany(3, company2);
//
//        System.out.println(CompanyDao.getCompany(3));
//
//        CompanyDao.deleteCompany(2);
//
//        CompanyDao.getCompanies().forEach(System.out::println);

        ClientDto clientDto = new ClientDto();
        clientDto.setFirstName("Kircho");
        clientDto.setLastName("Petrov");
        clientDto.setTelephoneNumber("0987654321");
        clientDto.setBirthDate(LocalDate.parse("2003-07-11"));
        PersonDao.createClient(clientDto);

        PersonDao.getClients().forEach(System.out::println);
        System.out.println(PersonDao.getClient(1));

        ClientDto client1Dto = new ClientDto();
        client1Dto.setFirstName("Kircho");
        client1Dto.setLastName("Murshata");
        client1Dto.setBirthDate(LocalDate.parse("2003-07-11"));
        client1Dto.setTelephoneNumber("0123456789");
        PersonDao.updateClient(1,client1Dto);
        System.out.println(PersonDao.getClient(1));

        PersonDao.deleteClient(1);
        PersonDao.getClients().forEach(System.out::println);

    }
}