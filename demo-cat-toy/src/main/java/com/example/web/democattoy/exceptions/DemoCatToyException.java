package com.example.web.democattoy.exceptions;

public class DemoCatToyException extends Exception {
    public DemoCatToyException(ErrMsg errMsg) {
        super(errMsg.getDescription());
    }

    public DemoCatToyException(String msg){
        super(msg);
    }
}
