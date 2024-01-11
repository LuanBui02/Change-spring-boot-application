package com.managebankaccount.managebankaccount.accountException;

public class NoSpaceInPasswordException extends RuntimeException{
    public NoSpaceInPasswordException() {
        super("Password can not have space");
    }
}
