package site.easy.to.build.crm.service.csv;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.Data;
import site.easy.to.build.crm.entity.csv.mapping.CsvMapping;
import site.easy.to.build.crm.entity.csv.results.ImportFileCsvResult;
import site.easy.to.build.crm.exception.ImportException;



@Service
@Validated
@Data
public class ImportCsvService<T,G extends CsvMapping> {
    private boolean auto_save = true;
    @Autowired
    private Validator validator;
    
    public void disableAutoSave(){
        setAuto_save(false);
    }
    protected void validateConstraint(Object mapped , ImportException lineException){
        Set<ConstraintViolation<Object>> violations = validator.validate(mapped);
        if (!violations.isEmpty()) {
            for (ConstraintViolation<Object> violation : violations) {
                lineException.addError(violation.getMessage());
            }
        }
    }
    protected void validate( Object mapped , ImportException lineException){
        validateConstraint(mapped,lineException);
    }
    
    @Transactional
    public void importData(List<G> data,ImportFileCsvResult importBody,IImportService<T,G> iService){
        int numLine = 1;
        for (G mapped : data) {
            ImportException e = importBody.getImportException(numLine);
            T instance = iService.parseFromMappinToInstance(mapped, e);
            validate(instance, e);
            if (auto_save && !e.hasEsrrors()) {
                iService.saveInstance(instance,e);
            }
        }
    }
}
