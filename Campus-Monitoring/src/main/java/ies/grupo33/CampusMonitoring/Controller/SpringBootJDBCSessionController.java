package ies.grupo33.CampusMonitoring.Controller;

import ies.grupo33.CampusMonitoring.DTO.UserDto;
import ies.grupo33.CampusMonitoring.Exception.LoginFailedException;
import ies.grupo33.CampusMonitoring.Exception.LoginRequiredException;
import ies.grupo33.CampusMonitoring.Exception.UserNotFoundException;
import ies.grupo33.CampusMonitoring.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;

@RestController
public class SpringBootJDBCSessionController {
	
	@Autowired
    private UserServices userServices;

	@GetMapping("/login")
	public UserDto login(HttpServletRequest request) throws LoginFailedException, UserNotFoundException {
		String auth = request.getHeader(HttpHeaders.AUTHORIZATION);
		String[] headerParts = auth.trim().split(" ");
		if (headerParts.length != 2) {
			throw new LoginFailedException("Bad authorization header");
		} else if (!headerParts[0].equals("Basic")) {
			throw new LoginFailedException("Unsupported authorization header type.");
		}

		String[] decodedTokenParts = new String(Base64.getDecoder().decode(headerParts[1])).split(":");

		if (decodedTokenParts.length != 2) {
			throw new LoginFailedException("Bad authorization header");
		}

		String username = decodedTokenParts[0];
		String password = decodedTokenParts[1];

		UserDto user = userServices.loginUser(username, password);
		request.getSession().setAttribute("username", user.getUsername());

		return user;
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
