package site.easy.to.build.crm.builder;

import site.easy.to.build.crm.entity.api.ApiResponse;
import site.easy.to.build.crm.enums.ApiResponseStatus;
public class ApiResponseBuilder {
    public static ApiResponse success(String message , Object data){
        if (message == null) {
            message = "success";
        }
        return new ApiResponse(ApiResponseStatus.SUCCESS,message,data,null);
    }

    public static ApiResponse error(ApiResponseStatus status,String message,Object errors){
        return new ApiResponse(status, message,null, errors);
    }
    public static ApiResponse error(ApiResponseStatus responseStatus,Exception exception){
        return error(responseStatus, exception.getMessage(), exception);
    }

    public static ApiResponse error400(String message,Object errors){
        return error(ApiResponseStatus.ERROR, message, errors);
    }
    public static ApiResponse error400(Exception error){
        return error(ApiResponseStatus.ERROR,error);
    }
    
    public static ApiResponse error500(String message,Object errors){
        return error(ApiResponseStatus.SERVER_ERROR, message, errors);
    }
    public static ApiResponse error500(Exception exception){
        return error(ApiResponseStatus.SERVER_ERROR,exception);
    }
}
