package ies.grupo33.CampusMonitoring.security;

import ies.grupo33.CampusMonitoring.Exception.LoginRequiredException;

import javax.servlet.http.HttpSession;

public class SecurityUtils {
    private SecurityUtils(){}

    public static String getUserIdentity(HttpSession session) throws LoginRequiredException {
        String username = (String) session.getAttribute("username");

        if (username == null) {
            throw new LoginRequiredException("Login required.");
        }

        return username;
    }
}
