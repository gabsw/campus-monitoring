package ies.grupo33.CampusMonitoring.Controller;

import ies.grupo33.CampusMonitoring.Model.User;
import ies.grupo33.CampusMonitoring.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserServices userServices;

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
    
    @GetMapping("/authentication/{username}/{password}")
    public User authenticateUser(@PathVariable String username, @PathVariable String password) {
    	return userServices.loginUser(username, password);
    }



}
