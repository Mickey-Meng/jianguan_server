package com.ruoyi.web.controller.jianguan.other.zlsk.stframebase.core;

import com.ruoyi.web.controller.jianguan.other.zlsk.stsupporter.stctk.model.STData;
import com.ruoyi.web.controller.jianguan.other.zlsk.stsupporter.stctk.stad.Stat;
import org.springframework.core.annotation.*;
import org.springframework.stereotype.*;
import org.slf4j.*;
import org.aspectj.lang.*;
import org.aspectj.lang.annotation.*;

@Order(1)
@Aspect
@Component
public class StAuthMonitor
{
    private static Stat stat;
    private Logger logger;

    public StAuthMonitor() {
        this.logger = LoggerFactory.getLogger((Class)StAuthMonitor.class);
        if (StAuthMonitor.stat == null) {
            try {
                StAuthMonitor.stat = new Stat(this.getClass().getName().substring(0, 11) + "supporter");
            }
            catch (Exception e) {
                StAuthMonitor.stat = null;
            }
        }
    }

    @Around("com.ruoyi.web.controller.jianguan.other.zlsk.stsupporter.stctk.stbase.StPointCuts.sta()")
    public Object checkAuth(final ProceedingJoinPoint pjp) {
        final long st = System.currentTimeMillis();
        final String cn = pjp.getTarget().getClass().getName();
        final String ar = this.checkAu(cn);
        final long et = System.currentTimeMillis();
        this.logger.info("check au,time fly: " + (et - st) + "ms");
        if (ar == null) {
            this.logger.info("st: \u6388\u6743\u5931\u8d25");
            final STData<String> std = (STData<String>)new STData(-2, (String)null, (Object)null);
            return std;
        }
        Object r = null;
        try {
            r = pjp.proceed();
        }
        catch (Throwable t) {
            r = new STData(-1, t.getMessage(), (Object)null);
        }
        return r;
    }

    private String checkAu(final String sk) {
        return StAuthMonitor.stat.chk(sk);
    }

    static {
        StAuthMonitor.stat = null;
    }
}
