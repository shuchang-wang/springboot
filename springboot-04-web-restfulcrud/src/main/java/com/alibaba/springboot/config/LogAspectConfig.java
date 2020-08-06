package com.alibaba.springboot.config;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Aspect注解定义切面类
 * @Configuration注解定义spring配置文件类似于<bean id="" class=""></bean>
 * 加入日志切面
 */
@Aspect
@Configuration
public class LogAspectConfig {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 以 controller 包下定义的所有请求为切入点
     */
    @Pointcut(value = "execution(* com.alibaba.springboot..controller..*.*(..))")
    public void webLog() {
    }

    @Around(value = "webLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        // 开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String queryString = request.getQueryString();
        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        String remoteAddr = request.getRemoteAddr();

        long startTime = System.currentTimeMillis();
        // 打印请求相关参数
        logger.info("========================================== Begin ==========================================");
        // 打印请求 url
        logger.info("URL            : {}", url);
        // 打印 Http method
        logger.info("HTTP Method    : {}", method);
        // 打印调用 controller 的全路径以及执行方法
        logger.info("Class Method   : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        // 打印请求的 IP
        logger.info("IP             : {}", remoteAddr);
        Object[] args = joinPoint.getArgs();
        String params = "";
        if (args.length > 0) {
            if ("POST".equals(method)) {
                List<Object> logArgs = Stream.of(args).filter(arg -> !(arg instanceof HttpServletRequest) &&
                        !(arg instanceof HttpServletResponse)).collect(Collectors.toList());
                params = logArgs.size() > 0 ? JSON.toJSONString(logArgs.get(0)) : params;
            } else if ("GET".equals(method)) {
                if (!StringUtils.isEmpty(queryString)) {
                    params = URLDecoder.decode(queryString, "utf-8");
                }
            }
        }
        // 打印请求入参
        logger.info("Request Args   : {}", params);
        // 获取返回结果
        Object result = joinPoint.proceed();
        logger.info("Response Args  : {}", JSON.toJSON(result));
        long endTime = System.currentTimeMillis();
        logger.info("Time-Consuming : {}ms", (endTime - startTime));
        logger.info("========================================== Ended ==========================================");
        return result;
    }
}
