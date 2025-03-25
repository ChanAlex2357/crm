package site.easy.to.build.crm.service.csv;
import site.easy.to.build.crm.entity.csv.mapping.CsvMapping;
import site.easy.to.build.crm.exception.ImportException;

public class ExpenseImportService extends ImportCsvService{

    public ExpenseImportService(Class<? extends CsvMapping> mapping) {
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
