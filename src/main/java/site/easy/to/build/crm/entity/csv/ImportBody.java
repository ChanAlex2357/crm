package site.easy.to.build.crm.entity.csv;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import site.easy.to.build.crm.exception.AdminImportException;
import site.easy.to.build.crm.exception.ImportException;
@Data
public class ImportBody {
    List<Object> data;
    AdminImportException importException;

    public ImportBody(List<Object> data, AdminImportException importException) {
        this.data = data;
        this.importException = importException;
    }
    public ImportBody() {
        this(new ArrayList<>(), new AdminImportException());
    }

    public void addData(Object data) {
        this.data.add(data);
    }
    public boolean hasErrors() {
        return importException.hasErrors();
    }
    public void addError(String message , int line) {
        // importException.addError(message,line);
    } 

    public void addError(ImportException error) {
        importException.addError(error);
    }
}
