package ies.grupo33.CampusMonitoring.Services;

import ies.grupo33.CampusMonitoring.Model.Local;
import ies.grupo33.CampusMonitoring.Repository.LocalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalServices {

    @Autowired
    private LocalRepository localRepository;

    public List<Local> getAllLocals() {
        return localRepository.findAll();
    }


}
