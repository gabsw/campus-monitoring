package ies.grupo33.CampusMonitoring.View;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin
@Controller
public class HomeController {

    @RequestMapping("/")
    public String welcome() {
        return "login";
    }

    @RequestMapping("/login.html")
    public String login() {
        return "login";
    }


    @RequestMapping("/board_geral.html")
    public String board() {
        return "board_geral";
    }

    @RequestMapping("/historico.html")
    public String historico() {
        return "historico";
    }

    @RequestMapping("/reports.html")
    public String reports() {
        return "reports";
    }

    @RequestMapping("/reviews.html")
    public String reviews() {
        return "reviews";
    }

    @RequestMapping("/reviews_regular.html")
    public String reviews_regular() {
        return "reviews_regular";
    }
}
