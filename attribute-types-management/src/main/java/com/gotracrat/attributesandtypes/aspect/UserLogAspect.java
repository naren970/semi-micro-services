package com.gotracrat.attributesandtypes.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.gotracrat.attributesandtypes.entity.UserLog;
import com.gotracrat.attributesandtypes.repository.UserLogRepository;

import javax.annotation.Resource;

@Aspect
@Component
public class UserLogAspect {

    @Resource
    private UserLogRepository userLogRepository;

    @Before(value = "execution(* com.gotracrat.attributesandtypes.service.*.*(..))")
    public void beforeAdvice(JoinPoint joinPoint) {
        System.out.println("Before method:" + joinPoint.getSignature());
        System.out.println("Successfully Executing Attribute Calls - ");
    }
    
    @After(value = "execution(* com.gotracrat.attributesandtypes.service.*.*(..))")
    public void afterAdvice(JoinPoint joinPoint) {
        UserLog userLogAction=null;
        Object[] argsArr = joinPoint.getArgs();
        for (Object signatureArg: argsArr) {
            String className = signatureArg.getClass().getName();
            if(className.equalsIgnoreCase("com.gotracrat.attributesandtypes.entity.UserLog")){
                System.out.println("UserLog class inside if condition is: " + className);
                UserLog userLogg = (UserLog)signatureArg;
                userLogg.setUserLogId(0);
                userLogg.setLogXml(null);
                if(!userLogg.isCanNotInsert())
                userLogAction = userLogRepository.save(userLogg);
            }
        }
      /* String methodName = joinPoint.getSignature().getName();
       String className = joinPoint.getTarget().getClass().getName();
       System.out.println("After method moduleAndMethod:" + methodName);
       System.out.println("After method className:" + className);
       System.out.println("Successfully created UserLog with module - " + userLogAction.getUserAction() + " and user - " + userLogAction.getUserName());*/
    }
}
