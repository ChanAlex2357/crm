package site.easy.to.build.crm.entity.csv.providers;

import org.springframework.web.multipart.MultipartFile;
import lombok.Data;
import site.easy.to.build.crm.service.csv.ImportCsvService;
@Data
public class ImportDataProvider {
    private MultipartFile file;
    private ImportCsvService importCsvService;
    private char separator;
    public ImportDataProvider(MultipartFile file,ImportCsvService service,char separator){
        setFile(file);
        setImportCsvService(service);
        setSeparator(separator);
    }
    public ImportDataProvider(MultipartFile file,ImportCsvService service){
        this(file, service,';');
    }
}
