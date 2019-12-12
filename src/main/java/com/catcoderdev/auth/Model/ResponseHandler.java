package com.catcoderdev.auth.Model;


import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {

    private boolean error;
    private Object data;
    private int statusCode;
    private String message;
    private Map<String, Object> meta;


    public ResponseHandler(boolean error, Object data, int statusCode, String message) {
        this.setError(error);
        this.setData(data);
        this.setStatusCode(statusCode);
        this.setMessage(message);
    }

    public ResponseHandler() {
        super();
    }

    public Map<String, Object> getMeta() {
        return meta;
    }

    public void setMeta(Map<String, Object> meta) {
        this.meta = meta;
    }

    /**
     * @return
     */
    public boolean getError() {
        return this.error;
    }

    /**
     * @param error
     */
    public void setError(boolean error) {
        this.error = error;
    }

    /**
     * @return
     */
    public Object getData() {
        return this.data;
    }

    /**
     * @param data
     */
    public void setData(Object data) {
        this.data = data;
    }

    /**
     * @return
     */
    public int getStatusCode() {
        return this.statusCode;
    }

    /**
     * @param statusCode
     */
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * @return String
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    public ResponseHandler body() {
        this.message = this.getMessage();
        this.statusCode = this.getStatusCode();
        this.data = this.getData();
        this.error = this.getError();
        this.meta = this.getMeta();
        return this;
    }

    public ResponseHandler notFound() {
        this.setMessage("Not found");
        this.setData(new ArrayList<>());
        this.setStatusCode(HttpStatus.NOT_FOUND.value());
        this.setError(true);
        this.getMeta();
        return this;
    }

    public ResponseHandler buildMetaData(int total, int page, int total_pages) {
        Map<String, Object> meta = new HashMap<>();
        meta.put("total", total);
        meta.put("page", page);
        meta.put("total_pages", total_pages);
        this.setMeta(meta);
        return this;
    }
}
