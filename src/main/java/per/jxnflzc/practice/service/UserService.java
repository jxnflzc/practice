package per.jxnflzc.practice.service;

import per.jxnflzc.practice.model.CurrentUser;
import per.jxnflzc.practice.model.ResponseBodyInfo;
import per.jxnflzc.practice.model.UserInfo;
import per.jxnflzc.practice.model.UserSign;

public interface UserService {
    ResponseBodyInfo<UserSign> register(UserSign userSign);

    ResponseBodyInfo<String> login(UserSign userSign);

    ResponseBodyInfo<String> updateUserInfo(UserInfo userInfo);

    ResponseBodyInfo<CurrentUser> generatorCurrentUser(String userId);
}
