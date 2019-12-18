package ies.grupo33.CampusMonitoring.Controller;

import ies.grupo33.CampusMonitoring.Exception.LoginRequiredException;
import ies.grupo33.CampusMonitoring.Exception.UserNotFoundException;
import ies.grupo33.CampusMonitoring.Model.Local;
import ies.grupo33.CampusMonitoring.Services.LocalServices;
import ies.grupo33.CampusMonitoring.Services.UserServices;
import ies.grupo33.CampusMonitoring.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@CrossOrigin
@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserServices userServices;

    @Autowired
    private LocalServices localServices;

    @GetMapping("/{username}/locals")
    public Collection<Local> getLocalsByUser(@PathVariable String username, HttpServletRequest request) throws UserNotFoundException, LoginRequiredException {
        SecurityUtils.getUserIdentity(request.getSession());
        return localServices.getLocalsByUser(username);
    }
}
