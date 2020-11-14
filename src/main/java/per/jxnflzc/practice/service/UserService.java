package per.jxnflzc.practice.service;

import per.jxnflzc.practice.model.ResponseBodyInfo;
import per.jxnflzc.practice.model.UserSign;

public interface UserService {
    ResponseBodyInfo register(UserSign userSign);
}
