package ies.grupo33.CampusMonitoring.Services;

import ies.grupo33.CampusMonitoring.Exception.ForbiddenUserException;
import ies.grupo33.CampusMonitoring.Exception.LocalNotFoundException;
import ies.grupo33.CampusMonitoring.Exception.LoginRequiredException;
import ies.grupo33.CampusMonitoring.Exception.UserNotFoundException;
import ies.grupo33.CampusMonitoring.Model.User;
import ies.grupo33.CampusMonitoring.Repository.WeatherStatsRepository;
import ies.grupo33.CampusMonitoring.Model.WeatherStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;


@Service
public class WeatherStatsServices {

    @Autowired
    private WeatherStatsRepository weatherStatsRepository;

    @Autowired
    private UserServices userServices;

    public List<WeatherStats> getWeatherStats(String localName, LocalDate timeStart, LocalDate timeEnd, HttpSession session)
            throws ForbiddenUserException, LocalNotFoundException, UserNotFoundException, LoginRequiredException {
        if (timeStart == null || timeEnd == null) {
            throw new IllegalArgumentException("Time range is not defined.");
        } else if (localName == null) {
            throw new IllegalArgumentException("Local name is not defined.");
        } else {
            User currentUser = userServices.findUserBySession(session);

            userServices.checkIfUserIsAtLocal(currentUser.getUsername(), localName);

            return weatherStatsRepository.findByWeatherStatsPKLocalNameAndWeatherStatsPKDateBetweenOrderByWeatherStatsPKDateAsc(localName, timeStart, timeEnd);
        }
    }

    public List<WeatherStats> getWeatherStats(String localName, HttpSession session)
            throws ForbiddenUserException, LocalNotFoundException, UserNotFoundException, LoginRequiredException {
        if (localName == null) {
            throw new IllegalArgumentException("Local name is not defined.");
        } else {
            User currentUser = userServices.findUserBySession(session);

            userServices.checkIfUserIsAtLocal(currentUser.getUsername(), localName);


            return weatherStatsRepository.findByWeatherStatsPKLocalNameOrderByWeatherStatsPKDateAsc(localName);
        }
    }
}
