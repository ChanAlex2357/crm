package site.easy.to.build.crm.service.alert;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.easy.to.build.crm.entity.AlertType;
import site.easy.to.build.crm.repository.AlertTypeRepository;

@Service
public class AlertTypeService {

    @Autowired
    private AlertTypeRepository alertTypeRepository;

    public List<AlertType> getAllAlertTypes() {
        return alertTypeRepository.findAll();
    }

    public AlertType getAlertTypeById(int id) {
        return alertTypeRepository.findById(id).orElse(null);
    }

    public AlertType createAlertType(AlertType alertType) {
        return alertTypeRepository.save(alertType);
    }

    public AlertType updateAlertType(AlertType alertType) {
        return alertTypeRepository.save(alertType);
    }

    public void deleteAlertType(int id) {
        alertTypeRepository.deleteById(id);
    }

    public AlertType getTauxAlert(){return alertTypeRepository.findById(1).orElseThrow( () -> new RuntimeException("Taux d'alert not found with id: 1"));}
    public AlertType getDepassementAlert(){return alertTypeRepository.findById(2).orElseThrow( () -> new RuntimeException("Alerte de depassement not found with id: 2"));}
}