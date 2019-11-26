package ies.grupo33.CampusMonitoring.Services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ies.grupo33.CampusMonitoring.Model.Local;
import ies.grupo33.CampusMonitoring.Repository.LocalRepository;

@Service
public class LocalServices {
	
	@Autowired
	private LocalRepository localRepository;
	
	public List<Local> getAllLocals(){
		return localRepository.findAll();
	}
	

}
