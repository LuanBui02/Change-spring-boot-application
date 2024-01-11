package com.managebankaccount.managebankaccount.accountException;

import com.managebankaccount.managebankaccount.utils.Constant;

public class ConditionWithDrawException extends RuntimeException{
    public ConditionWithDrawException() {
        super(Constant.conditionWithDraw);
    }
}
