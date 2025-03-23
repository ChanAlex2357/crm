package site.easy.to.build.crm.service.budget;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.easy.to.build.crm.entity.Devise;
import site.easy.to.build.crm.repository.DeviseRepository;

@Service
public class DeviseService {

    @Autowired
    private DeviseRepository deviseRepository;
    
    // Retrieve all Devise records
    public List<Devise> findAll() {
        return deviseRepository.findAll();
    }
    
    // Retrieve a Devise by its id
    public Devise getDeviseById(int id) {
        return deviseRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Devise not found with id: " + id));
    }
    
    // Create a new Devise
    public Devise createDevise(Devise devise) {
        return deviseRepository.save(devise);
    }
    
    // Update an existing Devise
    public Devise updateDevise(Devise devise) {
        if (!deviseRepository.existsById(devise.getId())) {
            throw new RuntimeException("Devise not found with id: " + devise.getId());
        }
        return deviseRepository.save(devise);
    }
    
    // Delete a Devise by its id
    public void deleteDevise(int id) {
        if (!deviseRepository.existsById(id)) {
            throw new RuntimeException("Devise not found with id: " + id);
        }
        deviseRepository.deleteById(id);
    }
}
