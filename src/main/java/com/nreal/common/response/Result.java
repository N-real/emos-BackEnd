package com.nreal.common.response;

import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class Result extends HashMap<String,Object> {

    public Result(){
        put("code", HttpStatus.SC_OK);
        put("msg","success");
    }

    public Result put(String key,Object value){
        super.put(key,value);
        return this;
    }

    public static Result ok(){
        return new Result();
    }

    public static Result ok(String msg){
        Result res = new Result();
        res.put("msg",msg);
        return res;
    }

    public static Result ok(Map<String,Object> map){
        Result res = new Result();
        res.putAll(map);
        return res;
    }

    public static Result error(int code,String msg){
        Result res=new Result();
        res.put("code",code);
        res.put("msg",msg);
        return res;
    }

    public static Result error(String msg){
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR,msg);
    }

    public static Result error(){
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR,"未知异常，请联系管理员");
    }

}
