package com.ruoyi.czjg.zjrw.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mongodb.client.gridfs.GridFSFindIterable;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.ruoyi.common.config.zjrw.ZhuJiOfferConfig;
import com.ruoyi.common.core.domain.model.ZjPersonLeave;
import com.ruoyi.czjg.zjrw.dao.*;
import com.ruoyi.czjg.zjrw.domain.dto.PostData;
import com.ruoyi.czjg.zjrw.domain.dto.TokenData;
import com.ruoyi.common.core.domain.entity.ZjYcdata;
import com.ruoyi.czjg.zjrw.domain.entity.ZjYcexceeddata;
import com.ruoyi.czjg.zjrw.domain.entity.ZjYcperday;
import com.ruoyi.czjg.zjrw.domain.entity.weather.WeatherNow;
import com.ruoyi.czjg.zjrw.domain.entity.weather.WeatherReturn;
import com.ruoyi.common.utils.zjbim.zjrw.HttpsUtils;
import com.ruoyi.common.utils.zjbim.zjrw.httputils.HttpHeader;
import com.ruoyi.common.utils.zjbim.zjrw.httputils.HttpParamers;
import com.ruoyi.common.utils.zjbim.zjrw.httputils.HttpService;
import com.ruoyi.common.utils.zjbim.zjrw.httputils.HttpUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.bson.BsonObjectId;
import org.bson.BsonValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import static com.ruoyi.common.constant.Constant.ENVIREMENTDATA;
import static com.ruoyi.common.constant.Constant.zjYcdataMap;

/**
 * @author ：lin_zhixiong
 * @date ：Created in 2021/7/22 11:57 上午
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description:
 **/
@Component
public class Job {

    Logger log = LoggerFactory.getLogger(Job.class);

    @Autowired
    private HttpsUtils httpsUtils;
    @Autowired
    private ZhuJiOfferConfig zhuJiOfferConfig;
    @Autowired
    private ZjYcdataDAO zjYcdataDAO;
    @Autowired
    private ZjYcperdayDAO zjYcperdayDAO;
    @Autowired
    private ZjYcexceeddataDAO zjYcexceeddataDAO;
    @Autowired
    private RecodeDAO recodeDAO;
    @Autowired
    private SsFGroupsDAO projectsDAO;
    @Autowired
    private ZjFileDAO fileDAO;
    @Autowired
    private ZjNewsDAO newsDAO;
    @Autowired
    private ZjPersonChangeDAO personChangeDAO;
    @Autowired
    private PersonDAO personDAO;
    @Autowired
    private ZjQualityEventDAO qualityEventDAO;
    @Autowired
    private ZjSafeEventDAO safeEventDAO;
    @Autowired
    private ZjPersonLeaveDAO leaveDAO;
    @Autowired
    private FileMapper fileMapper;

    @Value("${road.clock.host}")
    private String host;
    @Value("${road.clock.url}")
    private String url;

