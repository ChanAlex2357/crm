package site.easy.to.build.crm.exception;

public class ImportException extends RuntimeException {
    public ImportException(Exception source,int line) {
        super("Error importing data at line "+line+": "+source.getMessage());
    }
    
}
