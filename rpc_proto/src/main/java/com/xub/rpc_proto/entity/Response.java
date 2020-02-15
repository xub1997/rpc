package com.xub.rpc_proto.entity;

import com.xub.rpc_proto.enums.ResponseCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xub
 * @Name: Response 表示RPC的返回
 * @Description: TODO
 * @date 2020/2/14  21:10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Response {

    private Integer code;
    private String msg;
    private Object data;

    public Response() {
    }

    public Response(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Response(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Response success() {
        return new Response(ResponseCodeEnum.SUCCESS.getCode(), ResponseCodeEnum.SUCCESS.getMsg());
    }

    public static Response success(String msg) {
        return new Response(ResponseCodeEnum.SUCCESS.getCode(), msg);
    }

    public static Response success(Integer code, String msg) {
        return new Response(code, msg);
    }

    public static Response success(String msg, Object data) {
        return new Response(ResponseCodeEnum.SUCCESS.getCode(), msg, data);
    }

    public static Response success(String msg, String dataDesc, Object data) {
        Map<String, Object> resultMap = new HashMap<>(1);
        resultMap.put(dataDesc, data);
        return new Response(ResponseCodeEnum.SUCCESS.getCode(), msg, resultMap);
    }

    public static Response success(Integer code, String msg, Object data) {
        return new Response(code, msg, data);
    }


    public static Response success(Integer code, String msg, String dataDesc, Object data) {
        Map<String, Object> resultMap = new HashMap<>(1);
        resultMap.put(dataDesc, data);
        return new Response(code, msg, resultMap);
    }


    public static Response fail(String msg) {
        return new Response(ResponseCodeEnum.FAIL.getCode(), msg);
    }

    public static Response fail(Integer code, String msg) {
        return new Response(code, msg);
    }

    public static Response fail(String msg, Object data) {
        return new Response(ResponseCodeEnum.FAIL.getCode(), msg, data);
    }

    public static Response fail(String msg, String dataDesc, Object data) {
        Map<String, Object> resultMap = new HashMap<>(1);
        resultMap.put(dataDesc, data);
        return new Response(ResponseCodeEnum.FAIL.getCode(), msg, resultMap);
    }

    public static Response fail(Integer code, String msg, Object data) {
        return new Response(code, msg, data);
    }


    public static Response fail(Integer code, String msg, String dataDesc, Object data) {
        Map<String, Object> resultMap = new HashMap<>(1);
        resultMap.put(dataDesc, data);
        return new Response(code, msg, resultMap);
    }
}
