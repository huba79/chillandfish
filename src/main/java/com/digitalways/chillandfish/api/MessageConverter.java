/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digitalways.chillandfish.api;

import java.util.List;

/**
 * @param <T> the response-specific data
 * @author huba
 */
public class MessageConverter<T> {
    public ApiResponse<T> convertToResponse(T data, String businessStatus, List<String> errors) {
        return new ApiResponse<T>(businessStatus, errors, data);
    }

//    public T interpretMessage(ApiMessage message) {
//       return (T) message.getData();
//    }

}
