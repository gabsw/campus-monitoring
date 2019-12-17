package ies.grupo33.CampusMonitoring.Controller;

import ies.grupo33.CampusMonitoring.DTO.UserDto;
import ies.grupo33.CampusMonitoring.Exception.LoginFailedException;
import ies.grupo33.CampusMonitoring.Exception.LoginRequiredException;
import ies.grupo33.CampusMonitoring.Exception.UserNotFoundException;
import ies.grupo33.CampusMonitoring.Model.Local;
import ies.grupo33.CampusMonitoring.Model.User;
import ies.grupo33.CampusMonitoring.Services.LocalServices;
import ies.grupo33.CampusMonitoring.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
        return localServices.getLocalsByUser(request.getSession());
    }

    // TODO: Add controller for sensor getSensorsByUser

    // These controllers have been created for testing User services, they are not final

    @GetMapping("/local-name/{localName}")
    public List<User> getUserByLocal(@PathVariable String localName) {
        return userServices.getUsersByLocal(localName);
    }


    @GetMapping("/local-name/{localName}/admin")
    public List<User> getAdminByLocal(@PathVariable String localName) {
        return userServices.getAdminByLocal(localName);
    }

    @GetMapping("/local-name/{localName}/regular")
    public List<User> getRegularUsersByLocal(@PathVariable String localName) {
        return userServices.getRegularUsersByLocal(localName);
    }
    




}
