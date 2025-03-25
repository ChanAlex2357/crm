package site.easy.to.build.crm.exception;

import java.util.ArrayList;
import java.util.List;

public class AdminImportException extends RuntimeException {
    
    private List<ImportException> errors ;
    private int currentLine = 1;
    public AdminImportException() {
        super("Error while importing data");
        setErrors(new ArrayList<>());
    }

    public AdminImportException(List<ImportException> errors) {
        this();
        setErrors(errors);
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
    public void addError(String message) {
        // addError(new ImportException(message, this.currentLine++));
    }

    public boolean hasErrors() {
        return errors != null && !getErrors().isEmpty();
    }
    
}
