package com.yryun.system.utils;

import lombok.Data;


@Data
public class ApiResponse<T>{

    /**
     * 状态码
     */
    private Integer status;
    /**
     * 提示信息，如果有错误时，前端可以获取该字段进行提示
     */
    private String msg;
    /**
     * 查询到的结果数据，
     */
    private T data;

    public ApiResponse(int status, String message, T data) {
        this.status = status;
        this.msg = message;
        this.data = data;
    }

    // 静态方法：成功返回
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(Constants.HttpStatus.SUCCESS, "Success", data);
    }

    // 静态方法：失败返回
    public static <T> ApiResponse<T> error(int status, String msg) {
        return new ApiResponse<>(status, msg, null);
    }

}