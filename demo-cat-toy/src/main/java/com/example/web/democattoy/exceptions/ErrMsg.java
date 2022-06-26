package com.example.web.democattoy.exceptions;

public enum ErrMsg {

    ID_NOT_FOUND("Id not found"),
    WEIGHT_ID_NEGATIVE("Weight must be positive");

    private String description;

    ErrMsg(String description) {
        this.description =description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
