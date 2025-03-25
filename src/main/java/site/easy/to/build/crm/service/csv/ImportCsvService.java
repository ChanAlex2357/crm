package site.easy.to.build.crm.service.csv;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import site.easy.to.build.crm.entity.Customer;
import site.easy.to.build.crm.entity.csv.mapping.CsvMapping;
import site.easy.to.build.crm.entity.csv.results.ImportFileCsvResult;
import site.easy.to.build.crm.entity.csv.results.ImportMapFilesCsvResult;
import site.easy.to.build.crm.exception.AdminImportException;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.time.LocalDateTime;


@Service
@Validated
public abstract class ImportCsvService {

    public Class<? extends CsvMapping> mapping;
    public ImportCsvService( Class<? extends CsvMapping> mapping) {
        this.mapping = mapping;
    }
    
    @Autowired
    private Validator validator;
    
    protected void validate( Object mapped , ImportFileCsvResult importException){
        Set<ConstraintViolation<Object>> violations = validator.validate(mapped);
        if (!violations.isEmpty()) {
            // Traiter les violations
            List<String> errors = new ArrayList<>();
            for (ConstraintViolation<Object> violation : violations) {
                errors.add(violation.getMessage());
            }
            importException.addErrors(errors);
        }
    }
    
    abstract protected void importData(List<? extends CsvMapping> data,ImportMapFilesCsvResult importBody);
}
