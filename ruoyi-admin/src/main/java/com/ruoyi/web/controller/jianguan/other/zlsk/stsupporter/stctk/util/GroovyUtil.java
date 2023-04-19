package com.ruoyi.web.controller.jianguan.other.zlsk.stsupporter.stctk.util;

import com.ruoyi.web.controller.jianguan.other.zlsk.stsupporter.stctk.stbase.StSupporterBase;
import javax.script.*;
import java.util.*;

public class GroovyUtil extends StSupporterBase
{
    private static final ScriptEngineManager a;
    private static final ScriptEngine b;

    public static final Object eval(final String s, final Map<String, Object> map) {
        if (s == null || s.isEmpty()) {
            return null;
        }
        try {
            if (map != null) {
                final Bindings bindings = GroovyUtil.b.createBindings();
                for (final Map.Entry<String, Object> entry : map.entrySet()) {
                    bindings.put((String)entry.getKey(), entry.getValue());
                }
                return GroovyUtil.b.eval(s, bindings);
            }
            return GroovyUtil.b.eval(s);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    static {
        b = (a = new ScriptEngineManager()).getEngineByName("groovy");
    }
}
