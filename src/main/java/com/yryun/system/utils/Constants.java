package com.yryun.system.utils;

public class Constants {

    // HTTP 状态码
    public static class HttpStatus {
        /**
         * 成功
         */
        public static final int SUCCESS = 200;

        /**
         * 已创建
         */
        public static final int CREATED = 201;

        /**
         * 已接受
         */
        public static final int ACCEPTED = 202;

        /**
         * 无内容
         */
        public static final int NO_CONTENT = 204;

        /**
         * 请求错误
         */
        public static final int BAD_REQUEST = 400;

        /**
         * 未授权
         */
        public static final int UNAUTHORIZED = 401;

        /**
         * 权限不足
         */
        public static final int FORBIDDEN = 403;

        /**
         * 资源未找到
         */
        public static final int NOT_FOUND = 404;
        public static final int METHOD_NOT_ALLOWED = 405;
        public static final int CONFLICT = 409;
        public static final int UNPROCESSABLE_ENTITY = 422;
        public static final int INTERNAL_SERVER_ERROR = 500;
        public static final int BAD_GATEWAY = 502;
        public static final int SERVICE_UNAVAILABLE = 503;
        public static final int GATEWAY_TIMEOUT = 504;
    }

    // 自定义状态码
    public static class CustomStatus {
        /**
         * 服务器防御触发
         */
        public static final int SERVER_DEFENSE_TRIGGERED = 5144;

        /**
         * 当前会话超时
         */
        public static final int CURRENT_SESSION_TIMEOUT = 11;

        /**
         * 令牌验证失败
         */
        public static final int TOKEN_VERIFICATION_FAILED = 12;

        /**
         * 成功
         */
        public static final int OK = 0;
    }

    // 错误码
    public static class ErrorCodes {
        /**
         * 无效输入
         */
        public static final int INVALID_INPUT = 1001;

        /**
         * 数据库错误
         */
        public static final int DB_ERROR = 1002;

        /**
         * 网络错误
         */
        public static final int NETWORK_ERROR = 1003;

        /**
         * 文件未找到
         */
        public static final int FILE_NOT_FOUND = 1004;
        // ... 其他错误码
    }

    // ... 其他常量分组


    // 私有构造方法，避免实例化
    private Constants() {
    }
}