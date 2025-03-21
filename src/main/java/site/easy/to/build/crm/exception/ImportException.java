package site.easy.to.build.crm.exception;

public class ImportException extends RuntimeException {
    public ImportException(Exception source,int line) {
        super(line+": "+source.getMessage());
    }
    public ImportException(String source,int line) {
        super(line+": "+source);
    }
    
}