    //清除mongoDB多余文件
    //每月1号凌晨0点执行一次
//    @Scheduled(cron = "0 0 0 1 * ?")
//    @Scheduled(cron = "0 */1 * * * ?")
    public void ClearRedundantFiles() {
        List<String> fileIds1 = Lists.newArrayList();
        List<String> fileIds2 = Lists.newArrayList();
        //查询所有recode表的文件id
        List<String> recodeRemark = recodeDAO.getNotNullRemark();
        if (recodeRemark.size() > 0){
            fileIds1.addAll(recodeRemark);
        }

        List<String> recodeAccreUrl = recodeDAO.getAccrecodeurl();
        if (recodeAccreUrl.size() > 0){
            fileIds1.addAll(recodeAccreUrl);
        }

        List<String> getTesturl = recodeDAO.getTesturl();
        if (getTesturl.size() > 0){
            fileIds1.addAll(getTesturl);
        }

        List<String> getStandbyrecode = recodeDAO.getStandbyrecode();
        if (getStandbyrecode.size() > 0){
            fileIds1.addAll(getStandbyrecode);
        }
        //查询所有ss_f_projects表的文件id
        List<String> projectPic = projectsDAO.getProjectPic();
        if (projectPic.size() > 0){
            fileIds1.addAll(projectPic);
        }
        //查询所有zj_file表的文件id
        List<String> zjFiles = fileDAO.getFileurl();
        if (zjFiles.size() > 0){
            fileIds1.addAll(zjFiles);
        }
        //查询所有zj_news表的文件id
        List<String> zjNews = newsDAO.getPic();
        if (zjNews.size() > 0){
            fileIds1.addAll(zjNews);
        }
        //查询所有zj_person_change_file表的文件id
        List<String> personChangeFile = personChangeDAO.getFileIds();
        if (personChangeFile.size() > 0){
            fileIds1.addAll(personChangeFile);
        }
        //查询所有zj_person_people_sub表的peoplePic的id
        List<String> peoplePics = personDAO.getPeoPlePics();
        if (peoplePics.size() > 0){
            fileIds1.addAll(peoplePics);
        }
        //查询所有zj_quality_event表的文件id
        List<String> qualityUpload = qualityEventDAO.getNotNullUploadurl();
        if (qualityUpload.size() > 0){
            fileIds1.addAll(qualityUpload);
        }
        List<String> qualityModifyurl = qualityEventDAO.getNotNullModifyurl();
        if (qualityModifyurl.size() > 0){
            fileIds1.addAll(qualityModifyurl);
        }
        //查询所有zj_safe_event表的文件id
        List<String> safeUpload = safeEventDAO.getNotNullUploadurl();
        if (safeUpload.size() > 0){
            fileIds1.addAll(safeUpload);
        }
        List<String> safeModifyurl = safeEventDAO.getNotNullUploadurl();
        if (safeModifyurl.size() > 0){
            fileIds1.addAll(safeModifyurl);
        }

        //查询所有mongoDB里面的fs存储桶的所有_id
        //获取所有存储桶里面的文件信息
        GridFSFindIterable it = fileMapper.findAll();
        //遍历, 获取文件对应的 _id 值
        for (GridFSFile file : it) {
            BsonValue bson = file.getId();
            BsonObjectId bsonObjectId = bson.asObjectId();
            String fileId = String.valueOf(bsonObjectId.getValue());
            fileIds2.add(fileId);
        }

        //获取mongoDB的list与查询出来的list的差集(交集就是说明存了的,没有就说明没有存,没有存则直接删除)
        List<String> getSubtract = (List<String>) CollectionUtils.subtract(fileIds2, fileIds1);
        for (String ids : getSubtract) {
            //遍历,通过_id删除文件
             fileMapper.delete(ids);
        }
        log.info("清理mongoDB存储桶完毕!");
    }

    // todo 所有用户 打卡提醒：分别在2个时间点提醒用户：①上午9：30时提醒用户打卡；
    //  ②上午未打卡，下午14：20时再次提醒用户。【提醒内容：今日还未打卡，请尽快打卡。
    //  有2个快捷路径按钮“考勤打卡”“请休假”，点击后跳转到对应的页面】
    //每天 9:30, 14:20, 未打卡的发起通知
    @Scheduled(cron = "0 30 9 * * ?")
    public void sendMessage1(){
        InvokingRemindMessage();
    }

    @Scheduled(cron = "0 20 14 * * ?")
    public void sendMessage2(){
        InvokingRemindMessage();
    }

    // todo 管理层 未考勤打卡、未请休假：通知提醒在下午15:00时通知提醒管理层。
    //  【提醒内容：XX2022-05-18今日未打卡、未请休假，请知晓！】

    //每隔 2小时取一次数据入库
    @Scheduled(cron = "0 0 0/2 * * ?")
    public void fixedRateJobDataBase() throws IOException {
        List<ZjYcdata> zjYcdataList = doUnitJob();
        zjYcdataList.forEach(zjYcdata -> {
            // 新加逻辑, 按照甲方要求,因数据提供方,提供的数据中,
            //  address为空, 因此这里使用name作为 address
            zjYcdata.setAddress(zjYcdata.getName());
            zjYcdataDAO.insert(zjYcdata);
        });
    }

    /**
     * 自动设置已经请假结束的数据
     */
    @Scheduled(cron = "0 0 0 * * ?")
//    @Scheduled(cron = "0 */1 * * * ?")
    public void checkLeave(){
        //获取所有已审批通过的
        List<ZjPersonLeave> leaves = leaveDAO.getAllFinishLeaves();
        for (ZjPersonLeave leave : leaves) {
            LocalDateTime leaveEndTime = leave.getEndTime();
            int result = LocalDateTime.now().compareTo(leaveEndTime);
            if (result == 1){
                //当前时间大于请假结束时间
                //说明请假已过期, 设置状态为3
                leave.setStatus(3);
                leaveDAO.updatePersonLeave(leave);
            }
        }
    }

