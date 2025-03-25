package site.easy.to.build.crm.service.csv;

import java.util.List;

import site.easy.to.build.crm.entity.csv.mapping.CsvMapping;
import site.easy.to.build.crm.entity.csv.results.ImportMapFilesCsvResult;

public class ExpenseImportService extends ImportCsvService{

    public ExpenseImportService(Class<? extends CsvMapping> mapping) {
        super(mapping);
    }
    
    @Override
    protected void importData(List<? extends CsvMapping> data, ImportMapFilesCsvResult importBody) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'importData'");
    }
    
}
