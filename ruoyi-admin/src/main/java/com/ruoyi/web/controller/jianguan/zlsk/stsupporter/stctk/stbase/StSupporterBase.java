package com.ruoyi.web.controller.jianguan.zlsk.stsupporter.stctk.stbase;

import com.ruoyi.web.controller.jianguan.zlsk.stsupporter.stctk.stad.*;

public class StSupporterBase
{
    private static Stat a;

    public StSupporterBase() {
        if (StSupporterBase.a == null) {
            try {
                StSupporterBase.a = new Stat(this.getClass().getName().substring(0, 20));
            }
            catch (Exception ex) {
                StSupporterBase.a = null;
            }
        }
        StSupporterBase.a.chk(null).substring(20);
    }

    static {
        StSupporterBase.a = null;
    }
}
