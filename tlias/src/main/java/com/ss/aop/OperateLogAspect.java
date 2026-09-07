package com.ss.aop;

import com.ss.utils.CurrentHolder;
import com.ss.pojo.OperateLog;
import com.ss.mapper.OperateLogMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * 操作日志切面: 记录系统中所有增、删、改功能接口的操作日志
 */
@Slf4j
@Aspect
@Component
public class OperateLogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;

    // 拦截所有被 @Log 注解标记的方法
    @Around("@annotation(com.ss.anno.Log)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {

        long begin = System.currentTimeMillis();

        // 执行目标方法 (方法异常时不记录日志, 异常直接向上抛出)
        Object result = joinPoint.proceed();
        saveLog(joinPoint, begin, result != null ? result.toString() : null);

        return result;
    }

    private void saveLog(ProceedingJoinPoint joinPoint, long begin, String returnValue) {
        OperateLog olog = new OperateLog();

        olog.setOperateEmpId(getUserId());   // 操作人ID: 从请求头中的令牌解析
        olog.setOperateTime(LocalDateTime.now());          // 操作时间
        olog.setClassName(joinPoint.getTarget().getClass().getName());  // 目标类的全类名
        olog.setMethodName(joinPoint.getSignature().getName());          // 目标方法名
        olog.setMethodParams(Arrays.toString(joinPoint.getArgs()));      // 方法运行时参数
        olog.setReturnValue(returnValue);                  // 返回值
        olog.setCostTime(System.currentTimeMillis() - begin);  // 方法执行时长

        // 保存日志
        operateLogMapper.insert(olog);
        OperateLogAspect.log.info("操作日志已记录: {}", olog);
    }

    /**
     * 从请求头中的令牌解析操作人ID, 解析失败返回null
     */
    private Integer getUserId() {
        return CurrentHolder.getCurrentId();    // 从当前线程的ThreadLocal中获取用户ID
    }
}