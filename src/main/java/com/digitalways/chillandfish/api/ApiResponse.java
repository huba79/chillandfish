/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digitalways.chillandfish.api;

import java.util.List;

/**
 *
 * @author huba
 */
public class ApiResponse<T> {
    private String status;
    private List<String> errors;
    private T data;

    public ApiResponse(String status, List<String> errors, T data) {
        this.status = status;
        this.errors = errors;
        this.data = data;
    }

    public ApiResponse(String status, List<String> errors) {
        this.status = status;
        this.errors = errors;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
    
    
    
    
}
