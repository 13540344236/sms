package com.cs.sms.aspect;

import com.cs.sms.annotation.RequiredLog;
import com.cs.sms.pojo.entity.Log;
import com.cs.sms.service.LogService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @Aspect 注解描述的类型为一个切面类型,切面中可以定义多个切入点表达式和通知方法
 * 1)切入点表达式(粗粒度/细粒度-可以精确到具体方法)
 * 1.1)@Pointcut("@annotation(注解类全名)")
 * 1.2)....
 * 2)通知方法
 * 1.1)@Around(....)
 */
@Aspect
@Component
public class LogAspect {
    //创建一个日志对象(请问这里用到了什么设计模式?-简单工厂,门面模式)
    private static final Logger log= LoggerFactory.getLogger(LogAspect.class);

    /**
     * 定义切入点表达式(基于@Pointcut注解定义切入点表达式)
     * 1)@annotation用于定义注解方式的切入点表达式
     * 2).....
     */
    //@Pointcut("bean(productController)") 粗粒度的切入点表达式作用域bean对象中的所有方法
    @Pointcut("@annotation(com.cs.sms.annotation.RequiredLog)")
    public void doLog(){}//doLog方法内部不用写具体内容,只是用于承载注解

    /**
     * 定义功能增强方法(很多人翻译为通知方法),这样的方法可以使用一些注解进行描述,例如:
     * @Around/@Before/@After/@AfterThrowing/@AfterReturning
     * @Around注解描述的通知方法是所有通知方法中优先级最高的,其内部可以通过连接点对象调用目标方法
     * @param joinPoint 为连接点对象,基于此对象可以访问目标方法信息,同时也可以调用目标方法.
     *                  ProceedingJoinPoint类型的参数只能应用在环绕通知中.
     * @return
     * @throws Throwable
     */
    @Around("doLog()")
    public Object doLogAround(ProceedingJoinPoint joinPoint)throws Throwable{
        //1.定义初始状态信息
        int status=1;
        String error="";
        long time=0;
        long start=System.currentTimeMillis();
        //2.调用目标执行链(可能有本类的其它通知方法,可能有其它切面的通知方法,可能会有目标方法)
        try {
            Object result = joinPoint.proceed();
            //3.定义结果状态
            time=System.currentTimeMillis()-start;
            return result;
        }catch(Throwable e){
            status=0;
            error=e.getMessage();
            time=System.currentTimeMillis()-start;
            throw e;
        }finally{
          //4.获取用户行为并记录到数据库
            log.info("任务执行时长 {}",time);
            saveUserLog(joinPoint,status,error,time);
        }
    }
    private void saveUserLog(ProceedingJoinPoint joinPoint,int status,String error,long time) throws NoSuchMethodException, JsonProcessingException {

        //1.获取具体行为日志
        String username="";
        String operation="";
        String classMethodName="";
        String params="";
        //1)获取用户名
        //2)获取ip地址(首先获取请求对象)
        //2.1)获取当前线程的请求对象
        ServletRequestAttributes requestAttribute=
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=requestAttribute.getRequest();
        //2.1)获取ip地址
        String ip=request.getRemoteAddr();
        //3)获取operation的值
        //3.1)获取目标类的字节码对象
        Class<?> targetClass = joinPoint.getTarget().getClass();
        //3.2)获取目标方法对象
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method targetMethod =
                targetClass.getDeclaredMethod(signature.getName(),
                        signature.getParameterTypes());
        //3.3)获取方法上的RequiredLog注解对象
        RequiredLog requiredLog = targetMethod.getAnnotation(RequiredLog.class);
        //3.4)获取RequiredLog注解中operation的值
        operation=requiredLog.operation();
        //4)获取方法名(类名+方法名)
        classMethodName=targetClass.getName()+"."+targetMethod.getName();
        //5)获取参数(建议将参数转换为JSON字符串)
        params=new ObjectMapper().writeValueAsString(joinPoint.getArgs());

        //2.封装日志
        Log userLog=new Log();
        userLog.setUsername(username);
        userLog.setIp(ip);
        userLog.setCreatedTime(new Date());
        userLog.setOperation(operation);
        userLog.setMethod(classMethodName);
        userLog.setParams(params);
        userLog.setTime(time);
        userLog.setStatus(status);
        userLog.setError(error);
        //3.记录日志
        logService.insert(userLog);
        log.debug("新增日志");
    }
    @Autowired
    private LogService logService;
}
