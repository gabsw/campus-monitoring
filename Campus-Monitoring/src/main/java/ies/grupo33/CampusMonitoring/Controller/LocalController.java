package ies.grupo33.CampusMonitoring.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ies.grupo33.CampusMonitoring.Model.Local;
import ies.grupo33.CampusMonitoring.Services.LocalServices;


@RestController
@RequestMapping("local")
public class LocalController {
	
	@Autowired
	LocalServices localServices;
	
	
	@GetMapping("/")
	public List<Local> getLocals(){
		return localServices.getAllLocals();
	}

}
