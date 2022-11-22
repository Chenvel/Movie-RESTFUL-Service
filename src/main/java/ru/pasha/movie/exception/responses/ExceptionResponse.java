package ru.pasha.movie.exception.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor
@Data
@ToString
public class ExceptionResponse {

    private String message;
    private Date date;
    private int code;
}
