package aop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/*
*
* 중요한 부분을 AOP를 활용해서 LOG를 남길 수 있다.
*
* */


@Aspect // Advice + PointCut = Aspect 어느 부분에 적용시킬 건지
@Component //스프링에서 관리
public class ParameterAop {

    @Pointcut("execution(* aop.controller..*.*(..))")
    private  void cut(){}

    //메서드가 실행되기 전에 실행되는 아규먼트 확인
    @Before("cut()")
    public void before(JoinPoint joinPoint){
        //어떤 메서드를 실행 시켰는지 파라미터를 찍어주는 기능
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        System.out.println(method.getName());

        Object[] args = joinPoint.getArgs();
        for(Object obj : args){
            System.out.println("type : "+ obj.getClass().getSimpleName());
            System.out.println("type : "+ obj);
        }
    }

    //returning이라는 속성이 하위에 있는 메소드가 실행되기 전에 실행되어 정상 실행 후 return이 이뤄지면 object 값을 볼 수 있다.
    @AfterReturning(value = "cut()", returning = "returnObj")
    public void afterReturn(JoinPoint joinPoint, Object returnObj){
        System.out.println("return Obj");
        System.out.println(returnObj);

    }
}
