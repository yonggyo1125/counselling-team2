package com.thxforservice.global.rests;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
public class JSONData {
    private HttpStatus status = HttpStatus.OK;
    private boolean success = true;
    private Object message;
    private Object data;

    public JSONData(Object data) {
        this.data = data;
    }

    public JSONData(boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }
}