package com.xub.rpc_proto.enums;

import lombok.Getter;
import lombok.NonNull;

/**
 * 返回值枚举
 */
@Getter
public enum ResponseCodeEnum {

    /**
     * 操作失败
     */
    FAIL(-1, "操作失败"),

    /**
     * 操作成功
     */
    SUCCESS(0, "操作成功"),

    /**
     * 未知操作
     */
    UNKNOWN_OPERATION(10000, "未知操作");

    private final Integer code;
    private final String msg;


    ResponseCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 根据code获取枚举
     *
     * @param code
     * @return
     */
    public static ResponseCodeEnum getByCode(@NonNull Integer code) {
        ResponseCodeEnum[] values = ResponseCodeEnum.values();
        for (ResponseCodeEnum value : values) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return UNKNOWN_OPERATION;
    }
}
