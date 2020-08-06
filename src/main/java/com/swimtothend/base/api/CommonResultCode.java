package com.swimtothend.base.api;

public enum CommonResultCode implements ResultCodeable {
    EXECUTE_SUCCESS("0000", "处理成功"),
    EXECUTE_FAILURE("9000", "处理失败");

    private String code;
    private String message;

    CommonResultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
