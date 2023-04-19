package com.ruoyi.web.controller.jianguan.other.zlsk.stframebase.core;

import com.ruoyi.web.controller.jianguan.other.zlsk.stsupporter.stctk.model.STData;
import com.ruoyi.web.controller.jianguan.other.zlsk.stsupporter.stctk.model.STStatus;
import org.springframework.core.annotation.*;
import org.springframework.stereotype.*;
import org.slf4j.*;
import org.aspectj.lang.*;
import org.springframework.util.*;
import org.springframework.dao.*;
import org.springframework.web.context.request.*;
import javax.servlet.http.*;
import java.util.*;
import org.aspectj.lang.annotation.*;

@Order(9)
@Aspect
@Component
public class ServiceMonitor
{
    private Logger logger;

    public ServiceMonitor() {
        this.logger = LoggerFactory.getLogger((Class)ServiceMonitor.class);
    }

    @Around("com.ruoyi.web.controller.jianguan.other.zlsk.stsupporter.stctk.stbase.StPointCuts.zlskController()")
    public Object logServiceAccess(final ProceedingJoinPoint pjp) {
        final long start = System.currentTimeMillis();
        final String className = pjp.getTarget().getClass().getName();
        final String fullMethodName = className + "." + pjp.getSignature().getName();
        boolean needLog = false;
        if (!className.contains("com.sun.proxy.$Proxy") && !className.contains("$$EnhancerBySpringCGLIB$$")) {
            needLog = true;
            final RequestAttributes ra = RequestContextHolder.getRequestAttributes();
            final ServletRequestAttributes sra = (ServletRequestAttributes)ra;
            final HttpServletRequest request = sra.getRequest();
            final StringBuffer sb = request.getRequestURL();
            if (request.getQueryString() != null) {
                sb.append("?").append(request.getQueryString());
            }
            this.logger.info(sb.toString());
            if (pjp.getArgs() != null && pjp.getArgs().length > 0) {
                this.logger.info(fullMethodName + "\u5c06\u88ab\u8c03\u7528,\u4e0b\u9762\u662f\u53c2\u6570:");
                for (final Object arg : pjp.getArgs()) {
                    if (arg != null) {
                        this.logger.info(arg.toString());
                        final Map<String, ArrayList<String>> validateResult = null;
                        if (validateResult != null) {
                            final Iterator<Map.Entry<String, ArrayList<String>>> iterator = validateResult.entrySet().iterator();
                            if (iterator.hasNext()) {
                                final Map.Entry<String, ArrayList<String>> entry = iterator.next();
                                this.logger.error(fullMethodName + "Monitor\u53c2\u6570:" + arg);
                                this.logger.error(entry.getKey() + "\u6821\u9a8c\u5931\u8d25,\u539f\u56e0:" + entry.getValue());
                                final long end = System.currentTimeMillis();
                                final long elapsedMilliseconds = end - start;
                                this.logger.info(fullMethodName + "\u6267\u884c\u8017\u65f6:" + elapsedMilliseconds + " \u6beb\u79d2");
                                final String say = "\u53c2\u6570\u6821\u9a8c\u5931\u8d25," + entry.getKey() + ":" + entry.getValue();
                                return new STStatus(-1, say);
                            }
                        }
                    }
                }
            }
            else {
                this.logger.info(fullMethodName + "\u5c06\u88ab\u8c03\u7528");
            }
        }
        Object result = null;
        try {
            result = pjp.proceed();
        }
        catch (Throwable e) {
            if (result != null) {
                if (result instanceof STStatus) {
                    final STStatus errorResult = (STStatus)result;
                    if (StringUtils.isEmpty((Object)errorResult.getMeow())) {
                        errorResult.setMeow(-1);
                    }
                    if (StringUtils.isEmpty((Object)errorResult.getMsg())) {
                        errorResult.setMsg(e.getLocalizedMessage());
                    }
                    result = errorResult;
                }
                else if (result instanceof STData) {
                    final STData errorResult2 = (STData)result;
                    if (StringUtils.isEmpty((Object)errorResult2.getMeow())) {
                        errorResult2.setMeow(-1);
                    }
                    if (StringUtils.isEmpty((Object)errorResult2.getMsg())) {
                        errorResult2.setMsg(e.getLocalizedMessage());
                    }
                    result = errorResult2;
                }
            }
            else {
                result = new STData(-1, e.getMessage(), (Object)null);
            }
            this.logger.error(fullMethodName + "\u6267\u884c\u51fa\u9519,\u8be6\u60c5:", e);
            if (e instanceof DataAccessException) {
                ((STStatus)result).setMeow(-1000);
            }
        }
        final long end2 = System.currentTimeMillis();
        final long elapsedMilliseconds2 = end2 - start;
        if (needLog) {
            this.logger.info(fullMethodName + "\u6267\u884c\u8017\u65f6:" + elapsedMilliseconds2 + " \u6beb\u79d2");
        }
        return result;
    }
}
