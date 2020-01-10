package com.example.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 所有自定义controller的丁姐接口，封装常用的与session和response、request相关的操作
 */
public abstract class GenericController {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .serializeNulls()
            .create();
    protected Result result;

    /**
     * 客户端返回JSON字符串
     * @param response
     * @param object
     * @return
     */
    protected String renderSuccessString(HttpServletResponse response, Object object){
        result = new Result();
        result.setResult(true);
        result.setDatum(object);
        result.setReason("SUCCESS");
        return renderString(response,result);
    }
    protected String renderErrorString(HttpServletResponse response,String reason){
        result = new Result();
        result.setDatum(null);
        result.setResult(false);
        result.setReason(reason);
        return renderString(response,result);
    }
    protected String renderString(HttpServletResponse response,Object object){
        return renderString(response,gson.toJson(object),"application/json");
    }

    /**
     * 客户端返回字符串
     * @param response
     * @param string
     * @param type
     * @return
     */
    protected String renderString(HttpServletResponse response,String string,String type){
        try{
            response.reset();
            response.setContentType(type);
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(string);
            return null;
        }catch(IOException e){
            return null;
        }
    }
}
