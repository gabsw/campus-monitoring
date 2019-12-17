package ies.grupo33.CampusMonitoring.Controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ies.grupo33.CampusMonitoring.DTO.UserDto;
import ies.grupo33.CampusMonitoring.Exception.LoginFailedException;
import ies.grupo33.CampusMonitoring.Exception.LoginRequiredException;
import ies.grupo33.CampusMonitoring.Exception.UserNotFoundException;
import ies.grupo33.CampusMonitoring.Services.UserServices;

@RestController
public class SpringBootJDBCSessionController {
	
	@Autowired
    private UserServices userServices;
	
	@GetMapping("/login/{username}/{password}")
    public UserDto login(@PathVariable String username, @PathVariable String password,HttpServletRequest request) throws LoginFailedException, UserNotFoundException{
		UserDto u = userServices.loginUser(username, password);
		if (u == null) {
			throw new LoginFailedException("Login failed.");
		}
		String user = (String) request.getSession().getAttribute("username");
        if(user==null){
            user = username;
            request.getSession().setAttribute("username",user);         
        }
        
        return u;
	}
	
	
	@GetMapping("/user-info")
	public UserDto getUserInfo(HttpServletRequest request) throws LoginFailedException, UserNotFoundException, LoginRequiredException{
		String user = (String) request.getSession().getAttribute("username");
		if (user == null) {
			throw new LoginRequiredException("Login Required for this request.");
		}
		UserDto u = userServices.getUserDtoByUsername(user);
		return u;
	}

}
