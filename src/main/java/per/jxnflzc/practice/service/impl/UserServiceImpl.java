package per.jxnflzc.practice.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import per.jxnflzc.practice.dao.UserInfoMapper;
import per.jxnflzc.practice.dao.UserSignMapper;
import per.jxnflzc.practice.model.ResponseBodyInfo;
import per.jxnflzc.practice.model.UserSign;
import per.jxnflzc.practice.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserInfoMapper userInfoMapper;

    private UserSignMapper userSignMapper;

    @Autowired
    public void setUserInfoMapper(UserInfoMapper userInfoMapper) {
        this.userInfoMapper = userInfoMapper;
    }

    @Autowired
    public void setUserSignMapper(UserSignMapper userSignMapper) {
        this.userSignMapper = userSignMapper;
    }

    @Override
    public ResponseBodyInfo register(UserSign userSign) {
        ResponseBodyInfo<Object> result;
        logger.debug("userSign:{}", userSign);
        UserSign exist = userSignMapper.selectByPrimaryKey(userSign.getUserId());
        if (exist == null) {
            int count = userSignMapper.insertSelective(userSign);
            if (count > 0) {
                result = ResponseBodyInfo.success(userSign);
            } else {
                result = ResponseBodyInfo.error("保存失败");
            }
        } else {
            result = ResponseBodyInfo.error("用户名已存在");
        }

        return result;
    }
}
