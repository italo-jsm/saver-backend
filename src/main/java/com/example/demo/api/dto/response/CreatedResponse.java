package com.example.demo.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreatedResponse {
    private String responseType;
    private String responseValue;

    public static final String RESOURCE_ID = "resourceId";
    public static final String ERROR = "resourceId";
    public static final String FILENAME = "filename";

    public static CreatedResponse withResourceId(String resourceId){
        return new CreatedResponse(CreatedResponse.RESOURCE_ID, resourceId);
    }

    public static CreatedResponse withError(String errorMessage){
        return new CreatedResponse(CreatedResponse.ERROR, errorMessage);
    }

    public static CreatedResponse withFileName(String fileName){
        return new CreatedResponse(CreatedResponse.FILENAME, fileName);
    }
}
