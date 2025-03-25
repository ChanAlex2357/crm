package site.easy.to.build.crm.service.csv;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import site.easy.to.build.crm.entity.Customer;
import site.easy.to.build.crm.entity.csv.mapping.CsvMapping;
import site.easy.to.build.crm.entity.csv.results.ImportFileCsvResult;
import site.easy.to.build.crm.entity.csv.results.ImportMapFilesCsvResult;
import site.easy.to.build.crm.exception.ImportException;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;


@Service
@Validated
@Data
public abstract class ImportCsvService {
    private boolean auto_save = true;
    public Class<? extends CsvMapping> mapping;
    public ImportCsvService( Class<? extends CsvMapping> mapping) {
        this.mapping = mapping;
    }
    
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
    
    abstract protected Object parseFromMappinToInstance(CsvMapping mapping , ImportException lineException);
    abstract protected Object saveInstance(Object data,ImportException lineException);
    
    // abstract protected void importData(List<? extends CsvMapping> data,ImportFileCsvResult importBody);
    @Transactional
    public void importData(List<? extends CsvMapping> data,ImportFileCsvResult importBody){
        int numLine = 1;
        for (CsvMapping mapped : data) {
            ImportException e = importBody.getImportException(numLine);
            Object instance = parseFromMappinToInstance(mapped, e);
            validate(instance, e);
            if (auto_save && !e.hasEsrrors()) {
                saveInstance(data,e);
            }
        }
    }
}
