package com.italycalibur.ciallo.wms.core.common;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;
import com.italycalibur.ciallo.wms.core.constants.ResultCode;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @description: 统一返回结果
 * @author dhr
 * @date 2025-02-06 14:26:42
 * @version 1.0
 */
@Schema(description = "统一返回结果")
public record Result<T>(@Schema(description = "是否成功") Boolean success,
                        @Schema(description = "状态码") Integer code,
                        @Schema(description = "提示信息") String message,
                        @Schema(description = "返回数据") T data) {
    public static <T> Result<T> ok(T data) {
        return new Result<>(Boolean.TRUE, ResultCode.SUCCESS, "操作成功", data);
    }

    public static <T> Result<T> ok() {
        return ok(null);
    }

    public static <T> Result<T> error(Integer code, String message) {
        return new Result<>(Boolean.FALSE, code, message, null);
    }

    public static <T> Result<T> error(String message) {
        return error(ResultCode.FAILURE, message);
    }

    public static <T> Result<T> unauthorized() {
        return error(ResultCode.UNAUTHORIZED, "用户未登录或登录超时，请重新登录！");
    }

    public static <T> Result<T> forbidden() {
        return error(ResultCode.FORBIDDEN, "用户无权限，禁止访问！");
    }

    public String asJsonString() {
        return JSON.toJSONString(this, JSONWriter.Feature.WriteNulls);
    }

    // 新增Builder相关代码
    public static <T> Builder<T> builder() {
        return new Builder<>();
    }

    public static final class Builder<T> {
        private Boolean success = Boolean.TRUE;
        private Integer code = ResultCode.SUCCESS;
        private String message = "操作成功";
        private T data;

        public Builder<T> success(Boolean success) {
            this.success = success;
            return this;
        }

        public Builder<T> code(Integer code) {
            this.code = code;
            return this;
        }

        public Builder<T> message(String message) {
            this.message = message;
            return this;
        }

        public Builder<T> data(T data) {
            this.data = data;
            return this;
        }

        public Result<T> build() {
            return new Result<>(success, code, message, data);
        }
    }
}
