package com.managebankaccount.managebankaccount.accountException;

public class AlreadyReportedException extends RuntimeException{
    public AlreadyReportedException(long id) {
        super(String.format("This id %d is already used", id));
    }
}
