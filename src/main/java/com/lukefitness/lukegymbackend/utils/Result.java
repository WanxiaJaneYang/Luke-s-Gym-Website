package com.lukefitness.lukegymbackend.utils;

import java.io.Serializable;

public class Result<T> implements Serializable {
    private Integer code;
    private String message;
    private T data;
    boolean success;

    // constructor with no parameters
    public Result() {
        this.code = ResultCodeEnum.SUCCESS.getCode();
        this.message = ResultCodeEnum.SUCCESS.getMessage();
        this.success = true;
    }

    /**
     * constructor with data parameter, used for success
     * @param data - data to be returned
     */
    public Result(T data) {
        this.code = ResultCodeEnum.SUCCESS.getCode();
        this.message = ResultCodeEnum.SUCCESS.getMessage();
        this.data = data;
        this.success = true;
    }

    /**
     * constructor with resultCodeEnum parameter, used for errors
     * @param resultCodeEnum - resultCodeEnum to be returned
     */
    public Result(ResultCodeEnum resultCodeEnum) {
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
        this.success = false;
    }

    /**
     * constructor with resultCodeEnum and message parameters, used for errors with custom messages
     * @param resultCodeEnum - resultCodeEnum to be returned
     * @param message - message to be returned
     */
    public Result(ResultCodeEnum resultCodeEnum, String message) {
        this.code = resultCodeEnum.getCode();
        this.message = message;
        this.success = false;
    }

    /**
     * static method to return a success result
     * @return Result<T> - success result
     */
    public static <T> Result<T> success() {
        return new Result<T>();
    }

    /**
     * static method to return a success result with data
     * @param data - data to be returned
     * @return Result<T> - success result with data
     */
    public static <T> Result<T> success(T data) {
        return new Result<T>(data);
    }

    /**
     * static method to return an error result
     * @param resultCodeEnum - resultCodeEnum to be returned
     * @return Result<T> - error result
     */
    public static <T> Result<T> error(ResultCodeEnum resultCodeEnum) {
        return new Result<T>(resultCodeEnum);
    }

    /**
     * static method to return an error result with custom message
     * @param resultCodeEnum - resultCodeEnum to be returned
     * @param message - message to be returned
     * @return Result<T> - error result with custom message
     */
    public static <T> Result<T> error(ResultCodeEnum resultCodeEnum, String message) {
        return new Result<T>(resultCodeEnum, message);
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "Result(code=" + this.getCode() + ", message=" + this.getMessage() + ", data=" + this.getData() + ", success=" + this.isSuccess() + ")";
    }
}
