package site.easy.to.build.crm.service.csv.importer;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import site.easy.to.build.crm.entity.Customer;
import site.easy.to.build.crm.entity.csv.mapping.CustomerMapping;
import site.easy.to.build.crm.entity.csv.results.ImportFileCsvResult;
import site.easy.to.build.crm.exception.ImportException;
import site.easy.to.build.crm.service.csv.CSVService;
import site.easy.to.build.crm.service.csv.validation.ConstrainValidator;
@Service
@Slf4j
public class CustomerImportService  implements ICsvImporter<Customer,CustomerMapping> {
    //  -- SERVICES Pour l'import --
    @Autowired
    private CSVService<Customer,CustomerMapping> csvParser;
    
    @Autowired
    private ConstrainValidator<Customer> constrainValidator;

    // - Fonctions importation de donnee
    @Transactional
    public ImportFileCsvResult<Customer> importData(MultipartFile file){
        // Prepare the result
        ImportFileCsvResult<Customer> result = new ImportFileCsvResult<Customer>(file);
        List<CustomerMapping> csvRecords = csvParser.parseCSV(file,CustomerMapping.class,';');

        int line = 1;
        for (CustomerMapping customerMapping : csvRecords) {
            ImportException exception = result.getImportException(line++);
            Customer customer = new Customer(); // Cree une instance
            transfer(customerMapping, customer,exception); // Transferer les donnee csv dans l'instance
            validation(customer, exception);
            result.addData(customer);
        }
        return result;
    }

    @Override
    @Transactional
    public void transfer(CustomerMapping mapping, Customer entity,ImportException exception) {
        entity.setName(mapping.getName());
        entity.setEmail(mapping.getEmail());
        entity.setCountry("Madagascar");
    }

    
    @Override
    @Transactional
    public void validation(Customer entity, ImportException exception) {
        constrainValidator.validateConstraint(entity, exception);
    }
}
