package com.managebankaccount.managebankaccount.details.exception;

import com.managebankaccount.managebankaccount.details.Constant;

public class ConditionWithDraw extends RuntimeException{
    public ConditionWithDraw() {
        super(Constant.conditionWithDraw);
    }
}
