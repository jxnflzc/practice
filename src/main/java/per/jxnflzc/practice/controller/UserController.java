package per.jxnflzc.practice.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import per.jxnflzc.practice.model.ResponseBodyInfo;
import per.jxnflzc.practice.model.UserSign;
import per.jxnflzc.practice.model.enums.ResponseCode;
import per.jxnflzc.practice.service.UserService;

@Api(tags = {"用户信息相关"})
@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "用户注册")
    @PostMapping(value = "/register", produces = "application/json;charset=utf-8")
    public ResponseBodyInfo register(@RequestBody @ApiParam(value = "用户账号密码") UserSign userSign) {
        if (validateUserSign(userSign)) {
            return userService.register(userSign);
        }
        return ResponseBodyInfo.build(ResponseCode.PARAM_ERROR);
    }

    @ApiOperation(value = "用户登录")
    @PostMapping(value = "/login")
    public ResponseBodyInfo login(@RequestBody @ApiParam(value = "用户账号密码") UserSign userSign) {
        if (validateUserSign(userSign)) {
            return userService.login(userSign);
        }
        return ResponseBodyInfo.build(ResponseCode.PARAM_ERROR);
    }

    private boolean validateUserSign(UserSign userSign) {
        return StringUtils.isNotBlank(userSign.getUserId())
                && StringUtils.isNotBlank(userSign.getUserPassword());
    }
}
