package com.ruoyi.ql.domain.report;

import com.ruoyi.common.core.domain.R;
import lombok.Data;

@Data
public class ReportResponse<T> {

    private T data;

    public static <T> ReportResponse<T> ok(T data) {
        return restResult(data);
    }


    private static <T> ReportResponse<T> restResult(T data) {
        ReportResponse<T> reportResponse = new ReportResponse<>();
        reportResponse.setData(data);
        return reportResponse;
    }
}
