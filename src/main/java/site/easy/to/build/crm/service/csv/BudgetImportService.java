package site.easy.to.build.crm.service.csv;

import org.springframework.stereotype.Service;

import site.easy.to.build.crm.entity.Budget;
import site.easy.to.build.crm.entity.csv.mapping.CustomerMapping;
import site.easy.to.build.crm.exception.ImportException;

@Service
public class BudgetImportService implements IImportService<Budget,CustomerMapping>{

    @Override
    public Budget parseFromMappinToInstance(CustomerMapping mapping, ImportException lineException) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'parseFromMappinToInstance'");
    }

    @Override
    public Budget saveInstance(Budget data, ImportException lineException) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveInstance'");
    }

    @Override
    public Class<CustomerMapping> getMapping() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMapping'");
    }
}    
