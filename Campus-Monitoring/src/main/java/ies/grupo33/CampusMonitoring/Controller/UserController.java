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
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserServices userServices;

    @Autowired
    private LocalServices localServices;

    @GetMapping("/authentication/")
    public UserDto authenticateUser(HttpServletRequest request) throws LoginFailedException, UserNotFoundException {
        if (!request.getAuthType().equals(request.BASIC_AUTH)) {
            throw new LoginFailedException("Unsupported authorization header type.");
        }

        String auth = request.getHeader(HttpHeaders.AUTHORIZATION);
        String token = auth.trim().split(" ")[1];
        String[] decodedTokenParts = new String(Base64.getDecoder().decode(token)).split(":");

        if (decodedTokenParts.length != 2) {
            throw new LoginFailedException("Bad authorization header");
        }

        String username = decodedTokenParts[0];
        String password = decodedTokenParts[1];

        return userServices.loginUser(username, password);
    }

    @GetMapping("/username/{username}/locals")
    public List<Local> getLocalsByUser(@PathVariable String username, HttpServletRequest request) throws UserNotFoundException, LoginRequiredException {
        SecurityUtils.getUserIdentity(request.getSession());
        return localServices.getLocalsByUser(username);
    }

    // TODO: Add controller for sensor getSensorsByUser
}
