package com.ruoyi.flowable.factory;

import com.ruoyi.flowable.utils.BaseBusinessDataExtHelper;
import com.ruoyi.flowable.utils.BaseFlowIdentityExtHelper;
import org.springframework.stereotype.Component;

/**
 * 工作流自定义扩展工厂类。
 *
 * @author Jerry
 * @date 2021-06-06
 */
@Component
public class FlowCustomExtFactory {

    private BaseFlowIdentityExtHelper flowIdentityExtHelper;

    private BaseBusinessDataExtHelper businessDataExtHelper = new BaseBusinessDataExtHelper();

    /**
     * 获取业务模块自行实现的用户身份相关的扩展帮助实现类。
     *
     * @return 业务模块自行实现的用户身份相关的扩展帮助实现类。
     */
    public BaseFlowIdentityExtHelper getFlowIdentityExtHelper() {
        return flowIdentityExtHelper;
    }

    /**
     * 注册业务模块自行实现的用户身份扩展帮助实现类。
     *
     * @param helper 业务模块自行实现的用户身份扩展帮助实现类。
     */
    public void registerFlowIdentityExtHelper(BaseFlowIdentityExtHelper helper) {
        this.flowIdentityExtHelper = helper;
    }

    /**
     * 获取有关业务数据的扩展帮助实现类。
     *
     * @return 有关业务数据的扩展帮助实现类。
     */
    public BaseBusinessDataExtHelper getBusinessDataExtHelper() {
        return businessDataExtHelper;
    }
}
