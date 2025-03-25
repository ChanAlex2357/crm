package site.easy.to.build.crm.exception;

import java.util.ArrayList;
import java.util.List;
public class ImportException extends RuntimeException {
    private final int line;
    private List<String> errors;
    protected ImportException(int line, List<String> errors) {
        this.line = line;
        this.errors = errors;
    }
    public ImportException(int line) {
        this.line = line;
        errors = new ArrayList<String>();
    }
    public void addError(Exception exception) {
        errors.add(exception.getMessage());
    }

    public void addError(String erroMessage){
        errors.add(erroMessage);
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
}
