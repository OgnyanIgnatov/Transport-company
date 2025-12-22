package org.example.dao;

import org.example.configuration.SessionFactoryUtil;
import org.example.dto.*;
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
            Client client = session.find(Client.class, paymentDto.getClientId());
            Service service = session.find(Service.class, paymentDto.getServiceId());

            if(client == null) throw new IllegalArgumentException("Client not found");
            if(service == null) throw new IllegalArgumentException("Service not found");

            payment.setClient(client);
            payment.setService(service);

            session.persist(payment);
            transaction.commit();


        }
    }

    public static List<PaymentDto> getPayments(){
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                    "SELECT new PaymentDto(p.id, p.price, p.paymentDate, c.id, s.id) FROM Payment p " +
                            "JOIN p.client c " +
                            "JOIN p.service s",
                    PaymentDto.class)
                    .getResultList();
        }
    }

    public static PaymentDto getPayment(long paymentId){
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "SELECT new PaymentDto(p.id, p.price, p.paymentDate, c.id, s.id) FROM Payment p " +
                                    "JOIN p.client c " +
                                    "JOIN p.service s " +
                                    "WHERE p.id = :id",
                            PaymentDto.class)
                    .setParameter("id", paymentId)
                    .getSingleResult();
        }
    }

    public static void updatePayment(long paymentId, PaymentDto paymentDto){
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Payment payment = session.find(Payment.class, paymentDto);
            payment.setPrice(paymentDto.getPrice());
            payment.setPaymentDate(paymentDto.getPaymentDate());
            Client client = session.find(Client.class, paymentDto.getClientId());
            Service service = session.find(Service.class, paymentDto.getServiceId());

            if(client == null) throw new IllegalArgumentException("Client not found");
            if(service == null) throw new IllegalArgumentException("Service not found");

            payment.setClient(client);
            payment.setService(service);

            session.persist(payment);
            transaction.commit();
        }
    }

    public static void deletePayment(long paymentId){
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            Payment payment = session.find(Payment.class, paymentId);
            session.remove(payment);
            transaction.commit();
        }
    }



}
