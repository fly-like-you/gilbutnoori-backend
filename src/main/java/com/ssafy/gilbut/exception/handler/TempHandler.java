package com.ssafy.gilbut.exception.handler;

import com.ssafy.gilbut.advice.code.BaseErrorCode;
import com.ssafy.gilbut.exception.GeneralException;

public class TempHandler extends GeneralException {

    public TempHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}