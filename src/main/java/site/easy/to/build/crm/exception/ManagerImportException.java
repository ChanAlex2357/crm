package site.easy.to.build.crm.exception;

import java.util.List;

public class ManagerImportException extends Exception{
    List<AdminImportException> errors;
    public ManagerImportException(List<AdminImportException> errors) {
        this.errors = errors;
    }
    public String getErrorHtml(){
      String html = "";
      for (AdminImportException error : errors) {;
        html += "<h3> FILE : "+error.getFileSource().getName()+" errors </h3>";
        for (ImportException importException : error.getErrors().values()) {
            html += importException.getHtml("");
        }  
      } 
      return html;
   }
}
