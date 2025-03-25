package site.easy.to.build.crm.service.csv.general;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import site.easy.to.build.crm.entity.csv.CsvMapping;
import site.easy.to.build.crm.entity.csv.ImportCsvResult;
import site.easy.to.build.crm.service.csv.CSVService;
import site.easy.to.build.crm.service.csv.ImportCsvService;

@Service
public class AdminImportCsvService {
    @Autowired
    private CSVService csvService;
    
    public  importData(MultipartFile file, ImportCsvService importService,char separator){
        ImportCsvResult importBody = new ImportCsvResult();
        try {
            List<CsvMapping> data = csvService.parseCSV(file, importService.getMapping(), separator);
            importService.importData(data,importBody);
        } catch (Exception e) {
            String message = e.getMessage();
            if (e.getCause() != null) {
                message += "\n [ because ] :: "+e.getCause().getMessage();
            }
            importBody.addError(message,0);
        }
        return importBody;
    }
}
