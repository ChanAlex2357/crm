package site.easy.to.build.crm.exception;

import java.util.ArrayList;
import java.util.List;

public class AdminImportException extends RuntimeException {
    
    private List<ImportException> errors ;
    private int currentLine = 0;
    public AdminImportException() {
        super("Error while importing data");
        setErrors(new ArrayList<>());
    }

    public List<ImportException> getErrors() {
        return errors;
    }

    private void setErrors(List<ImportException> errors) {
        this.errors = errors;
    }

    public void addError(ImportException error) {
        errors.add(error);
    }
    public void addError(List<String> errorMessages) {
        addError(new ImportException(currentLine++ , errorMessages));
    }

    public boolean hasErrors() {
        return errors != null && !getErrors().isEmpty();
    }
    
}
