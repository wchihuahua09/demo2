package com.example.demo.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "通用响应结果")
public class Result<T> {
    @ApiModelProperty(value = "状态码", example = "200")
    private Integer code;
    
    @ApiModelProperty(value = "消息", example = "success")
    private String message;
    
    @ApiModelProperty(value = "数据")
    private T data;

    /**
     * 成功结果
     * @param data 数据
     * @return 结果对象
     */
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(200);
        result.setMessage("success");
        result.setData(data);
        return result;
    }

    /**
     * 错误结果
     * @param code 错误码
     * @param message 错误消息
     * @return 结果对象
     */
    public static <T> Result<T> error(Integer code, String message) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }
} 