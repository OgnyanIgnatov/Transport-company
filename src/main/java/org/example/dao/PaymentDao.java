package org.example.dao;

import org.example.configuration.SessionFactoryUtil;
import org.example.dto.ClientDto;
import org.example.dto.CompanyDto;
import org.example.dto.PaymentDto;
import org.example.entity.Client;
import org.example.entity.Company;
import org.example.entity.Payment;
import org.example.entity.Service;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PaymentDao {

    public static void createPayment(PaymentDto paymentDto) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Payment payment = new Payment();
            payment.setPrice(paymentDto.getPrice());
            payment.setPaymentDate(paymentDto.getPaymentDate());

            ClientDto clientDto = PersonDao.getClient(paymentDto.getClientId());
            Client client = new Client();
            client.setId(clientDto.getId());
            client.setFirstName(clientDto.getFirstName());
            client.setLastName(clientDto.getLastName());
            client.setTelephoneNumber(clientDto.getTelephoneNumber());
            client.setBirthDate(clientDto.getBirthDate());
            payment.setClient(client);
        }
    }

    public static List<CompanyDto> getCompanies() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "SELECT new org.example.dto.CompanyDto(c.id,c.name,c.income) FROM Company c",
                            CompanyDto.class)
                    .getResultList();
        }
    }

    public static CompanyDto getCompany(long id) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "SELECT new org.example.dto.CompanyDto(c.id, c.name, c.income) FROM Company c " +
                                    "WHERE c.id = :id", CompanyDto.class)
                    .setParameter("id", id)
                    .getSingleResult();
        }
    }

    public static void updateCompany(long id, CompanyDto company) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Company company1 = session.find(Company.class, id);
            company1.setName(company.getName());
            company1.setIncome(company.getIncome());
            session.persist(company1);
            transaction.commit();
        }
    }

    public static void deleteCompany(long id) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Company company1 = session.find(Company.class, id);
            session.remove(company1);
            transaction.commit();
        }
    }
}
