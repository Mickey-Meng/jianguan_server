package com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.dp.enums;

public enum FilePathEnum
{
    EXCEL_PATH("D:/smartFile");

    String path;

    private FilePathEnum(final String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }
}
