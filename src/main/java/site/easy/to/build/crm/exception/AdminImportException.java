package site.easy.to.build.crm.exception;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import lombok.Data;

@Data
public class AdminImportException extends RuntimeException {
    private HashMap<Integer,ImportException> errors;
    private int currentLine = 0;
    public AdminImportException() {

        setErrors(new HashMap<Integer,ImportException>());
    }
    public void addError(List<String> errorMessages) {
        // TODO
        // addError(new ImportException(currentLine++ , errorMessages));
    }

    public boolean hasErrors() {
        return errors != null && !getErrors().isEmpty();
    }

    public ImportException next(){
        return errors.get(currentLine++);
    }
    
}
