package site.easy.to.build.crm.service.csv.general;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import site.easy.to.build.crm.entity.csv.CsvMapping;
import site.easy.to.build.crm.entity.csv.ImportBody;
import site.easy.to.build.crm.service.csv.CSVService;

@Service
public class AdminImportService {
    private final CSVService csvService;
    public AdminImportService() {
        this.csvService = new CSVService();
    }
    public ImportBody importData(MultipartFile file, ImportService importService,char separator){
        ImportBody importBody = new ImportBody();
        List<CsvMapping> data = csvService.parseCSV(file, importService.getMapping(), separator);
        importService.importData(data,importBody);
        return importBody;
    }
}
