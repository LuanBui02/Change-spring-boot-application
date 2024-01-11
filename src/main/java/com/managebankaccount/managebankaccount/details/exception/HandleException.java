package com.managebankaccount.managebankaccount.details.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
@RestControllerAdvice
public class HandleException {
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, String> handleNullPointerException(NullPointerException nullPointerException) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("Error code", "1");
        errorMap.put("Error Msg", nullPointerException.getMessage());
        return errorMap;
    }
    @ExceptionHandler(AlreadyReported.class)
    @ResponseStatus(value = HttpStatus.ALREADY_REPORTED)
    public Map<String, String> handleAlreadyReported(AlreadyReported alreadyReported) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("Error code", "2");
        errorMap.put("Error Msg", alreadyReported.getMessage());
        return errorMap;
    }
    @ExceptionHandler(NoSpaceInPassword.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Map<String, String> handleSpaceInPassword(NoSpaceInPassword noSpaceInPassword) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("Error code", "3");
        errorMap.put("Error Msg", noSpaceInPassword.getMessage());
        return errorMap;
    }
    @ExceptionHandler(ConditionWithDraw.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Map<String, String> handleConditionWithDraw(ConditionWithDraw conditionWithDraw) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("Error code", "4");
        errorMap.put("Error Msg", conditionWithDraw.getMessage());
        return errorMap;
    }
}