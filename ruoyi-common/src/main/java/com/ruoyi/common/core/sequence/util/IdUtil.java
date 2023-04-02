package com.ruoyi.common.core.sequence.util;

import com.ruoyi.common.core.sequence.wrapper.IdGeneratorWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 获取id
 *
 * @author qiaoxulin 20220325
 */
@Component
public class IdUtil {


    private static IdGeneratorWrapper idGeneratorWrapper;


    @Autowired
    public void setIdGeneratorWrapper(IdGeneratorWrapper idGeneratorWrapper) {
        IdUtil.idGeneratorWrapper = idGeneratorWrapper;
    }


    /**
     * 由于底层实现为synchronized方法，因此计算过程串行化，且线程安全。
     *
     * @return 计算后的全局唯一Id。
     */
    public static long nextLongId() {
        return idGeneratorWrapper.nextLongId();
    }

    /**
     * 由于底层实现为synchronized方法，因此计算过程串行化，且线程安全。
     *
     * @return 计算后的全局唯一Id。
     */
    public static String nextStringId() {
        return idGeneratorWrapper.nextStringId();
    }
}
