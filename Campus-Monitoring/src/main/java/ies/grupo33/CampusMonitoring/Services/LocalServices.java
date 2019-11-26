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
	
	public List<Local> getLocals(String name){
		if (name == null) {
			return localRepository.findAll();
		}
		Optional<Local> local = localRepository.findByName(name);
		
		if (local.isPresent()) {
			return Collections.singletonList(local.get());
		} else {
			return Collections.emptyList();
		}
	}

}
