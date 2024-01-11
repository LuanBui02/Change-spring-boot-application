package com.managebankaccount.managebankaccount.accountException;

public class NoSpaceInPassword extends RuntimeException{
    public NoSpaceInPassword() {
        super("Password can not have space");
    }
}
