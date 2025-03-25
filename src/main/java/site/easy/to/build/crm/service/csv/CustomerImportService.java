package site.easy.to.build.crm.service.csv;

import java.util.List;

import site.easy.to.build.crm.entity.csv.mapping.CsvMapping;
import site.easy.to.build.crm.entity.csv.results.ImportMapFilesCsvResult;
import site.easy.to.build.crm.exception.ImportException;

public class CustomerImportService extends ImportCsvService{

    public CustomerImportService(Class<? extends CsvMapping> mapping) {
        super(mapping);
    }
    
    @Override
    protected Object parseFromMappinToInstance(CsvMapping mapping, ImportException lineException) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'parseFromMappinToInstance'");
    }

    @Override
    protected Object saveInstance(Object data, ImportException lineException) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveInstance'");
    }
    
}
