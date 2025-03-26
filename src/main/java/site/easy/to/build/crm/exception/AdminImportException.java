package site.easy.to.build.crm.exception;

import java.util.HashMap;

import org.springframework.web.multipart.MultipartFile;

import site.easy.to.build.crm.enums.ImportErrorStatus;
public class AdminImportException extends RuntimeException {
    private HashMap<Integer,ImportException> errors;
    private ImportErrorStatus errorStatus ;
    private MultipartFile fileSource;
    public AdminImportException(MultipartFile file){
        setErrors(new HashMap<Integer,ImportException>());
        this.errorStatus = new ImportErrorStatus();
        this.fileSource = file;
    }
    private void addError(ImportException importException) {
        getErrors().put(importException.getLine(), importException);
    }
    public boolean hasErrors() {
        return errorStatus.isError();
    }

    private void setErrors(HashMap<Integer, ImportException> errors) {
        this.errors = errors;
    }

    public MultipartFile getFileSource() {
        return fileSource;
    }

    public HashMap<Integer, ImportException> getErrors() {
        return errors;
    }
    public ImportException getImportException(int line){
        return getErrors().get(line);
    }
    public ImportException getOrCreate(int line){
        ImportException e = getImportException(line);
        if (e == null) {
            e = new ImportException(line,this.errorStatus);
            addError(e);
        }
        return e;
    }
    
}
