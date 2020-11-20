package per.jxnflzc.practice.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import per.jxnflzc.practice.dao.UserInfoMapper;
import per.jxnflzc.practice.dao.UserSignMapper;
import per.jxnflzc.practice.model.CurrentUser;
import per.jxnflzc.practice.model.ResponseBodyInfo;
import per.jxnflzc.practice.model.UserInfo;
import per.jxnflzc.practice.model.UserSign;
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
    public void setIdUtil(IdUtil idUtil) {
        this.idUtil = idUtil;
    }

    @Autowired
    public void setRedisUtil(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    @Override
    public ResponseBodyInfo register(UserSign userSign) {
        ResponseBodyInfo<Object> result;
        LOGGER.debug("userSign:{}", userSign);
        UserSign exist = userSignMapper.selectByPrimaryKey(userSign.getUserId());
        if (exist == null) {
            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(userSign.getUserId());
            int count = userSignMapper.insertSelective(userSign);
            count += userInfoMapper.insertSelective(userInfo);
            if (count == 2) {
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
    public ResponseBodyInfo login(UserSign userSign) {
        ResponseBodyInfo<Object> result;
        LOGGER.debug("userSign:{}", userSign);
        UserSign exist = userSignMapper.login(userSign);
        if (exist == null) {
            result = ResponseBodyInfo.error("登录失败");
        } else {
            CurrentUser currentUser = userInfoMapper.generatorCurrentUser(userSign.getUserId());
            String token = idUtil.generatorId();
            currentUser.setUid(token);
            redisUtil.set(token, currentUser, 60 * 60 * 6);
            LOGGER.debug("token:{}",token);
            result = ResponseBodyInfo.success(token);
        }
        return result;
    }
}
