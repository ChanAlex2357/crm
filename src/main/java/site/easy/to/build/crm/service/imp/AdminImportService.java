package site.easy.to.build.crm.service.imp;

import java.util.List;

import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import site.easy.to.build.crm.entity.imp.CsvMapping;
import site.easy.to.build.crm.exception.AdminImportException;

@Service
public class AdminImportService {
    private final CSVService csvService;
    public AdminImportService() {
        this.csvService = new CSVService();
    }
    public void importData(MultipartFile file, ImportService importService,char separator){
        ImportBody importBody = new ImportBody();
        List<CsvMapping> data = csvService.parseCSV(file, importService.getMapping(), separator);
        importService.importData(data,importBody);
        if (importBody.getImportException().hasErrors()) {
            throw importBody.getImportException();
        }

    }
}
