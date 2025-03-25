package site.easy.to.build.crm.entity.csv.results;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import site.easy.to.build.crm.exception.AdminImportException;
import site.easy.to.build.crm.exception.ImportException;

@Data
public class ImportFileCsvResult {
    MultipartFile fileSource;
    String dataStr;
    List<Object> data;
    AdminImportException exceptions;
    public ImportFileCsvResult (MultipartFile file){
        setFileSource(file);
        setData(new ArrayList<>());
        setExceptions(new AdminImportException());
    }
    public void addData(Object data) {
        this.data.add(data);
    }
    public boolean hasErrors() {
        return exceptions.hasErrors();
    }

    public ImportException getImportException(int line){
        return exceptions.getErrors().get(line);
    }
}
