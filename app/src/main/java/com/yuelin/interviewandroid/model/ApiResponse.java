package com.yuelin.interviewandroid.model;

import com.google.gson.JsonObject;

public class ApiResponse {
    public boolean isError;
    public String message;
    public int code;
    public JsonObject data;
}
