package com.managebankaccount.managebankaccount.accountException;

import com.managebankaccount.managebankaccount.utils.Constant;

public class ConditionWithDraw extends RuntimeException{
    public ConditionWithDraw() {
        super(Constant.conditionWithDraw);
    }
}
