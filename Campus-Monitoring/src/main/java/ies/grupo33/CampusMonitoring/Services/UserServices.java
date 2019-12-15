package ies.grupo33.CampusMonitoring.Services;

import ies.grupo33.CampusMonitoring.DTO.UserDto;
import ies.grupo33.CampusMonitoring.Exception.LoginFailedException;
import ies.grupo33.CampusMonitoring.Exception.UserNotFoundException;
import ies.grupo33.CampusMonitoring.Model.Local;
import ies.grupo33.CampusMonitoring.Model.User;
import ies.grupo33.CampusMonitoring.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServices {

    @Autowired
    private UserRepository userRepository;
    
    public User getUsersByUsername(String username) throws UserNotFoundException {
    	if (username == null) {
            throw new IllegalArgumentException("Username is not defined.");
        } else {
            Optional<User> opt_user= userRepository.findById(username);
            if (!opt_user.isPresent()) {
            	throw new UserNotFoundException("User not found "+username);
            }
            return opt_user.get();
        }
    }
    
    public UserDto loginUser(String username, String password) throws LoginFailedException{
    	if (username == null || password == null) {
    		//return null;
            throw new IllegalArgumentException("User (password or username) is not defined.");
        }
    	Optional<User> opt_user= userRepository.findById(username);
    	if (!opt_user.isPresent())
    		return null;
    	if (opt_user.get().getPassword().equals(password)){
    		User u = opt_user.get();
    		UserDto udto = new UserDto(username, u.getEmail(), u.getName(), u.getLocals(), u.isAdmin());
    		return udto;
    		}
    	throw new LoginFailedException("Login failed.");
    }

    public List<User> getUsersByLocal(String local) {
        if (local == null) {
            throw new IllegalArgumentException("Local is not defined.");
        } else {
            return userRepository.findByLocalsName(local);
        }
    }

    public List<User> getUsersByAdminStatus(boolean isAdmin) {
        return userRepository.findByAdmin(isAdmin);
    }


    public List<User> getAdminByLocal(String local) {

        if (local == null) {
            throw new IllegalArgumentException("Local is not defined.");
        } else {
            return userRepository.findByLocalsNameAndAdmin(local, true);
        }

    }

    public List<User> getRegularUsersByLocal(String local) {

        if (local == null) {
            throw new IllegalArgumentException("Local is not defined.");
        } else {
            return userRepository.findByLocalsNameAndAdmin(local, false);
        }

    }
}
