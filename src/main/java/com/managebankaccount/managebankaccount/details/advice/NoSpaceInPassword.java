package com.managebankaccount.managebankaccount.details.advice;

public class NoSpaceInPassword extends RuntimeException{
    public NoSpaceInPassword() {
        super("Password can not have space");
    }
}
