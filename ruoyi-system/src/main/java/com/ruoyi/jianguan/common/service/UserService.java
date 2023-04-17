package com.ruoyi.jianguan.common.service;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpStatus;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ruoyi.common.annotation.CheckRepeatCommit;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.dao.SsFGroupsDAO;
import com.ruoyi.common.core.dao.SsFUserGroupDAO;
import com.ruoyi.common.core.dao.SsFUsersDAO;
import com.ruoyi.common.core.dao.jianguan.ZjFGroupsProjectsDAO;
import com.ruoyi.common.core.domain.dto.RoleDTO;
import com.ruoyi.common.core.domain.dto.UserOnlineDTO;
import com.ruoyi.common.core.domain.entity.PowerData;
import com.ruoyi.common.core.domain.entity.SsFGroups;
import com.ruoyi.common.core.domain.entity.SsFUserGroup;
import com.ruoyi.common.core.domain.entity.SsFUsers;
import com.ruoyi.common.core.domain.model.LoginBody;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.domain.model.SsFUserRole;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.common.config.zjrw.ZhuJiOfferConfig;
import com.ruoyi.common.core.domain.object.TokenData;
import com.ruoyi.common.core.service.LogininforService;
import com.ruoyi.common.enums.DeviceType;
import com.ruoyi.common.enums.UserType;
import com.ruoyi.common.helper.LoginHelper;
import com.ruoyi.common.utils.JwtUtil;
import com.ruoyi.common.utils.MessageUtils;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.jianguan.common.dao.SsFUserOnlineDAO;
import com.ruoyi.jianguan.common.dao.SsFUserRoleDAO;
import com.ruoyi.jianguan.common.domain.dto.*;
import com.ruoyi.jianguan.common.domain.entity.SsFUserOnline;

import com.ruoyi.common.utils.jianguan.zjrw.HttpsUtils;
import com.ruoyi.common.utils.jianguan.zjrw.MD5Util;
import com.ruoyi.common.utils.jianguan.zjrw.MyExcelUtil;
import com.ruoyi.system.service.SysLoginService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ：lin_zhixiong
 * @date ：Created in 2021/7/6 12:09 下午
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description:
 **/
@Service("loginUserService")
@RequiredArgsConstructor
public class UserService {

    Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
//    @Autowired
//    private UserDAO userDAO;

    @Autowired
    private SsFUsersDAO ssFUsersDAO;

    @Autowired
    private SsFUserGroupDAO ssFUserGroupDAO;

    @Autowired
    private SsFGroupsDAO ssFGroupsDAO;

    @Autowired
    private SsFUserOnlineDAO userOnlineDAO;

    @Autowired
    private SsFUserRoleDAO userRoleDAO;

    @Autowired
    private ZhuJiOfferConfig zhuJiOfferConfig;

    @Autowired
    private HttpsUtils httpsUtils;

    @Autowired
    private LogininforService asyncService;

    @Autowired
    @Qualifier("zjFGroupsProjectsDAO")
    private ZjFGroupsProjectsDAO zjFGroupsProjectsDAO;

    public volatile int a = 0;