    //每天23点执行一次
    @Scheduled(cron = "0 0 23 * * ?")
//    @Scheduled(cron = "0 */1 * * * ?")  //测试每1分钟执行一次
    public void perDayDo(){
        //取数据进行 操作
        //设备
        ENVIREMENTDATA.forEach((key,deviceData)->{
            //分类的
            deviceData.forEach((type,sortData)->{
                AtomicReference<String> name = new AtomicReference<>("");
                AtomicInteger maxValue = new AtomicInteger();
                //具体每一个
                sortData.forEach((data,value)->{
                    if(maxValue.get() ==0){
                        name.set(data);
                        maxValue.set(value);
                    }else{
                        if(value>maxValue.get()){
                            name.set(data);
                            maxValue.set(value);
                        }
                    }
                });
                String address = zjYcdataDAO.getNameByCode(key);
                //存数据库
                ZjYcperday zjYcperday =new ZjYcperday();
                zjYcperday.setType(type);
                zjYcperday.setDaytime(new Date());
                if (address != null && !address.equals("")){
                    zjYcperday.setCode(key);
                }
                zjYcperday.setStatus(Integer.parseInt(name.get()));
                zjYcperday.setAddr(address);
                zjYcperdayDAO.insert(zjYcperday);
            });
        });
        ENVIREMENTDATA = Maps.newHashMap();
    }

    //8小时更新一次
    @Scheduled(fixedRate = 1000*8*60)
    public void fixedRateJob() throws IOException {
        List<ZjYcdata> zjYcdataList = doUnitJob();
        //感觉不需要存数据库
        zjYcdataList.stream().forEach(zjYcdata -> {
            String code = zjYcdata.getCode();
            zjYcdata.setAddress(zjYcdata.getName());
            //统计每次的数据
            if(ENVIREMENTDATA.containsKey(code)){
                Map<String, Map<String,Integer>> typeList = ENVIREMENTDATA.get(code);
                zjYcdata.setNoiselevel(zjYcdata.getNoise());
                String noiseLevel = zjYcdata.getNoiselevel().toString();
                zjYcdata.setPm10level(zjYcdata.getPm10());
                String pm10Level = zjYcdata.getPm10level().toString();
                zjYcdata.setPm25level(zjYcdata.getPm25());
                String pm25Level = zjYcdata.getPm25level().toString();
                dealWithExceed(zjYcdata);
                //噪音判断
                dealWithType("noise",noiseLevel,typeList);
                dealWithType("pm10",pm10Level,typeList);
                dealWithType("pm25",pm25Level,typeList);
            }else {
                Map<String, Map<String,Integer>> mapdata = Maps.newHashMap();
                zjYcdata.setNoiselevel(zjYcdata.getNoise());
                zjYcdata.setPm10level(zjYcdata.getPm10());
                zjYcdata.setPm25level(zjYcdata.getPm25());
                Map<String ,Integer> mapNoise = Maps.newHashMap();
                Map<String,Integer> mapPm25=Maps.newHashMap();
                Map<String,Integer> mapPm10=Maps.newHashMap();
                //初始化
                dealWithExceed(zjYcdata);

                mapNoise.put(zjYcdata.getNoiselevel().toString(),1);
                mapPm25.put(zjYcdata.getPm25level().toString(),1);
                mapPm10.put(zjYcdata.getPm10level().toString(),1);
                //外层赋值
                mapdata.put("noise",mapNoise);
                mapdata.put("pm10",mapPm10);
                mapdata.put("pm25",mapPm25);
                ENVIREMENTDATA.put(code,mapdata);
            }

            zjYcdataMap.put(code,zjYcdata);
        });
    }

