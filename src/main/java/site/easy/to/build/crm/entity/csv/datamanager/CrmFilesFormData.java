package site.easy.to.build.crm.entity.csv.datamanager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.easy.to.build.crm.entity.Customer;
import site.easy.to.build.crm.entity.csv.form.IFilesFormData;
import site.easy.to.build.crm.entity.csv.mapping.CsvMapping;
import site.easy.to.build.crm.entity.csv.mapping.CustomerMapping;
import site.easy.to.build.crm.service.csv.ImportCsvManager;
@Service
public class CrmFilesFormData implements IFilesFormData {

    @Autowired
    private ImportCsvManager<Customer,CustomerMapping> customerCsvManager;

    @Override
    public List<ImportCsvManager<?,? extends CsvMapping>> getManagers() {
        ArrayList<ImportCsvManager<?,? extends CsvMapping>> files = new ArrayList<ImportCsvManager<?,? extends CsvMapping>>();
        files.add(customerCsvManager);
        return files;
    }
}