    @Deprecated
    public ResponseBase login(SsFUsers user) {
        //根据 用户名与密码进行匹配
//        SsFUsers findUser = ssFUsersDAO.checkLogin(user.getUsername());
        SsFUsers findUser = ssFUsersDAO.userLogin(user.getUsername());
        log.info("该用户的id为: " + findUser.getId());
        LoginData loginData = new LoginData();
        //检查是否存在
        if (findUser == null) {
            loginData.setToken("用户不存在");
            return new ResponseBase(200, "用户不存在");
        }
        if (findUser.getStstate() == 0) {
            loginData.setToken("该账号已冻结");
            return new ResponseBase(200, "该账号已冻结");
        }
        if (!MD5Util.string2MD5(user.getPwd()).equals(findUser.getPwd())) {
            loginData.setToken("密码不正确");
            return new ResponseBase(200, "密码不正确");
        }

        List<SsFUserGroup> ssFUserGroups = ssFUserGroupDAO.getUserGroupsOfProject(findUser.getId());
//        List<SsFGroups> ssFGroups = Lists.newArrayList();
//        for (SsFUserGroup ssFUserGroup : ssFUserGroups) {
//            SsFGroups ssFGroup = ssFGroupsDAO.selectByPrimaryKey(ssFUserGroup.getGroupid());
//            ssFGroups.add(ssFGroup);
//        }

//        List<Integer> projects = Lists.newArrayList();
//        List<Integer> gongquids = Lists.newArrayList();
//        List<Object> groupsProjects = Lists.newArrayList();
//        List<String> projectCode = Lists.newArrayList();
//
//        for (SsFGroups ssFGroup : ssFGroups) {
//            //只会存在3个情况
//            //总部人员
//            if (ssFGroup.getGrouplevel() == 2) {
//                //工区
//                List<SsFGroups> ssFGroupsList = ssFGroupsDAO.getByParentId(ssFGroup.getId());
//                ssFGroupsList.forEach(sFGroups -> {
//                    //项目
//                    gongquids.add(sFGroups.getId());
//                    List<SsFGroups> ssFGroupsListSecond = ssFGroupsDAO.getByParentId(sFGroups.getId());
//                    ssFGroupsListSecond.forEach(gg -> {
//                        projects.add(gg.getId());
//                    });
//                });
//            }//工区人员
//            else if (ssFGroup.getGrouplevel() == 4) {
//                gongquids.add(ssFGroup.getId());
//                List<SsFGroups> ssFGroupsList = ssFGroupsDAO.getByParentId(ssFGroup.getId());
//                ssFGroupsList.forEach(gg -> {
//                    projects.add(gg.getId());
//                });
//            }//项目人员
//            else if (ssFGroup.getGrouplevel() == 5) {
//                projects.add(ssFGroup.getId());
//            }
//            //根据list 获取 项目对应的id
//            if (projects != null && projects.size() != 0) {
//                List<ZjFGroupsProjects> projectGroup = zjFGroupsProjectsDAO.getProjectCode(projects);
//                groupsProjects.add(projectGroup);
//                projectGroup.forEach(gg -> {
//                    projectCode.add(gg.getProjectid());
//                });
//            }
//        }

        // 此处可根据登录用户的数据不同 自行创建 loginUser
        LoginUser loginUser = new LoginUser();
        loginUser.setUserId(Long.valueOf(findUser.getId()));
        loginUser.setUsername(findUser.getUsername());
        loginUser.setNickName(findUser.getName());
        loginUser.setUserType(UserType.BUSINESS_USER.getUserType());
        // 生成token
        LoginHelper.loginByDevice(loginUser, DeviceType.PC);
        String authToken = StpUtil.getTokenValue();
        asyncService.recordLogininfor(findUser.getUsername(), Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success"), ServletUtils.getRequest());

        // todo 暂时只支持1对1,如果用户对应权限表变成了1对多,这个位置需要做修改
        Integer roleId = ssFUsersDAO.getRoleById(findUser.getId());
        //PowerData powerData = new PowerData(findUser.getId(), roleId);
        PowerData powerData = null;
        //转token
        String jwtToken = JwtUtil.sign(powerData, JwtUtil.SSO_TIME);
        loginData.setToken(jwtToken);
        loginData.setAuthToken(authToken);
        loginData.setId(findUser.getId());
        loginData.setName(findUser.getName());

        LoginDataDTO dataDTO = new LoginDataDTO();
        if (roleId == null) {
            return new ResponseBase(201, "该角色没有配置角色权限,请到运维系统进行配置! ");
        }
        if (ssFUserGroups.size() == 0) {
            return new ResponseBase(201, "该角色没有配置工区, 请到管理员账号下进行配置! ");
        }
        dataDTO.setLoginData(loginData);
        dataDTO.setGroupid(roleId);

        return new ResponseBase(200, "登陆成功", dataDTO);
    }

    public ResponseBase viewToken() throws Exception {
        Map<String, String> maps = Maps.newHashMap();
        maps.put("client_id", zhuJiOfferConfig.getClientId());
        maps.put("client_secret", zhuJiOfferConfig.getClientSecret());
        maps.put("scope", zhuJiOfferConfig.getScope());
        maps.put("grant_type", zhuJiOfferConfig.getGrantType());
        String returnToken = httpsUtils.httpsPostZhuJi(zhuJiOfferConfig.getLoginurl(), maps);
        //处理token 将json转为对象
        TokenData tokenData = JSONObject.parseObject(returnToken, TokenData.class);
        //获取视频的token
        String url = "https://proj.bim-ace.com:10207/services/api/data-center/ys7/" +
                "subAccessToken?Id=0430554C-B95A-4F24-94E8-AEE9B27B3A08&AccountId=c85a9c74afac4b6e9fa516efe34e586d";
        String viewToken = httpsUtils.httpsGet(tokenData.getAccess_token(), url);
        return new ResponseBase(200, "查询成功", viewToken);
    }

    public ResponseBase viewNewToken() throws Exception {
        String appKey = zhuJiOfferConfig.getAppKey();
        String secret = zhuJiOfferConfig.getSecret();
        String url = zhuJiOfferConfig.getYcApiNewUrl();
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("appKey", appKey);
        paramMap.put("appSecret", secret);
        String viewToken = HttpUtil.post(url, paramMap);
        NewTokenData newTokenData = JSONObject.parseObject(viewToken, NewTokenData.class);
        return new ResponseBase(200, "查询成功", newTokenData.getData().getAccessToken());
    }

    public ResponseBase userAddGroups(UserAddGroupsDTO addGroupsDTO) {
        //当该用户的roleid不是2-管理员,不允许添加
        if (!LoginHelper.getLoginUser().getRolePermission().contains("admin")) {
            return new ResponseBase(HttpStatus.HTTP_INTERNAL_ERROR, "用户添加组织机构失败,该用户没有权限配置组织机构!");
        }
        SsFUserGroup userGroup = new SsFUserGroup();
        userGroup.setStstate(0);
        userGroup.setStorder(0);

        //先删除用户对应的项目下所有权限
//        ssFUserGroupDAO.deleteByUserIds(addGroupsDTO.getUserIds());
        for (Integer userid : addGroupsDTO.getUserIds()) {
            List<Integer> childProjects = ssFUserGroupDAO.getChildIdByProjectId(addGroupsDTO.getProjectId());
            ssFUserGroupDAO.deleteProjectsByUser(userid, childProjects);
        }
        //再添加相应权限
        for (Integer groupsId : addGroupsDTO.getGroupsIds()) {
            for (Integer id : addGroupsDTO.getUserIds()) {
                userGroup.setSttime(new Date());
                userGroup.setUserid(id);
                userGroup.setGroupid(groupsId);
                ssFUserGroupDAO.insert(userGroup);
            }
        }
        return new ResponseBase(HttpStatus.HTTP_OK, "用户添加组织机构权限成功!");
    }

    public ResponseBase getGroups(Integer userId) {

        if (userId == null || userId == 0 || userId.toString().equals("")) {
            List<SsFGroups> groupsList = ssFGroupsDAO.getGroupsByLevel();
            return new ResponseBase(200, "查询组织机构成功!", groupsList);
        }

        List<SsFUserGroup> groupsList = ssFUserGroupDAO.getUserGroupsOfProject(userId);
        return new ResponseBase(200, "根据用户id查询用户拥有的组织机构成功!", groupsList);

    }

    public ResponseBase updatePwd(SsFUsers user) {
        //根据 用户名与密码进行匹配
        Integer id = LoginHelper.getUserId().intValue();
        SsFUsers findUser = ssFUsersDAO.checkLoginById(id);
        LoginData loginData = new LoginData();
        //检查是否存在
        if (ObjectUtils.isEmpty(findUser)) {
            loginData.setToken("用户不存在");
            return new ResponseBase(200, "用户不存在");
        }
        String newPwd = MD5Util.string2MD5(user.getPwd());
        Integer row = ssFUsersDAO.updatePwd(newPwd, findUser.getId());
        if (row == 1) {
            return new ResponseBase(200, "修改密码成功!");
        }
        return new ResponseBase(200, "修改密码失败!");
    }

    @Deprecated
    @CheckRepeatCommit
    public ResponseBase updateOnline(String cid, String lon, String lat) {
        //通过token获取用户的id
        Integer userid = LoginHelper.getUserId().intValue();
        Integer roleId = LoginHelper.getLoginUser().getRoleId().intValue();

        SsFUsers users = ssFUsersDAO.selectByPrimaryKey(userid);
        Map<String, String> maps = Maps.newHashMap();
        //两个roleId进行对比，如果不一样说明需要重新登录
        SsFUserRole userRole = userRoleDAO.getByUserid(userid);
        //首先查询人员账号的状态  如果为0需要重新登录
        if (users.getStstate() == 0) {
            maps.put("isChange", "true");
            return new ResponseBase(200, "更新用户在线失败! ", maps);
        }

        if ((userRole == null && roleId == null)) {
            //进行更新
            SsFUserOnline userOnline = new SsFUserOnline();
            userOnline.setUserid(userid);
            userOnline.setUsername(users.getUsername());
            userOnline.setName(users.getName());
            userOnline.setCid(cid);
            userOnline.setRole(userRole.getRoleid());
            userOnline.setLat(lat);
            userOnline.setLon(lon);
            //在此之前查询在线表是否有该用户数据
            Integer result = userOnlineDAO.getByUserid(userid);
            if (result == 0) {
                userOnlineDAO.insert(userOnline);
            } else {
                userOnlineDAO.updateTimeByUserid(userid, cid, userRole.getRoleid(), lon, lat);
            }
            maps.put("isChange", "false");
            return new ResponseBase(200, "更新用户在线成功! ", maps);
        } else if (userRole != null && roleId != null) {
            if (userRole.getRoleid() != null && roleId == userRole.getRoleid()) {
                SsFUserOnline userOnline = new SsFUserOnline();
                userOnline.setUsername(users.getUsername());
                userOnline.setUserid(userid);
                userOnline.setCid(cid);
                userOnline.setRole(userRole.getRoleid());
                userOnline.setName(users.getName());
                userOnline.setLat(lat);
                userOnline.setLon(lon);
                //在此之前查询在线表是否有该用户数据
                Integer result = userOnlineDAO.getByUserid(userid);
                if (result == 0) {
                    userOnlineDAO.insert(userOnline);
                } else {
                    userOnlineDAO.updateTimeByUserid(userid, cid, userRole.getRoleid(), lon, lat);
                }
                maps.put("isChange", "false");
                return new ResponseBase(200, "更新用户在线成功! ", maps);
            } else {
                maps.put("isChange", "true");
                return new ResponseBase(200, "更新用户在线失败! ", maps);
            }
        } else {
            maps.put("isChange", "true");
            return new ResponseBase(200, "更新用户在线失败! ", maps);
        }
    }

    public ResponseBase getOnline() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String today = sdf.format(date);
        String startTime = today + " 00:00:00";
        String endTime = today + " 23:59:59";
        //监理在线   2022-08-22号， 增加联ss_f_users表，当账号被冻结时不记录
        Integer onlineJLCount = userOnlineDAO.getJLOnlineCount(startTime, endTime);
        List<String> onlineJLUsers = userOnlineDAO.getJLOnlineUsers(startTime, endTime);
        List<String> allJLUsers = userOnlineDAO.getAllJLUsers(startTime, endTime);
        OnlineUser JLUser = new OnlineUser();
        JLUser.setOnlineCount(onlineJLCount);
        JLUser.setOnlineUser(onlineJLUsers);
        JLUser.setAllUser(allJLUsers);
        //施工在线
        Integer onlineSGCount = userOnlineDAO.getSGOnlineCount(startTime, endTime);
        List<String> onlineSGUsers = userOnlineDAO.getSGOnlineUsers(startTime, endTime);
        List<String> allSGUsers = userOnlineDAO.getAllSGUsers(startTime, endTime);
        OnlineUser SGUser = new OnlineUser();
        SGUser.setOnlineCount(onlineSGCount);
        SGUser.setOnlineUser(onlineSGUsers);
        SGUser.setAllUser(allSGUsers);
        //业主在线
        Integer onlineYZCount = userOnlineDAO.getYZOnlineCount(startTime, endTime);
        List<String> onlineYZUsers = userOnlineDAO.getYZOnlineUsers(startTime, endTime);
        List<String> allYZUsers = userOnlineDAO.getAllYZUsers(startTime, endTime);
        OnlineUser YZUser = new OnlineUser();
        YZUser.setOnlineCount(onlineYZCount);
        YZUser.setOnlineUser(onlineYZUsers);
        YZUser.setAllUser(allYZUsers);

        HashMap<String, Object> map = new HashMap();
        map.put("JL", JLUser);
        map.put("SG", SGUser);
        map.put("YZ", YZUser);

        return new ResponseBase(200, "在线人数查询成功!", map);
    }

