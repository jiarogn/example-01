package org.example.exampleapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ResponseBody;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {

    private boolean success; //是否成功
    private String code; //状态码
    private String msg; //返回信息
    private T data; //返回数据

    // 构建失败响应
    public static <T> Response<T> buildFailure(String code, String msg) {
        Response<T> response = new Response<>();
        response.setSuccess(false);
        response.setCode(code);
        response.setMsg(msg);
        return response;
    }

    // 构建成功响应
    public static <T> Response<T> buildSuccess(String code, String msg, T data) {
        Response<T> response = new Response<>();
        response.setSuccess(true);
        response.setCode(code);
        response.setMsg(msg);
        response.setData(data);
        return response;
    }

}
