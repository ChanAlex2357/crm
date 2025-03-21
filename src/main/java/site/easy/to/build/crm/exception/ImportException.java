package site.easy.to.build.crm.exception;

public class ImportException extends RuntimeException {
    public ImportException(Exception source,int line) {
        this(source.getMessage(), line);
    }
    public ImportException(String source,int line) {
        super("<span class=\"font-weight-bold\"> Ligne "+line+" : </span> "+source);
    }
    
}
