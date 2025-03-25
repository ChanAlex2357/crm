package site.easy.to.build.crm.service.csv;
import site.easy.to.build.crm.entity.Expense;
import site.easy.to.build.crm.entity.csv.mapping.CsvMapping;
import site.easy.to.build.crm.entity.csv.mapping.CustomerMapping;
import site.easy.to.build.crm.exception.ImportException;

public class ExpenseImportService implements IImportService<Expense,CustomerMapping> {

    @Override
    public Expense parseFromMappinToInstance(CustomerMapping mapping, ImportException lineException) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'parseFromMappinToInstance'");
    }

    @Override
    public Expense saveInstance(Expense data, ImportException lineException) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveInstance'");
    }

    @Override
    public Class<CustomerMapping> getMapping() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMapping'");
    }

    
    
}
