package site.easy.to.build.crm.service.csv;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import site.easy.to.build.crm.entity.Customer;
import site.easy.to.build.crm.entity.csv.CsvMapping;
import site.easy.to.build.crm.entity.csv.ImportBody;
import site.easy.to.build.crm.exception.AdminImportException;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.time.LocalDateTime;


@Service
@Validated
public abstract class ImportService {
    @Autowired
    Class<? extends CsvMapping> mapping;

    @Autowired
    private Validator validator;

    abstract protected void importData(List<? extends CsvMapping> data,ImportBody importBody);


    protected void validate( Object mapped , AdminImportException importException) {
        Set<ConstraintViolation<Object>> violations = validator.validate(mapped);
        if (!violations.isEmpty()) {
            // Traiter les violations
            List<String> errors = new ArrayList<>();
            for (ConstraintViolation<Object> violation : violations) {
                errors.add(violation.getMessage());
            }
            
            // Tu peux aussi lancer une exception personnalisée ici si nécessaire
            // importException.addError();
            
        }

    }
    abstract protected void controller(CsvMapping csvMapping,AdminImportException importException);
    abstract protected void validate(CsvMapping csvMapping,AdminImportException importException); 
}
