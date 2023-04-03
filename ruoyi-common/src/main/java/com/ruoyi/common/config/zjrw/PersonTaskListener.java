package com.ruoyi.common.config.zjrw;//package com.ruoyi.common.config.zjrw;
//
//import com.ruoyi.czjg.zjrw.service.PersonListenService;
//import org.flowable.engine.delegate.DelegateExecution;
//import org.flowable.engine.delegate.ExecutionListener;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
///**
// * @Author : Chen_ZhiWei
// * @Date : Create file in 2022/6/8 14:54
// * @Version : 1.0
// * @Description : flowable工作流监听器
// **/
//@Component
//public class PersonTaskListener implements ExecutionListener {
//
//    private static final Logger log = LoggerFactory.getLogger(PersonTaskListener.class);
//
//    @Autowired
//    private PersonListenService personService;
//
//    @Override
//    public void notify(DelegateExecution delegateExecution) {
//        String processInstanceId = delegateExecution.getProcessInstanceId();
//        String processDefinitionId = delegateExecution.getProcessDefinitionId();
//        String processName = processDefinitionId.split(":")[0];
//        log.info("监听到的流程code为: " + processName);
//        if (personService == null){
//            personService = ApplicationContextHolder.getBean(PersonListenService.class);
//        }
//        //截取definitionId前面的流程code
//        if (processName.equals("sgdwhtrybs") || processName.equals("jldwhtrybs") || processName.equals("qzdwhtrybs")){
//            personService.finishContract(processInstanceId);
//        } else if(processName.equals("sgdwrybg") || processName.equals("jldwrybg") || processName.equals("qzdwrybg")){
//            personService.finishPersonChange(processInstanceId);
//        } else if (processName.equals("sgdwryqj") || processName.equals("jldwryqj") || processName.equals("qzdwryqj")){
//            personService.finishPersonLeave(processInstanceId);
//        }
//    }
//}
