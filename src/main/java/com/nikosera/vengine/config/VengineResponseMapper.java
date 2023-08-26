package com.nikosera.vengine.config;

/**
 * @author Bikash Shah
 */
public class VengineResponseMapper {

    public static VengineResponse convertToSuccessVengineResponse(String message){
        VengineResponse response = new VengineResponse();
        response.setStatusCode("0");
        response.setSuccessful(true);
        response.setStatusMessage(message);
        return response;
    }

    public static VengineResponse convertToSuccessVengineResponse(String message,Object object){
        VengineResponse response = new VengineResponse();
        response.setStatusCode("0");
        response.setSuccessful(true);
        response.setStatusMessage(message);
        response.setObj(object);
        return response;
    }

    public static VengineResponse convertToFailureVengineResponse(String message){
        VengineResponse response = new VengineResponse();
        response.setStatusCode("2");
        response.setSuccessful(false);
        response.setStatusMessage(message);
        return response;
    }

    public static VengineResponse convertToFailureVengineResponse(String message,Object object){
        VengineResponse response = new VengineResponse();
        response.setStatusCode("2");
        response.setSuccessful(false);
        response.setStatusMessage(message);
        response.setObj(object);
        return response;
    }
}
