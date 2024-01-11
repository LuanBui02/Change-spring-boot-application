package com.managebankaccount.managebankaccount.accountException;

public class AlreadyReported extends RuntimeException{
    public AlreadyReported(long id) {
        super(String.format("This id %d is already used", id));
    }
}
