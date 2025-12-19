package org.example.dao;

import org.example.configuration.SessionFactoryUtil;
import org.example.dto.ClientDto;
import org.example.dto.CompanyDto;
import org.example.entity.Client;
import org.example.entity.Company;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ClientDao {
    public static void createClient(ClientDto clientDto) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Client client = new Client();
            client.setFirstName(clientDto.getFirstName());
            client.setLastName(clientDto.getLastName());
            client.setBirthDate(clientDto.getBirthDate());
            session.persist(client);
            transaction.commit();
        }
    }

    public static List<ClientDto> getClients() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "SELECT new org.example.dto.ClientDto(c.id,c.firstName,c.lastName, c.birthDate) FROM Client c",
                            ClientDto.class)
                    .getResultList();
        }
    }

    public static ClientDto getClient(long id) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "SELECT new org.example.dto.ClientDto(c.id,c.firstName,c.lastName, c.birthDate) FROM Client c " +
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
}
