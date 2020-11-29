package per.jxnflzc.practice.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import per.jxnflzc.practice.anno.NeedLogin;
import per.jxnflzc.practice.model.BaseLabel;
import per.jxnflzc.practice.model.LabelTypeCountLabel;
import per.jxnflzc.practice.model.ResponseBodyInfo;
import per.jxnflzc.practice.model.UserGroup;
import per.jxnflzc.practice.model.enums.LabelType;
import per.jxnflzc.practice.service.BaseLabelService;
import per.jxnflzc.practice.service.UserGroupService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Api(tags = {"用户客群相关"})
@RestController
@RequestMapping("/practice/group")
public class UserGroupController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserGroupController.class);

    private UserGroupService userGroupService;

    @Autowired
    public void setUserGroupService(UserGroupService userGroupService) {
        this.userGroupService = userGroupService;
    }

    @ApiOperation(value = "用户客群列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", required = true, paramType = "header")
    })
    @NeedLogin
    @GetMapping(value = "/queryGroupList", produces = "application/json;charset=utf-8")
    public ResponseBodyInfo<PageImpl<UserGroup>> queryGroupList(Pageable pageable,
                                                                @RequestParam(value = "keywords") String keywords) {
        return userGroupService.queryGroupList(pageable, keywords);
    }

    @ApiOperation(value = "查询用户客群")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", required = true, paramType = "header")
    })
    @NeedLogin
    @GetMapping(value = "/queryGroup", produces = "application/json;charset=utf-8")
    public ResponseBodyInfo<UserGroup> queryGroup(String groupId) {
        return userGroupService.queryGroup(groupId);
    }

    @ApiOperation(value = "添加用户客群")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", required = true, paramType = "header")
    })
    @NeedLogin
    @PostMapping(value = "/saveGroup")
    public ResponseBodyInfo<String> saveGroup(@RequestBody UserGroup userGroup) {
        return userGroupService.saveGroup(userGroup);
    }

    @ApiOperation(value = "删除用户客群")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", required = true, paramType = "header")
    })
    @NeedLogin
    @GetMapping(value = "/deleteGroup")
    public ResponseBodyInfo<String> deleteGroup(@RequestParam("groupId") String groupId) {
        return userGroupService.deleteGroup(groupId);
    }
}
