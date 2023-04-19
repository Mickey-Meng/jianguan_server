package com.ruoyi.web.controller.jianguan.other.zlsk.stsupporter.stctk.stbase;

public abstract class StRestServiceBase extends StServiceBase
{
    @Override
    public String getJarBaseName() {
        final String simpleName = this.getClass().getSimpleName();
        return simpleName.substring(2, simpleName.length() - 7).toLowerCase() + "restservice";
    }
}
