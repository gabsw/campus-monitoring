package ies.grupo33.CampusMonitoring.Services;

import ies.grupo33.CampusMonitoring.DTO.UserDto;
import ies.grupo33.CampusMonitoring.Exception.LocalNotFoundException;
import ies.grupo33.CampusMonitoring.Exception.LoginFailedException;
import ies.grupo33.CampusMonitoring.Exception.LoginRequiredException;
import ies.grupo33.CampusMonitoring.Exception.UserNotFoundException;
import ies.grupo33.CampusMonitoring.Exception.ForbiddenUserException;
import ies.grupo33.CampusMonitoring.Model.User;
import ies.grupo33.CampusMonitoring.Repository.LocalRepository;
import ies.grupo33.CampusMonitoring.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Service
public class UserServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LocalRepository localRepository;

    public User getUsersByUsername(String username) throws UserNotFoundException {
        if (username == null) {
            throw new IllegalArgumentException("Username is not defined.");
        } else {
            Optional<User> opt_user = userRepository.findById(username);
            if (!opt_user.isPresent()) {
                throw new UserNotFoundException("User not found " + username);
            }
            return opt_user.get();
        }
    }
    
    public UserDto getUserDtoByUsername(String username) throws UserNotFoundException {
    	if (username == null) {
            throw new IllegalArgumentException("Username is not defined.");
        } else {
            Optional<User> opt_user = userRepository.findById(username);
            if (!opt_user.isPresent()) {
                throw new UserNotFoundException("User not found " + username);
            }
            User u = opt_user.get();
            return new UserDto(username, u.getEmail(), u.getName(), u.getLocals(), u.isAdmin());
        }
    }

    public UserDto loginUser(String username, String password) throws LoginFailedException, UserNotFoundException {
        if (username == null || password == null) {
            throw new IllegalArgumentException("User (password or username) is not defined.");
        }
        Optional<User> opt_user = userRepository.findById(username);
        if (!opt_user.isPresent())
            throw new UserNotFoundException("User not found " + username);
        if (!opt_user.get().getPassword().equals(password)) {
            throw new LoginFailedException("Login failed.");
        }

        User u = opt_user.get();
        
        return new UserDto(username, u.getEmail(), u.getName(), u.getLocals(), u.isAdmin());
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

    public boolean checkIfUserIsAtLocal(String username, String localName) throws ForbiddenUserException, LocalNotFoundException {


        if (username == null) {
            throw new IllegalArgumentException("Username is not defined.");
        }

        if (!localRepository.findById(localName).isPresent()) {
            throw new LocalNotFoundException("Local not found " + localName);
        }

        Optional<User> user = userRepository.findByLocalsNameAndUsername(localName, username);

        if (!user.isPresent()) {
            throw new ForbiddenUserException("User does not have permission to access local.");
        }

        return true;
    }

    public boolean checkIfUserIsAdmin(User user) throws ForbiddenUserException {

        if (user.getUsername() == null) {
            throw new IllegalArgumentException("Username is not defined.");
        }

        if (!user.isAdmin()) {
            throw new ForbiddenUserException("User does not have admin privileges.");
        }

        return true;
    }

    public User findUserBySession(HttpSession session) throws UserNotFoundException, LoginRequiredException {

        String username = (String) session.getAttribute("username");
        
        if (username==null) {
        	throw new LoginRequiredException("Login required.");
        }

        Optional<User> opt_user = userRepository.findById(username);
        if (!opt_user.isPresent())
            throw new UserNotFoundException("User not found " + username);

        return opt_user.get();
    }
}
