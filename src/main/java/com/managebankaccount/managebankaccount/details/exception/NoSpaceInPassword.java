package com.managebankaccount.managebankaccount.details.exception;

public class NoSpaceInPassword extends RuntimeException{
    public NoSpaceInPassword() {
        super("Password can not have space");
    }
}
