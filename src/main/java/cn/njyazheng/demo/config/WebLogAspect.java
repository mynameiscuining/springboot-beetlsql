package cn.njyazheng.demo.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Enumeration;

@Component
@Aspect
public class WebLogAspect {

    private static Logger logger = LogManager.getLogger(WebLogAspect.class);

    @Pointcut("execution( * cn.njyazheng.demo.controller.*.*(..))")
    public void webLog() {
    }
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logger.info("---------------------------------请求开始-----------------------------------------------");
        // 记录下请求内容
        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("IP : " + request.getRemoteAddr());
        logger.info("---------------------------------请求参数-----------------------------------------------");

        logger.info("REQUEST_PARAMS:");
        Enumeration<String> enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String name = (String) enu.nextElement();
            logger.info(name+":"+request.getParameter(name));
        }
        logger.info("---------------------------------请求JSON-----------------------------------------------");
        BufferedReader streamReader = new BufferedReader( new InputStreamReader(request.getInputStream(), "UTF-8"));
        StringBuilder responseStrBuilder = new StringBuilder();
        String inputStr;
        while ((inputStr = streamReader.readLine()) != null) {
            responseStrBuilder.append(inputStr);
        }
        logger.info("REQUEST_JSON:"+responseStrBuilder.toString());
        logger.info("-------------------------------请求结束-------------------------------------------------");
    }
    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        logger.info("-------------------------------返回开始-------------------------------------------------");
        // 处理完请求，返回内容
        logger.info("RESPONSE : " + ret);
        logger.info("---------------------------------返回结束-----------------------------------------------");
    }
}
