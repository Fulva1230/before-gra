package com.gmail.noxdawn;

import lombok.Data;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.util.logging.Logger;

@Aspect
@Data
public class SimpleLogger {
    private final Logger logger;
    
    @Before("execution(public * * (..))")
    private void log() {
        logger.log(logger.getLevel(), "here is a function call");
    }
}
