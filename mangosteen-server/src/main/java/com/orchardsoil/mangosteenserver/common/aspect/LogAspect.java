package com.orchardsoil.mangosteenserver.common.aspect;

import com.orchardsoil.mangosteenserver.common.authentication.JWTUtil;
import com.orchardsoil.mangosteenserver.common.properties.MangosteenProperties;
import com.orchardsoil.mangosteenserver.common.utils.HttpContextUtil;
import com.orchardsoil.mangosteenserver.core.model.Log;
import com.orchardsoil.mangosteenserver.core.service.LogService;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Aspect
@Component
public class LogAspect {

    private final MangosteenProperties mangosteenProperties;

    private final LogService logService;

    @Autowired
    public LogAspect(LogService logService, MangosteenProperties mangosteenProperties) {
        this.logService = logService;
        this.mangosteenProperties = mangosteenProperties;
    }

    @Pointcut("@annotation(com.orchardsoil.mangosteenserver.common.annotation.SysLog)")
    public void pointcut() {
        // do nothing
    }

    /**
     * (环绕通知)： 在方法执行前和执行后都会执行
     *
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object result = null;
        long beginTime = System.currentTimeMillis();
        // 执行方法
        result = point.proceed();
        // 获取 request
        HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
        // 设置 IP 地址
        // String ip = IPUtil.getIpAddr(request);
        // 执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        if (mangosteenProperties.isOpenAopLog()) {
            // 保存日志
            String token = (String) SecurityUtils.getSubject().getPrincipal();
            String username = "";
            if (StringUtils.isNotBlank(token)) {
                username = JWTUtil.getUsername(token);
            }

            Log log = new Log();
            log.setUsername(username);
            // log.setIp(ip);
            log.setTime(time + "");
            logService.saveLog(point, log);
        }
        return result;
    }
}
