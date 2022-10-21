package com.digitalways.chillandfish.api;

import java.util.List;

/**
 * @param <T> the response-specific data
 * @author huba
 */
public class MessageConverter<T> {
    public ApiResponse<T> messageToResponse(T data, String businessStatus, List<String> errors) {
        return new ApiResponse<T>(businessStatus, errors, data);
    }

}
