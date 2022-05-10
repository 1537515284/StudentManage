package com.work.utils;

/**
 * 统一返回结果状态信息类
 *
 */
public enum ResultCodeEnum {

    SUCCESS(200,"成功"),
    FAIL(201, "失败"),
    SERVICE_ERROR(202, "服务异常"),
    ILLEGAL_REQUEST( 203, "非法请求"),
    ARGUMENT_VALID_ERROR(204, "参数校验错误"),
    LOGIN_ERROR(205, "用户名或密码错误"),
    LOGIN_AUTH(206, "未登陆"),
    PERMISSION(207, "没有权限"),
    LOGIN_CODE(208,"长时间未操作,会话已失效,请刷新页面后重试!"),
    CODE_ERROR(209,"验证码错误!"),
    ;

    private Integer code;

    private String message;

    private ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}