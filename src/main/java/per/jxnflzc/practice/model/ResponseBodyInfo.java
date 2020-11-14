package per.jxnflzc.practice.model;

import per.jxnflzc.practice.model.enums.ResponseCode;

import java.io.Serializable;

public class ResponseBodyInfo<T> implements Serializable {
    private String code;
    private String message;
    private T data;

    private ResponseBodyInfo(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    private ResponseBodyInfo(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> ResponseBodyInfo<T> success(T data) {
        return new ResponseBodyInfo<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getDesc(), data);
    }

    public static <T> ResponseBodyInfo<T> success(String message, T data) {
        return new ResponseBodyInfo<>(ResponseCode.SUCCESS.getCode(), message, data);
    }

    public static <T> ResponseBodyInfo<T> error() {
        return new ResponseBodyInfo<>(ResponseCode.FAIL.getCode(), ResponseCode.FAIL.getDesc());
    }

    public static <T> ResponseBodyInfo<T> error(String message) {
        return new ResponseBodyInfo<>(ResponseCode.FAIL.getCode(), message);
    }

    public static <T> ResponseBodyInfo<T> build(String code, String message, T data) {
        return new ResponseBodyInfo<>(code, message, data);
    }

    public static <T> ResponseBodyInfo<T> build(ResponseCode code, String message, T data) {
        return new ResponseBodyInfo<>(code.getCode(), message, data);
    }

    public static <T> ResponseBodyInfo<T> build(ResponseCode code, T data) {
        return new ResponseBodyInfo<>(code.getCode(), code.getDesc(), data);
    }

    public static <T> ResponseBodyInfo<T> build(ResponseCode code) {
        return new ResponseBodyInfo<>(code.getCode(), code.getDesc());
    }
}
