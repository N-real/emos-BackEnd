package com.nreal.aop;

import com.nreal.common.response.Result;
import com.nreal.web.shiro.ThreadLocalToken;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TokenAspect {

    @Autowired
    private ThreadLocalToken threadLocalToken;

    @Pointcut("execution(public * com.nreal.controller.*.*(..))")
    public void aspect(){

    }

    @Around("aspect()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Result res = (Result) point.proceed();
        String token = threadLocalToken.getToken();
        // 如果threadLocal存在token，说明是更新的token
        if(token!=null){
            res.put("token",token);
            threadLocalToken.clear();
        }
        return res;
    }

}
