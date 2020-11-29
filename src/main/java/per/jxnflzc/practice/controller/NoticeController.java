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
import per.jxnflzc.practice.model.Notice;
import per.jxnflzc.practice.model.ResponseBodyInfo;
import per.jxnflzc.practice.model.UserGroup;
import per.jxnflzc.practice.model.enums.LabelType;
import per.jxnflzc.practice.model.enums.NoticeLevel;
import per.jxnflzc.practice.model.enums.PermissionType;
import per.jxnflzc.practice.service.NoticeService;
import per.jxnflzc.practice.service.UserGroupService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Api(tags = {"公告通知相关"})
@RestController
@RequestMapping("/practice/notice")
public class NoticeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(NoticeController.class);

    private NoticeService noticeService;

    @Autowired
    public void setNoticeService(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @ApiOperation(value = "公告通知列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", required = true, paramType = "header")
    })
    @NeedLogin
    @GetMapping(value = "/queryNoticeList", produces = "application/json;charset=utf-8")
    public ResponseBodyInfo<PageImpl<Notice>> queryNoticeList(Pageable pageable,
                                                              @RequestParam(value = "keywords") String keywords) {
        return noticeService.queryNoticeList(pageable, keywords);
    }

    @ApiOperation(value = "查询通知")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", required = true, paramType = "header")
    })
    @NeedLogin
    @GetMapping(value = "/queryNotice", produces = "application/json;charset=utf-8")
    public ResponseBodyInfo<Notice> queryNotice(String noticeId) {
        return noticeService.queryNotice(noticeId);
    }

    @ApiOperation(value = "查询通知级别")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", required = true, paramType = "header")
    })
    @NeedLogin
    @GetMapping(value = "/queryNoticeLevelList")
    public ResponseBodyInfo<List<NoticeLevel>> queryNoticeLevelList() {
        NoticeLevel[] enums = NoticeLevel.class.getEnumConstants();
        List<NoticeLevel> noticeLevelList = new ArrayList<>(Arrays.asList(enums));
        return ResponseBodyInfo.success(noticeLevelList);
    }

    @ApiOperation(value = "删除通知")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", required = true, paramType = "header")
    })
    @NeedLogin
    @GetMapping(value = "/deleteNotice")
    public ResponseBodyInfo<String> deleteNotice(@RequestParam("noticeId") String noticeId) {
        return noticeService.deleteNotice(noticeId);
    }
}
