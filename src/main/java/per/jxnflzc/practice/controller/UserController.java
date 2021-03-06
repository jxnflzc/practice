package per.jxnflzc.practice.controller;

import io.swagger.annotations.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import per.jxnflzc.practice.anno.NeedLogin;
import per.jxnflzc.practice.model.CurrentUser;
import per.jxnflzc.practice.model.ResponseBodyInfo;
import per.jxnflzc.practice.model.UserInfo;
import per.jxnflzc.practice.model.UserSign;
import per.jxnflzc.practice.model.enums.ResponseCode;
import per.jxnflzc.practice.service.UserService;
import per.jxnflzc.practice.util.PracticeUtil;
import per.jxnflzc.practice.util.RedisUtil;

import javax.validation.Valid;

@Api(tags = {"用户信息相关"})
@RestController
@RequestMapping("/practice/user")
public class UserController {
    private UserService userService;

    private RedisUtil redisUtil;

    @Autowired
    public void setRedisUtil(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "用户注册")
    @PostMapping(value = "/register", produces = "application/json;charset=utf-8")
    public ResponseBodyInfo<UserSign> register(@Valid @RequestBody  @ApiParam(value = "用户账号密码") UserSign userSign) {
        if (validateUserSign(userSign)) {
            return userService.register(userSign);
        }
        return ResponseBodyInfo.build(ResponseCode.PARAM_ERROR);
    }

    @ApiOperation(value = "用户登录")
    @PostMapping(value = "/login")
    public ResponseBodyInfo<String> login(@RequestBody @ApiParam(value = "用户账号密码") UserSign userSign) {
        if (validateUserSign(userSign)) {
            return userService.login(userSign);
        }
        return ResponseBodyInfo.build(ResponseCode.PARAM_ERROR);
    }

    @ApiOperation(value = "用户修改信息")
    @PostMapping(value = "/updateUserInfo")
    public ResponseBodyInfo<String> updateUserInfo(@RequestBody @ApiParam(value = "用户个人信息") UserInfo userInfo) {
        return userService.updateUserInfo(userInfo);
    }

    private boolean validateUserSign(UserSign userSign) {
        return StringUtils.isNotBlank(userSign.getUserId())
                && StringUtils.isNotBlank(userSign.getUserPassword());
    }

    @ApiOperation(value = "用户登出")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", required = true, paramType = "header")
    })
    @NeedLogin
    @GetMapping(value = "/logout")
    public ResponseBodyInfo<String> logout() {
        String token = PracticeUtil.getInstance().getCurrentUserToken();
        redisUtil.del(token);
        return ResponseBodyInfo.success("登出成功");
    }

    @ApiOperation(value = "用户个人信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", required = true, paramType = "header")
    })
    @NeedLogin
    @GetMapping(value = "/info")
    public ResponseBodyInfo<CurrentUser> info() {
        String userId = PracticeUtil.getInstance().getCurrentUserId();
        return userService.generatorCurrentUser(userId);
    }

    @ApiOperation(value = "当前用户权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", required = true, paramType = "header")
    })
    @NeedLogin
    @GetMapping(value = "/auth")
    public ResponseBodyInfo<String> auth() {
        String permission = PracticeUtil.getInstance().getCurrentUserPermission();
        return ResponseBodyInfo.success(permission);
    }
}
