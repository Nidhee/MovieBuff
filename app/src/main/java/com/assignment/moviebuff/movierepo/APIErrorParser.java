package com.assignment.moviebuff.movierepo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class APIErrorParser {
    @SerializedName("status_code")
    @Expose
    private int errorCode;
    @SerializedName("status_message")
    @Expose
    private String errorMessage;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
