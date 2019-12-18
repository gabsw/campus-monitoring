package ies.grupo33.CampusMonitoring.Services;

import ies.grupo33.CampusMonitoring.Exception.ForbiddenUserException;
import ies.grupo33.CampusMonitoring.Exception.LocalNotFoundException;
import ies.grupo33.CampusMonitoring.Exception.LoginRequiredException;
import ies.grupo33.CampusMonitoring.Exception.UserNotFoundException;
import ies.grupo33.CampusMonitoring.Model.User;
import ies.grupo33.CampusMonitoring.Model.WeatherStats;
import ies.grupo33.CampusMonitoring.Repository.WeatherStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;


@Transactional
@Service
public class WeatherStatsServices {

    @Autowired
    private WeatherStatsRepository weatherStatsRepository;

    @Autowired
    private UserServices userServices;

    public List<WeatherStats> getWeatherStats(String localName, LocalDate timeStart, LocalDate timeEnd, String username)
            throws ForbiddenUserException, LocalNotFoundException, UserNotFoundException, LoginRequiredException {
        if (timeStart == null || timeEnd == null) {
            throw new IllegalArgumentException("Time range is not defined.");
        } else if (localName == null) {
            throw new IllegalArgumentException("Local name is not defined.");
        } else {
            User currentUser = userServices.findUserByUsername(username);

            userServices.checkIfUserIsAtLocal(currentUser.getUsername(), localName);

            return weatherStatsRepository.findByWeatherStatsPKLocalNameAndWeatherStatsPKDateBetweenOrderByWeatherStatsPKDateAsc(localName, timeStart, timeEnd);
        }
    }

    public List<WeatherStats> getWeatherStats(String localName, String username)
            throws ForbiddenUserException, LocalNotFoundException, UserNotFoundException, LoginRequiredException {
        if (localName == null) {
            throw new IllegalArgumentException("Local name is not defined.");
        } else {
            User currentUser = userServices.findUserByUsername(username);

            userServices.checkIfUserIsAtLocal(currentUser.getUsername(), localName);


            return weatherStatsRepository.findByWeatherStatsPKLocalNameOrderByWeatherStatsPKDateAsc(localName);
        }
    }
}
