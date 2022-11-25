package com.example.registerloginapp.errors;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResult {

    private HttpStatus httpStatus;
    private String timestamp = nowDate();
    private int status;
    private String error;
    private String message;
    private String path;

    //Now Date
    private String nowDate() {
        Locale locale = new Locale("tr", "TR");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MMMM.yyyy HH:mm:ss", locale);
        Date date = new Date();
        String change = simpleDateFormat.format(date);
        System.out.println(change);
        return change;
    }

    //parametreli constructor
    public ApiResult(int status,  String message, String path) {
        this.timestamp = nowDate();
        this.status = status;
        this.message = message;
        this.path = path;
    }

    public ApiResult(int status, String error, String message, String path) {
        this.timestamp = nowDate();
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

}
