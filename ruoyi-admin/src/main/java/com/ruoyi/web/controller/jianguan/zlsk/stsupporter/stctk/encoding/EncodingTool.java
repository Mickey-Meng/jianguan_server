package com.ruoyi.web.controller.jianguan.zlsk.stsupporter.stctk.encoding;

import com.ruoyi.web.controller.jianguan.zlsk.stsupporter.stctk.stbase.*;

public class EncodingTool extends StSupporterBase
{
    public static String recoding(final String s, final String s2, final String s3) {
        try {
            return new String(s.getBytes(s2), s3);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
