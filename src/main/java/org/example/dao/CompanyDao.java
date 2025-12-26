package org.example.dao;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.configuration.SessionFactoryUtil;
import org.example.dto.CompanyDto;
import org.example.entity.Company;
import org.example.entity.Employee;
import org.example.entity.Service;
import org.example.entity.Vehicle;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CompanyDao {
    public static void createCompany(CompanyDto companyDto) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Company company = new Company();
            company.setName(companyDto.getName());
            company.setIncome(companyDto.getIncome());
            session.persist(company);
            transaction.commit();
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

    public static void hireToCompany(long companyId, long employeeId){
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Company company = session.find(Company.class, companyId);
            Employee employee = session.find(Employee.class, employeeId);
            employee.setCompany(company);
            session.persist(employee);
            transaction.commit();
        }
    }

    public static void buyVehicleForCompany(long companyId, long vehicleId){
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Company company = session.find(Company.class, companyId);
            Vehicle vehicle = session.find(Vehicle.class, vehicleId);
            vehicle.setCompany(company);
            session.persist(vehicle);
            transaction.commit();
        }
    }

    public static List<CompanyDto> sortCompaniesByName(){
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "SELECT new org.example.dto.CompanyDto(c.id,c.name,c.income) FROM Company c " +
                                    "ORDER BY c.name",
                            CompanyDto.class)
                    .getResultList();
        }
    }

    public static List<CompanyDto> sortCompaniesByIncome(){
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "SELECT new org.example.dto.CompanyDto(c.id,c.name,c.income) FROM Company c " +
                                    "ORDER BY c.income",
                            CompanyDto.class)
                    .getResultList();
        }
    }

    public static Double getCompanyIncomeForPeriod(long companyId, LocalDate firstDate, LocalDate lastDate){
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Double> cq = cb.createQuery(Double.class);
            Root<Service> service = cq.from(Service.class);

            cq.select(cb.sum(service.get("servicePrice")))
                    .where(cb.and(
                            cb.equal(service.get("company").get("id"), companyId),
                            cb.between(service.get("depDate"), firstDate, lastDate))
                    );

            Double result = session.createQuery(cq).getSingleResult();
            return result;
        }
    }
}
