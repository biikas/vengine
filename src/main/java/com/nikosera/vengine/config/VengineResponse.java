package com.nikosera.vengine.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Bikash Shah
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VengineResponse {

    public boolean successful;
    public String statusCode;
    public String statusMessage;
    public String detailedMessage;
    private Object obj;
    private Object errObj;
}
