package site.easy.to.build.crm.entity.csv;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import site.easy.to.build.crm.exception.AdminImportException;

@Data
public class FileCsvResult {
    MultipartFile fileSource;
    List<Object> data;
    AdminImportException exceptions;
    public FileCsvResult (MultipartFile file){
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

    public void addErrors(List<String> errors) {
        exceptions.addError(errors);
    }
}
