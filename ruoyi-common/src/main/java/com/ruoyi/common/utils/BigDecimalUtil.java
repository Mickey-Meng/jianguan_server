package com.ruoyi.common.utils;


import java.math.BigDecimal;

/**
 * BigDecimal运算工具类
 * 除法保留精度方式都采用的ROUND_HALF_EVEN(银行家舍入法)
 */
public class BigDecimalUtil {

    /**
     * 初始化 0
     *
     * @return
     */
    public static BigDecimal init_0() {
        return BigDecimal.ZERO;
    }

    /**
     * double/float转换为BigDecimal
     *
     * @param val
     * @return
     */
    public static BigDecimal init_val(Object val) {
        if (val == null || "".equals(val.toString().trim())) {
            return BigDecimal.ZERO;
        }
        if (val instanceof Double) {
            return new BigDecimal((Double) val);
        } else if (val instanceof Float) {
            return new BigDecimal((Float) val);
        } else if (val instanceof Integer) {
            return new BigDecimal((Integer) val);
        } else if (val instanceof BigDecimal) {
            return (BigDecimal) val;
        }
        return BigDecimal.ZERO;
    }

    /**
     * BigDecimal 加法运算
     *
     * @param val1
     * @param val2
     * @return
     */
    public static BigDecimal add(BigDecimal val1, BigDecimal val2) {
        return nvl(val1).add(nvl(val2));
    }

    /**
     * double类型相加
     *
     * @param val1
     * @param val2
     * @return
     */
    public static BigDecimal add(double val1, double val2) {
        BigDecimal b1 = new BigDecimal(val1);
        BigDecimal b2 = new BigDecimal(val2);
        return b1.add(b2);
    }


    /**
     * 减法
     *
     * @param minuend     被减数
     * @param subtraction 减数
     * @return
     */
    public static BigDecimal sub(double minuend, double subtraction) {
        BigDecimal b1 = new BigDecimal(minuend);
        BigDecimal b2 = new BigDecimal(subtraction);
        return b1.subtract(b2);
    }

    /**
     * 减法
     *
     * @param minuend     被减数
     * @param subtraction 减数
     * @return
     */
    public static BigDecimal sub(BigDecimal minuend, BigDecimal subtraction) {
        return nvl(minuend).subtract(nvl(subtraction));
    }

    /**
     * 乘法
     *
     * @param val1
     * @param val2
     * @return
     */
    public static BigDecimal mul(double val1, double val2) {
        BigDecimal b1 = new BigDecimal(val1);
        BigDecimal b2 = new BigDecimal(val2);
        return b1.multiply(b2);
    }

    /**
     * 乘法
     *
     * @param val1
     * @param val2
     * @return
     */
    public static BigDecimal mul(BigDecimal val1, BigDecimal val2) {
        return nvl(val1).multiply(nvl(val2));
    }

    /**
     * 乘法
     *
     * @param val1
     * @param val2
     * @return
     */
    public static BigDecimal mul(BigDecimal val1, int val2) {
        return nvl(val1).multiply(nvl(init_val(val2)));
    }

    /**
     * 除法
     *
     * @param div1 被除数
     * @param div2 除数
     * @return
     */
    public static BigDecimal div(double div1, double div2) {
        BigDecimal b1 = new BigDecimal(div1);
        BigDecimal b2 = new BigDecimal(div2);
        return div(b1, b2);
    }

    /**
     * 除法
     *
     * @param div1 被除数
     * @param div2 除数
     * @return
     */
    public static BigDecimal div(BigDecimal div1, BigDecimal div2) {
        if (isET_ZERO(div1) || isET_ZERO(div2)) {
            return BigDecimal.ZERO;
        }
        return nvl(div1).divide(nvl(div2), 16, BigDecimal.ROUND_HALF_EVEN);
    }

    /**
     * 除法
     *
     * @param div1 被除数
     * @param div2 除数
     * @return
     */
    public static BigDecimal div(BigDecimal div1, Integer div2) {
        if (isET_ZERO(div1) || div2 == 0) {
            return BigDecimal.ZERO;
        }
        return nvl(div1).divide(nvl(init_val(div2)), 16, BigDecimal.ROUND_HALF_EVEN);
    }

