package com.project.uber.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse <T>{

    private boolean status;
    private String message;
    private T data;
    private String path;
    private HttpStatus httpStatus;

}
