package com.orchardsoil.mangosteenserver.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.orchardsoil.mangosteenserver.core.model.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.scheduling.annotation.Async;

public interface LogService extends IService<Log> {
    @Async
    void saveLog(ProceedingJoinPoint point, Log log) throws JsonProcessingException;
}
