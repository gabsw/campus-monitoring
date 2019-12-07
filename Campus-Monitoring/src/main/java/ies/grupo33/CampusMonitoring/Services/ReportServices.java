package ies.grupo33.CampusMonitoring.Services;

import ies.grupo33.CampusMonitoring.DTO.ReportDTO;
import ies.grupo33.CampusMonitoring.Repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;


@Service
public class ReportServices {
    @Autowired
    private ReportRepository reportRepository;

    public ReportDTO buildReport(String localName, LocalDate startDate, LocalDate endDate) {
        ReportDTO report = new ReportDTO(localName, startDate, endDate);
        List<Violation> violations = generateViolations(report);

        for (Violation violation : violations) {
            int count = reportRepository.alarmCount(localName,
                    violation.type,
                    violation.parameter,
                    startDate,
                    endDate);
            violation.setter.accept(count);
        }

        report.setAverageRating(reportRepository.averageRating(localName, startDate, endDate));
        report.setAverageAlarmsPerDay(reportRepository.averageAlarmsPerDay(localName, startDate, endDate));
        report.setNumberOngoingAlarms(reportRepository.countOngoingAlarms(localName, startDate, endDate));

        return report;
    }

    private List<Violation> generateViolations(ReportDTO reportDTO) {
        List<Violation> violations = new ArrayList<>();
        violations.add(new Violation("max", "temperatura", reportDTO::setTotalMaxTempAlarms));
        violations.add(new Violation("min", "temperatura", reportDTO::setTotalMinTempAlarms));
        violations.add(new Violation("max", "humidade", reportDTO::setTotalMaxHumAlarms));
        violations.add(new Violation("min", "humidade", reportDTO::setTotalMinHumAlarms));
        violations.add(new Violation("max", "co2", reportDTO::setTotalMaxCo2Alarms));
        return violations;
    }


    private class Violation {
        String type;
        String parameter;
        Consumer<Integer> setter;

        Violation(String type, String parameter, Consumer<Integer> setter) {
            this.type = type;
            this.parameter = parameter;
            this.setter = setter;
        }
    }
}

