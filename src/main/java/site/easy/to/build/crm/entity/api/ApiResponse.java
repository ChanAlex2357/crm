package site.easy.to.build.crm.entity.api;

import lombok.Data;
import site.easy.to.build.crm.enums.ApiResponseStatus;

@Data
public class ApiResponse {
    int status;
    String message;
    Object data;
    Object errors;

    public ApiResponse(ApiResponseStatus status,String message , Object data,Object errors){
        this(status.getStatus(),message,data,errors);
    }
    public ApiResponse(int status,String message , Object data,Object errors){
        setStatus(status);
        setMessage(message);
        setData(data);
        setErrors(errors);
    }
}

