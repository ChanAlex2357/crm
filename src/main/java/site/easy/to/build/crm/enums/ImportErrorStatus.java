package site.easy.to.build.crm.enums;

public class ImportErrorStatus {
    boolean error = false;
    public boolean isError() {
        return error;
    }
    public void setError(){
        this.error = true;
    }
}
