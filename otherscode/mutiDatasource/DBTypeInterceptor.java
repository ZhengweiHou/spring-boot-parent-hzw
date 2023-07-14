package cn.sccfc.zacps.ops.jdbc.datasource;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(0)
@Aspect
@Component
public class DBTypeInterceptor {

    @Pointcut("execution(* cn.sccfc.zacps.ops.dao.mapper..*(..))")
    public void dao1() {
    }
    @Pointcut("execution(* cn.sccfc.zacps.ops.opsauth.dao..*(..))")
    public void dao2() {
    }


    @Around("dao1() || dao2()")
    public Object dbFilter(ProceedingJoinPoint pj) throws Throwable {
        // 获取切入的 Method
        MethodSignature joinPointObject = (MethodSignature) pj.getSignature();
        Method method = joinPointObject.getMethod();
        DbName db = null;

        boolean flag = method.isAnnotationPresent(DbName.class);
        if (flag) {
            db = method.getAnnotation(DbName.class);
        } else {
            // 如果方法上没有注解，则搜索类上是否有注解
            db = AnnotationUtils.findAnnotation(joinPointObject.getMethod().getDeclaringClass(), DbName.class);
        }

        if (db == null) {
            //fallback to default datasource
            return pj.proceed();
        } else {
            DynamicDataSource.setDataSourceKey(db.value().getDbKey());
            try {
                return pj.proceed();
            } finally {
                DynamicDataSource.resetDataSourceKey();
            }
        }

    }
}
