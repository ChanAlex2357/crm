package site.easy.to.build.crm.exception;

import java.util.ArrayList;
public class ImportException extends RuntimeException {
    private final int line;
    private ArrayList<String> errors;
    public ImportException(int line) {
        this.line = line;
        errors = new ArrayList<String>();
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
