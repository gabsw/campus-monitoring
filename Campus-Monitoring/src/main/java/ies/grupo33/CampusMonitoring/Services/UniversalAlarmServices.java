package ies.grupo33.CampusMonitoring.Services;

import ies.grupo33.CampusMonitoring.Model.UniversalAlarm;
import ies.grupo33.CampusMonitoring.Model.User;
import ies.grupo33.CampusMonitoring.Repository.UniversalAlarmRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UniversalAlarmServices {
    private static Logger logger = LoggerFactory.getLogger(UniversalAlarmServices.class);

    @Autowired
    private UniversalAlarmRepository universalAlarmRepository;

    @Autowired
    private UserServices userServices;

    @Autowired
    private NotificationServices notificationServices;

    public Page<UniversalAlarm> getUniversalAlarm(String localName, Pageable pageable) {
        if (localName == null) {
            throw new IllegalArgumentException("Local name is not defined.");
        } else {
            return universalAlarmRepository.findByUniversalAlarmPKLocalName(localName, pageable);
        }
    }

    public Page<UniversalAlarm> getUniversalAlarm(String localName, LocalDate timeStart, LocalDate timeEnd, Pageable pageable) {
        if (timeStart == null || timeEnd == null) {
            throw new IllegalArgumentException("Time range is not defined.");
        } else if (localName == null) {
            throw new IllegalArgumentException("Local name is not defined.");
        } else {
            return universalAlarmRepository.findByUniversalAlarmPKLocalNameAndUniversalAlarmPKStartDateTimeBetween(localName, timeStart, timeEnd, pageable);
        }
    }


    // Add pagination

    // Retrieve all open alarms
    public Page<UniversalAlarm> getOpenUniversalAlarm(String localName, Pageable pageable) {
        if (localName == null) {
            throw new IllegalArgumentException("Local name is not defined.");
        } else {

            return universalAlarmRepository.findByUniversalAlarmPKLocalNameAndOngoingStatus(localName, true, pageable);
        }
    }

    public Page<UniversalAlarm> getOpenUniversalAlarm(String localName, LocalDate timeStart, LocalDate timeEnd, Pageable pageable) {
        if (timeStart == null || timeEnd == null) {
            throw new IllegalArgumentException("Time range is not defined.");
        } else if (localName == null) {
            throw new IllegalArgumentException("Local name is not defined.");
        } else {
            return universalAlarmRepository.findByUniversalAlarmPKLocalNameAndUniversalAlarmPKStartDateTimeBetweenAndOngoingStatus(localName, timeStart,
                    timeEnd, true,
                    pageable);
        }
    }

    // Retrieve all closed alarms
    public Page<UniversalAlarm> getClosedUniversalAlarm(String localName, Pageable pageable) {
        if (localName == null) {
            throw new IllegalArgumentException("Local name is not defined.");
        } else {

            return universalAlarmRepository.findByUniversalAlarmPKLocalNameAndOngoingStatus(localName, false, pageable);
        }
    }

    public Page<UniversalAlarm> getClosedUniversalAlarm(String localName, LocalDate timeStart,
                                                        LocalDate timeEnd, Pageable pageable) {
        if (timeStart == null || timeEnd == null) {
            throw new IllegalArgumentException("Time range is not defined.");
        } else if (localName == null) {
            throw new IllegalArgumentException("Local name is not defined.");
        } else {
            return universalAlarmRepository.findByUniversalAlarmPKLocalNameAndUniversalAlarmPKStartDateTimeBetweenAndOngoingStatus(localName, timeStart, timeEnd, false, pageable);
        }
    }


    // Send email notification for new alarm
    public void notifyUsers(UniversalAlarm alarm) {

        // Check if a notification has already been sent
        if (alarm.isNotificationSent()) {
            return;
        }

        // Retrieve primary key attributes
        String local = alarm.getUniversalAlarmPK().getLocalName();
        String violationParameter = alarm.getUniversalAlarmPK().getViolationParameter();
        LocalDateTime startDateTime = alarm.getUniversalAlarmPK().getStartDateTime();

        // Retrieve emails from users attached to local
        List<String> emails = userServices.getUsersByLocal(local).stream()
                .map(User::getEmail)
                .collect(Collectors.toList());

        // Send email to each user
        String subject = "Alerta " + violationParameter + " " + local;
        String text = "Avisamos que às " + startDateTime.toLocalTime() + " do dia " + startDateTime.toLocalDate()
                + " se regista uma violação do tipo '" + alarm.getViolationType() + "' de " + violationParameter +
                " de " + alarm.getViolationValue() + " no local '" + local + "'.";

        try {
            notificationServices.sendNotification(emails, subject, text);
            alarm.setNotificationSent(true);
            universalAlarmRepository.save(alarm);
        } catch (MailException ex) {
            logger.error("Failed to notify users about alarm - Subject: '{}' - Cause: '{}'", subject, ex.getMessage());
        }
    }
}