    public ResponseBase getOnlineCount(String date) {
        try {
            List<UserOnlineDTO> userOnlineDTO = Lists.newArrayList();
            if (date != null && !date.equals("")) {
                String startTime = date + " 00:00:00";
                String endTime = date + " 23:59:59";
                userOnlineDTO = userOnlineDAO.getAllUserOnlineByTime(startTime, endTime);
            } else {
                userOnlineDTO = userOnlineDAO.getAllUserOnline();
            }
            return new ResponseBase(200, "调用成功!", userOnlineDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseBase(200, "调用失败!", e.getStackTrace());
        }
    }

    public void exportOnlineCount(HttpServletRequest request, HttpServletResponse response, String date) {

        try {
            List<UserOnlineDTO> userOnlineDTO = Lists.newArrayList();
            if (date != null && !date.equals("")) {
                String startTime = date + " 00:00:00";
                String endTime = date + " 23:59:59";
                userOnlineDTO = userOnlineDAO.getAllUserOnlineByTime(startTime, endTime);
            } else {
                userOnlineDTO = userOnlineDAO.getAllUserOnline();
            }

            //创建excel表头
            List<String> column = Lists.newArrayList();
            column.add("用户ID");
            column.add("昵称");
            column.add("名字");
            column.add("角色ID");
            column.add("角色名称");
            column.add("最后在线时间");
            //表头对应数据
            List<Map<String, Object>> data = Lists.newArrayList();

            for (UserOnlineDTO onlineDTO : userOnlineDTO) {
                Map<String, Object> dataMap = Maps.newHashMap();
                dataMap.put("用户ID", onlineDTO.getUserId());
                dataMap.put("昵称", onlineDTO.getUserName());
                dataMap.put("名字", onlineDTO.getName());
                dataMap.put("角色ID", onlineDTO.getRoleId());
                dataMap.put("角色名称", onlineDTO.getRoleName());
//                dataMap.put("最后在线时间", onlineDTO.getUpdateTime());
                dataMap.put("最后在线时间", String.valueOf
                        (DateUtil.format(onlineDTO.getUpdateTime(), DatePattern.NORM_DATETIME_FORMAT)));

                data.add(dataMap);
            }
            // 调用工具类导出
            MyExcelUtil.exportExcel("活跃人员统计表", column, data, request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResponseBase getAllUserOnline() {
        List<SsFUserOnline> userOnlineList = userOnlineDAO.getAll();
        return new ResponseBase(200, "查询成功", userOnlineList);
    }

    public ResponseBase getUserIDListByProjectId(String projectId) {
        List<String> idList = ssFUsersDAO.getUserIDListByProjectId(projectId);
        return new ResponseBase(200, "查询成功", idList);
    }
}
