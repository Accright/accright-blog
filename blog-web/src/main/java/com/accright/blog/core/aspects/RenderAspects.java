package com.accright.blog.core.aspects;

import com.accright.blog.business.entity.Config;
import com.accright.blog.business.service.SysConfigService;
import freemarker.template.TemplateModelException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @version 1.0
 * @date 2018/5/17 17:06
 * @since 1.0
 */
@Slf4j
@Component
@Aspect
@Order(1)
public class RenderAspects {

    private static volatile long configLastUpdateTime = 0L;
    @Autowired
    protected freemarker.template.Configuration configuration;
    @Autowired
    private SysConfigService configService;

    @Pointcut("execution(* com.accright.blog.controller.RenderController.*(..))")
    public void pointcut() {
        // 切面切入点
    }

    @After("pointcut()")
    public void after(JoinPoint joinPoint) {
        Config config = configService.get();
        if (null == config) {
            log.error("config为空");
            return;
        }
        Long updateTime = config.getUpdateTime().getTime();
        if (updateTime == configLastUpdateTime) {
            log.info("config表未更新");
            return;
        }
        log.info("config表已更新，重新加载config到freemarker tag");
        configLastUpdateTime = updateTime;
        try {
            configuration.setSharedVariable("config", config);
        } catch (TemplateModelException e) {
            e.printStackTrace();
        }
    }
}
