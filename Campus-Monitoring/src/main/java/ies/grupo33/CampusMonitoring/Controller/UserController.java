package ies.grupo33.CampusMonitoring.Controller;

import ies.grupo33.CampusMonitoring.DTO.UserDto;
import ies.grupo33.CampusMonitoring.Exception.LoginFailedException;
import ies.grupo33.CampusMonitoring.Exception.LoginRequiredException;
import ies.grupo33.CampusMonitoring.Exception.UserNotFoundException;
import ies.grupo33.CampusMonitoring.Model.Local;
import ies.grupo33.CampusMonitoring.Services.LocalServices;
import ies.grupo33.CampusMonitoring.Services.UserServices;
import ies.grupo33.CampusMonitoring.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserServices userServices;

    @Autowired
    private LocalServices localServices;

    @GetMapping("/authentication/{username}/{password}")
    public UserDto authenticateUser(@PathVariable String username, @PathVariable String password) throws LoginFailedException, UserNotFoundException {
        return userServices.loginUser(username, password);
    }

    @GetMapping("/username/{username}/locals")
    public List<Local> getLocalsByUser(@PathVariable String username, HttpServletRequest request) throws UserNotFoundException, LoginRequiredException {
        SecurityUtils.getUserIdentity(request.getSession());
        return localServices.getLocalsByUser(username);
    }

    // TODO: Add controller for sensor getSensorsByUser
}
