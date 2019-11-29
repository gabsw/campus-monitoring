package ies.grupo33.CampusMonitoring.Services;

import ies.grupo33.CampusMonitoring.Model.UniversalAlarm;
import ies.grupo33.CampusMonitoring.Model.WeatherStats;
import ies.grupo33.CampusMonitoring.Representations.UniversalAlarmRepresentation;
import ies.grupo33.CampusMonitoring.Representations.WeatherStatsRepresentation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RepresentationAdapterService {

    public List<WeatherStatsRepresentation> convertWeatherStatsRep(List<WeatherStats> weatherStats) {
        return weatherStats.stream().map(WeatherStatsRepresentation::new).collect(Collectors.toList());
    }

    public List<UniversalAlarmRepresentation> convertUniversalAlarmsRep(List<UniversalAlarm> universalAlarms) {
        return universalAlarms.stream().map(UniversalAlarmRepresentation::new).collect(Collectors.toList());
    }


}