    private void dealWithExceed(ZjYcdata zjYcdata) {
        if(zjYcdata.getNoiselevel()>2){
            ZjYcexceeddata zjYcexceeddata = new ZjYcexceeddata();
            zjYcexceeddata.setAddr(zjYcdata.getAddress());
            zjYcexceeddata.setCode(zjYcdata.getCode());
            zjYcexceeddata.setData(zjYcdata.getNoise());
            zjYcexceeddata.setDate(zjYcdata.getTime());
            zjYcexceeddata.setLevel(zjYcdata.getNoiselevel());
            zjYcexceeddata.setItem("noise");
            zjYcexceeddataDAO.insert(zjYcexceeddata);
        }
        if(zjYcdata.getPm10level()>2){
            ZjYcexceeddata zjYcexceeddata = new ZjYcexceeddata();
            zjYcexceeddata.setAddr(zjYcdata.getAddress());
            zjYcexceeddata.setCode(zjYcdata.getCode());
            zjYcexceeddata.setDate(zjYcdata.getTime());
            zjYcexceeddata.setLevel(zjYcdata.getPm10level());
            zjYcexceeddata.setData(zjYcdata.getPm10());
            zjYcexceeddata.setItem("pm10");
            zjYcexceeddataDAO.insert(zjYcexceeddata);
        }
        if(zjYcdata.getPm25level()>2){
            ZjYcexceeddata zjYcexceeddata = new ZjYcexceeddata();
            zjYcexceeddata.setAddr(zjYcdata.getAddress());
            zjYcexceeddata.setCode(zjYcdata.getCode());
            zjYcexceeddata.setDate(zjYcdata.getTime());
            zjYcexceeddata.setLevel(zjYcdata.getPm25level());
            zjYcexceeddata.setData(zjYcdata.getPm25());
            zjYcexceeddata.setItem("pm25");
            zjYcexceeddataDAO.insert(zjYcexceeddata);
        }

    }

    private void dealWithType(String key,String noiseLevel, Map<String, Map<String,Integer>> maps) {
        Map<String, Integer> noise = maps.get(key);
        if(noise.containsKey(noiseLevel)){
            int value = noise.get(noiseLevel);
            noise.put(noiseLevel,value+1) ;
        }else{
            noise.put(noiseLevel,1);
        }
    }

    private  List<ZjYcdata> doUnitJob() throws IOException {
        //先获取请求数据
        Map<String,String> maps = Maps.newHashMap();
        maps.put("client_id",zhuJiOfferConfig.getClientId());
        maps.put("client_secret",zhuJiOfferConfig.getClientSecret());
        maps.put("scope",zhuJiOfferConfig.getScope());
        maps.put("grant_type",zhuJiOfferConfig.getGrantType());
        String returnToken = httpsUtils.httpsPostZhuJi(zhuJiOfferConfig.getLoginurl(), maps);
        //处理token 将json转为对象
        TokenData tokenData = JSONObject.parseObject(returnToken,TokenData.class);

        //在进行请求
        PostData postData = new PostData(zhuJiOfferConfig.getProjectid());
        String a = JSON.toJSONString(postData);
        String token = tokenData.getAccess_token();
        String s = httpsUtils.httpsPostData(zhuJiOfferConfig.getYcApiurl(),a,token);
        List<ZjYcdata> zjYcdataList = JSONObject.parseArray(s,ZjYcdata.class);
        return zjYcdataList;
    }

    private void InvokingRemindMessage(){
        HttpParamers param = HttpParamers.httpPostParamers();
        HttpHeader header = new HttpHeader();
        String response = "";
        try{
            HttpService httpService = new HttpService(host);
            response = httpService.service(url, param, header);
            log.info("打卡接口返回: " + response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Value("${weather.url}")
    private String weatherUrl;
    @Value("${weather.key}")
    private String key;
    @Value("${weather.location_id}")
    private String location;


    @Autowired
    private WeatherDAO weatherDAO;
    /**
     * 定时每小时调用一次天气接口
     */
//    @Scheduled(cron = "0 */1 * * * ?")    //测试1分钟执行一次
    @Scheduled(cron = "0 0 0/1 * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void getWeatherSchedule(){
        try {
            Map<String, Object> maps = Maps.newHashMap();
            maps.put("key", key);
            maps.put("city", location);
            maps.put("extensions", "base");
            maps.put("output", "JSON");

            String response = HttpUtils.sendGet(weatherUrl, maps);
            System.out.println(response);
            WeatherReturn weatherReturn = JSONObject.parseObject(response, WeatherReturn.class);
            if (weatherReturn != null && weatherReturn.getStatus().equals("1")){
                WeatherNow now = weatherReturn.getLives().get(0);
                now.setCreateTime(new Date());
                now.setStatus(1);
                now.setOrder(1);

                weatherDAO.insert(now);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
