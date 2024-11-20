package com.ssafy.gilbut.exception.handler;

import com.ssafy.gilbut.advice.code.BaseErrorCode;
import com.ssafy.gilbut.exception.GeneralException;

public class GeneralExceptionHandler extends GeneralException {

    public GeneralExceptionHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}