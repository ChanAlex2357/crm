package site.easy.to.build.crm.exception;

import java.util.ArrayList;
import java.util.List;

import site.easy.to.build.crm.enums.ImportErrorStatus;
public class ImportException extends RuntimeException {
    private final int line;
    private final List<String> errors;
    private ImportErrorStatus  errorStatus;
    
    public ImportException(int line,ImportErrorStatus errorStatus) {
        this.line = line;
        errors = new ArrayList<String>();
        this.errorStatus = errorStatus;
    }
    public void addError(Exception exception) {
        errors.add(exception.getMessage());
    }

    public void addError(String erroMessage){
        if (!this.errorStatus.isError()) {
            this.errorStatus.setError();
        }
        errors.add(erroMessage);
    }
    public void setErrorStatus(ImportErrorStatus errorStatus) {
        this.errorStatus = errorStatus;
    }
    public String getHtml() {
        String header = """
            <table>
                <tbody>
                """;
        String footer = """
                </tbody>
            </table>
        """;
        String body = "<tr><span class=\"font-weight-bold\">"+line+"</span></tr><tr><ul>";
        for (String error : errors) {
            body += "<li>"+error+"</li>";
        }
        body += "</ul></tr>";
        String html = header + body + footer;
        return html;
    }

    public int getLine() {
        return line;
    }
    public List<String> getErrors() {
        return errors;
    }
    public boolean hasEsrrors() {
        if(errors.size() == 0){
            return true;
        }
        return false;
    }
}
