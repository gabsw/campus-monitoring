package ies.grupo33.CampusMonitoring.Repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;

@Repository
@Transactional(readOnly = true)
public class ReportRepository {
    @PersistenceContext
    private EntityManager em;

    public int alarmCount(String localName, String violationType, String violationParameter, LocalDate startDate, LocalDate endDate) {
        String sql = "SELECT count(*) " +
                "FROM campus_monitoring.UNIVERSAL_ALARM " +
                "WHERE start_date_time BETWEEN :startDate AND :endDate " +
                "AND local_name = :localName " +
                "AND violation_type = :violationType " +
                "AND violation_parameter = :violationParameter " +
                "group by local_name";

        Query query = em.createNativeQuery(sql);
        query.setParameter("localName", localName);
        query.setParameter("violationType", violationType);
        query.setParameter("violationParameter", violationParameter);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);

        try {
            Number result = (Number) query.getSingleResult();
            return result.intValue();
        } catch (NoResultException ex) {
            return 0;
        }
    }

    public Double averageRating(String localName, LocalDate startDate, LocalDate endDate) {
        String sql = "Select AVG(rating) " +
                "from campus_monitoring.REVIEW " +
                "where date_time BETWEEN :startDate AND :endDate " +
                "AND local_name = :localName " +
                "group by local_name";

        Query query = em.createNativeQuery(sql);
        query.setParameter("localName", localName);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);

        try {
            Number result = (Number) query.getSingleResult();
            return result.doubleValue();
        } catch (NoResultException ex) {
            return null;
        }
    }

    public double averageAlarmsPerDay(String localName, LocalDate startDate, LocalDate endDate) {
        String sql = "Select cast(count(*) as float) / (SELECT DATE_PART('day', cast(:endDate as timestamp) - cast(:startDate as timestamp))) " +
                "from campus_monitoring.universal_alarm " +
                "where local_name = :localName " +
                "AND start_date_time BETWEEN :startDate AND :endDate";

        Query query = em.createNativeQuery(sql);
        query.setParameter("localName", localName);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);

        try {
            Number result = (Number) query.getSingleResult();
            return Math.round(result.doubleValue() * 100) / 100.0;
        } catch (NoResultException ex) {
            return 0;
        }
    }

    public int countOngoingAlarms(String localName, LocalDate startDate, LocalDate endDate) {
        String sql = "SELECT count(*) " +
                "FROM campus_monitoring.UNIVERSAL_ALARM " +
                "WHERE start_date_time BETWEEN :startDate AND :endDate " +
                "AND local_name = :localName " +
                "AND ongoing_status = TRUE";

        Query query = em.createNativeQuery(sql);
        query.setParameter("localName", localName);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);

        try {
            Number result = (Number) query.getSingleResult();
            return result.intValue();
        } catch (NoResultException ex) {
            return 0;
        }
    }
}


