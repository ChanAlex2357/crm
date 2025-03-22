package site.easy.to.build.crm.enums;

public enum ApiResponseStatus {
    ERROR(400),
    SERVER_ERROR(500),
    SUCCESS(200);

    private final int status;
    ApiResponseStatus(int status){
        this.status = status;
    }
    public int getStatus() {
        return status;
    }
}
