package com.yryun.system.utils;

import java.io.Serial;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: Kruarix
 * @time: 2023/8/10 14:53
 */
public class ApiR extends HashMap<String, Object> {
    @Serial
    private static final long serialVersionUID = 122094803127921820L;

    /**
     * 默认构造函数，初始化为成功状态
     */
    public ApiR(){
        put("code", Constants.CustomStatus.OK);
        put("msg", "Success");
    }

    /**
     * 构造一个错误响应
     * @param code 错误代码
     * @param msg 错误信息
     * @return ApiR 对象
     */
    public static ApiR error(int code, String msg){
        ApiR apiR = new ApiR();
        apiR.put("code", code);
        apiR.put("msg", msg);
        apiR.put("success", false);
        return apiR;
    }

    /**
     * 构造一个成功响应
     * @param data 返回的数据
     * @param msg 成功信息
     * @return ApiR 对象
     */
    public static ApiR success(Object data, String msg) {
        ApiR apiR = new ApiR();
        apiR.put("code", Constants.HttpStatus.SUCCESS);
        apiR.put("data", data);
        apiR.put("msg", msg);
        apiR.put("success", true);
        return apiR;
    }

    /**
     * 构造一个成功响应
     * @param data 返回的数据
     * @return ApiR 对象
     */
    public static ApiR success(Object data) {
        return success(data, "success");
    }

    /**
     * 构造一个成功响应
     * @param msg 成功信息
     * @return ApiR 对象
     */
    public static ApiR ok(String msg) {
        ApiR apiR = new ApiR();
        apiR.put("msg", msg);
        return apiR;
    }

    /**
     * 构造一个成功响应，从给定的 Map 中复制键值对
     * @param map 包含响应数据的 Map
     * @return ApiR 对象
     */
    public static ApiR ok(Map<String, Object> map) {
        ApiR apiR = new ApiR();
        apiR.putAll(map);
        return apiR;
    }

    /**
     * 构造一个成功响应
     * @return ApiR 对象
     */
    public static ApiR ok() {
        return new ApiR();
    }

    /**
     * 添加键值对到响应对象中
     * @param key 键
     * @param value 值
     * @return ApiR 对象
     */
    public ApiR put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
