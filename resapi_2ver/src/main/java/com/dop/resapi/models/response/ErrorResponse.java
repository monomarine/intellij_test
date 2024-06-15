package com.dop.resapi.models.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    HttpStatus status;
    String message;

    public ErrorResponse(HttpStatus stat, String mess){
        status = stat;
        message  = mess;
    }

}


