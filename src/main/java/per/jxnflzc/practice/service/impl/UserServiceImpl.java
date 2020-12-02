package per.jxnflzc.practice.service.impl;

import org.apache.ibatis.annotations.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import per.jxnflzc.practice.dao.UserInfoMapper;
import per.jxnflzc.practice.dao.UserPermissionMapper;
import per.jxnflzc.practice.dao.UserSignMapper;
import per.jxnflzc.practice.model.*;
import per.jxnflzc.practice.model.enums.PermissionType;
import per.jxnflzc.practice.service.UserService;
import per.jxnflzc.practice.util.IdUtil;
import per.jxnflzc.practice.util.RedisUtil;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private static final String PREFIX_TOKEN = "token ";

    private UserInfoMapper userInfoMapper;

    private UserSignMapper userSignMapper;

    private UserPermissionMapper userPermissionMapper;

    private IdUtil idUtil;

    private RedisUtil redisUtil;

    @Autowired
    public void setUserInfoMapper(UserInfoMapper userInfoMapper) {
        this.userInfoMapper = userInfoMapper;
    }

    @Autowired
    public void setUserSignMapper(UserSignMapper userSignMapper) {
        this.userSignMapper = userSignMapper;
    }

    @Autowired
    public void setUserPermissionMapper(UserPermissionMapper userPermissionMapper) {
        this.userPermissionMapper = userPermissionMapper;
    }

    @Autowired
    public void setIdUtil(IdUtil idUtil) {
        this.idUtil = idUtil;
    }

    @Autowired
    public void setRedisUtil(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    @Override
    public ResponseBodyInfo<UserSign> register(UserSign userSign) {
        ResponseBodyInfo<UserSign> result;
        UserSign exist = userSignMapper.selectByPrimaryKey(userSign.getUserId());
        if (exist == null) {
            int count = userSignMapper.insertSelective(userSign);

            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(userSign.getUserId());
            count += userInfoMapper.insertSelective(userInfo);

            UserPermission userPermission = new UserPermission();
            userPermission.setUserId(userSign.getUserId());
            userPermission.setPermission(PermissionType.OTHERS.getCode());
            count += userPermissionMapper.insertSelective(userPermission);

            if (count == 3) {
                result = ResponseBodyInfo.success(userSign);
            } else {
                result = ResponseBodyInfo.error("保存失败");
            }
        } else {
            result = ResponseBodyInfo.error("用户名已存在");
        }

        return result;
    }

    @Override
    public ResponseBodyInfo<String> login(UserSign userSign) {
        ResponseBodyInfo<String> result;
        UserSign exist = userSignMapper.login(userSign);
        if (exist == null) {
            result = ResponseBodyInfo.error("登录失败");
        } else {
            CurrentUser currentUser = userInfoMapper.generatorCurrentUser(userSign.getUserId());
            String token = idUtil.generatorId();
            currentUser.setUid(token);
            redisUtil.set(token, currentUser, 60 * 60 * 6);
            result = ResponseBodyInfo.success(token);
        }
        return result;
    }

    @Override
    public ResponseBodyInfo<CurrentUser> generatorCurrentUser(String userId) {
        ResponseBodyInfo<CurrentUser> result;
        CurrentUser currentUser = userInfoMapper.generatorCurrentUser(userId);
        if (currentUser != null) {
            result = ResponseBodyInfo.success(currentUser);
        } else {
            result = ResponseBodyInfo.error("用户 " + userId + " 不存在");
        }
        return result;
    }

    @Override
    public ResponseBodyInfo<String> updateUserInfo(UserInfo userInfo) {
        ResponseBodyInfo<String> result;
        int count = userInfoMapper.updateByPrimaryKeySelective(userInfo);

        if (count == 1) {
            result = ResponseBodyInfo.success("保存成功");
        } else {
            result = ResponseBodyInfo.error("保存失败");
        }
        return result;
    }
}
