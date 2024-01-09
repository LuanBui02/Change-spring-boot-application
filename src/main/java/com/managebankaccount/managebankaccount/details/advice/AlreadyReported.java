package com.managebankaccount.managebankaccount.details.advice;

public class AlreadyReported extends RuntimeException{
    public AlreadyReported(long id) {
        super(String.format("This id %d is already used", id));
    }
}