    /**
     * 除法
     *
     * @param div1     被除数
     * @param div2     除数
     * @param accuracy 小数精度
     * @return
     */
    public static BigDecimal div(BigDecimal div1, BigDecimal div2, int accuracy) {
        if (isET_ZERO(div1) || isET_ZERO(div2)) {
            return BigDecimal.ZERO;
        }
        return nvl(div1).divide(nvl(div2), accuracy, BigDecimal.ROUND_HALF_EVEN);
    }

    /**
     * 计算数据为null检查
     *
     * @param v1
     * @return
     */
    public static BigDecimal nvl(BigDecimal v1) {
        if (v1 == null) {
            return BigDecimal.ZERO;
        }
        return v1;
    }

    /**
     * 比较大小(值1大于值2)
     *
     * @param val1 值1
     * @param val2 值2
     * @return
     */
    public static boolean isGT(BigDecimal val1, BigDecimal val2) {
        return nvl(val1).compareTo(nvl(val2)) == 1;
    }

    /**
     * 比较大小(值1大于值2)
     *
     * @param val1 值1
     * @param val2 值2
     * @return
     */
    public static boolean isGT(BigDecimal val1, double val2) {
        return nvl(val1).compareTo(nvl(new BigDecimal(val2))) == 1;
    }

    /**
     * 比较大小(值1小于值2)
     *
     * @param val1 值1
     * @param val2 值2
     * @return
     */
    public static boolean isLT(BigDecimal val1, BigDecimal val2) {
        return nvl(val1).compareTo(nvl(val2)) == -1;
    }

    /**
     * 比较大小(值1等于值2)
     *
     * @param val1 值1
     * @param val2 值2
     * @return
     */
    public static boolean isET(BigDecimal val1, BigDecimal val2) {
        return nvl(val1).compareTo(nvl(val2)) == 0;
    }

    /**
     * 比较大小(值1大于等于值2)
     *
     * @param val1
     * @param val2
     * @return
     */
    public static boolean isGTAndET(BigDecimal val1, BigDecimal val2) {
        return nvl(val1).compareTo(nvl(val2)) == 1 || nvl(val1).compareTo(nvl(val2)) == 0;
    }

    /**
     * 比较大小(值1小于等于值2)
     *
     * @param val1
     * @param val2
     * @return
     */
    public static boolean isLTAndET(BigDecimal val1, BigDecimal val2) {
        return nvl(val1).compareTo(nvl(val2)) == -1 || nvl(val1).compareTo(nvl(val2)) == 0;
    }

    /**
     * 判断值是否大于0
     *
     * @param val1 值1
     * @return 大于0返回true
     */
    public static boolean isGT_ZERO(BigDecimal val1) {
        return isGT(val1, BigDecimal.ZERO);
    }

    /**
     * 判断值是否等于0
     *
     * @param val1
     * @return
     */
    public static boolean isET_ZERO(BigDecimal val1) {
        return isET(val1, BigDecimal.ZERO);
    }

    /**
     * 小于0
     *
     * @param val1 值1
     * @return
     */
    public static boolean isLT_ZERO(BigDecimal val1) {
        return isLT(val1, BigDecimal.ZERO);
    }

    /**
     * 大于等于0
     *
     * @param val1 值1
     * @return
     */
    public static boolean isGTAndET_ZERO(BigDecimal val1) {
        return isGTAndET(val1, BigDecimal.ZERO);
    }

    /**
     * 小于等于0
     *
     * @param val1 值1
     * @return
     */
    public static boolean isLTAndET_ZERO(BigDecimal val1) {
        return isLTAndET(val1, BigDecimal.ZERO);
    }

    /**
     * 取最小的值
     *
     * @return
     */
    public static BigDecimal min(BigDecimal a, BigDecimal b) {
        if (isLTAndET(a, b)) {
            return a;
        } else {
            return b;
        }
    }

    /**
     * @param decimal      var
     * @param scale        精度
     * @param roundingMode 模式
     * @return BigDecimal
     */
    public static BigDecimal precision(BigDecimal decimal, int scale, int roundingMode) {

        return decimal.setScale(scale, roundingMode);
    }

}
