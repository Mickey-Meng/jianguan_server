package com.ruoyi.web.controller.jianguan.business.mytask;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.ruoyi.common.annotation.MyRequestBody;
import com.ruoyi.common.core.domain.object.MyPageData;
import com.ruoyi.common.core.domain.object.MyPageParam;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.common.core.domain.object.ResponseResult;
import com.ruoyi.common.enums.ErrorCodeEnum;
import com.ruoyi.common.utils.MyPageUtil;
import com.ruoyi.flowable.common.constant.FlowMessageType;
import com.ruoyi.flowable.domain.vo.FlowMessageVo;
import com.ruoyi.flowable.model.FlowMessage;
import com.ruoyi.flowable.service.FlowMessageService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 工作流消息操作控制器类。
 *
 * @author Jerry
 * @date 2021-06-06
 */
@Api(tags = "工作流消息操作接口")
@Slf4j
@RestController
@RequestMapping("${common-flow.urlPrefix}/flowMessage")
public class FlowMessageController {

    @Autowired
    private FlowMessageService flowMessageService;

    /**
     * 获取当前用户的未读消息总数。
     * NOTE：白名单接口。
     *
     * @return 应答结果对象，包含当前用户的未读消息总数。
     */
    @GetMapping("/getMessageCount")
    public ResponseResult<JSONObject> getMessageCount() {
        JSONObject resultData = new JSONObject();
        resultData.put("remindingMessageCount", flowMessageService.countRemindingMessageListByUser());
        resultData.put("copyMessageCount", flowMessageService.countCopyMessageByUser());
        return ResponseResult.success(resultData);
    }

    /**
     * 获取当前用户的催办消息列表。
     * 不仅仅包含，其中包括当前用户所属角色、部门和岗位的候选组催办消息。
     * NOTE：白名单接口。
     *
     * @return 应答结果对象，包含查询结果集。
     */
    @PostMapping("/listRemindingTask")
    public ResponseResult<MyPageData<FlowMessageVo>> listRemindingTask(@MyRequestBody MyPageParam pageParam) {
        if (pageParam != null) {
            PageMethod.startPage(pageParam.getPageNum(), pageParam.getPageSize());
        }
        List<FlowMessage> flowMessageList = flowMessageService.getRemindingMessageListByUser();
        return ResponseResult.success(MyPageUtil.makeResponseData(flowMessageList, FlowMessage.FlowMessageModelMapper.INSTANCE));
    }

    /**
     * 获取当前用户的抄送消息列表。
     * 不仅仅包含，其中包括当前用户所属角色、部门和岗位的候选组抄送消息。
     * NOTE：白名单接口。
     *
     * @param read true表示已读，false表示未读。
     * @return 应答结果对象，包含查询结果集。
     */
    @PostMapping("/listCopyMessage")
    public ResponseBase listCopyMessage(
            @MyRequestBody MyPageParam pageParam, @MyRequestBody Boolean read) {
        if (pageParam != null) {
            PageMethod.startPage(pageParam.getPageNum(), pageParam.getPageSize());
        }
        PageInfo<FlowMessage> flowMessageList = flowMessageService.getCopyMessageListByUser(pageParam, read);
        return ResponseBase.success(flowMessageList);
    }

    /**
     * 读取抄送消息，同时更新当前用户对指定抄送消息的读取状态。
     *
     * @param messageId 消息Id。
     * @return 应答结果对象。
     */
    @PostMapping("/readCopyTask")
    public ResponseResult<Void> readCopyTask(@MyRequestBody Long messageId) {
        String errorMessage;
        // 验证流程任务的合法性。
        FlowMessage flowMessage = flowMessageService.getById(messageId);
        if (flowMessage == null) {
            return ResponseResult.error(ErrorCodeEnum.DATA_NOT_EXIST);
        }
        if (flowMessage.getMessageType() != FlowMessageType.COPY_TYPE) {
            errorMessage = "数据验证失败，当前消息不是抄送类型消息！";
            return ResponseResult.error(ErrorCodeEnum.DATA_VALIDATED_FAILED, errorMessage);
        }
        if (!flowMessageService.isCandidateIdentityOnMessage(messageId)) {
            errorMessage = "数据验证失败，当前用户没有权限访问该消息！";
            return ResponseResult.error(ErrorCodeEnum.DATA_VALIDATED_FAILED, errorMessage);
        }
        flowMessageService.readCopyTask(messageId);
        return ResponseResult.success();
    }
}
