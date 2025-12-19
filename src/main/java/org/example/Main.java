package org.example;

import org.example.configuration.SessionFactoryUtil;
import org.example.dao.ClientDao;
import org.example.dao.CompanyDao;
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
        clientDto.setFirstName("Yanko");
        clientDto.setLastName("Petrov");
        clientDto.setBirthDate(LocalDate.parse("2003-07-11"));
        ClientDao.createClient(clientDto);

        ClientDao.getClients().forEach(System.out::println);
        System.out.println(ClientDao.getClient(2));

        ClientDto client1Dto = new ClientDto();
        client1Dto.setFirstName("Yanko");
        client1Dto.setLastName("Murshata");
        client1Dto.setBirthDate(LocalDate.parse("2003-07-11"));
        ClientDao.updateClient(2,client1Dto);
        System.out.println(ClientDao.getClient(2));

        ClientDao.deleteClient(2);
        ClientDao.getClients().forEach(System.out::println);

    }
}