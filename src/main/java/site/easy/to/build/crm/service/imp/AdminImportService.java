package site.easy.to.build.crm.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import site.easy.to.build.crm.entity.imp.CsvMapping;
import site.easy.to.build.crm.exception.AdminImportException;

@Service
public class AdminImportService<T extends CsvMapping> {
    @Autowired
    private CSVService<T> csvService;


    public void importData(MultipartFile file, ImportService<T> importService,char separator){
        AdminImportException importException = new AdminImportException();

        List<T> data = csvService.parseCSV(file, importService.getMapping(), separator);
        importService.importData(data,importException);
        if (importException.hasErrors()) {
            throw importException;
        }
    }
}
