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
import per.jxnflzc.practice.model.PracticeLog;
import per.jxnflzc.practice.model.ResponseBodyInfo;
import per.jxnflzc.practice.model.enums.LabelType;
import per.jxnflzc.practice.model.enums.LogType;
import per.jxnflzc.practice.model.enums.PermissionType;
import per.jxnflzc.practice.service.BaseLabelService;
import per.jxnflzc.practice.service.PracticeLogService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Api(tags = {"日志相关"})
@RestController
@RequestMapping("/practice/log")
public class PracticeLogController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PracticeLogController.class);

    private PracticeLogService practiceLogService;

    @Autowired
    public void setPracticeLogService(PracticeLogService practiceLogService) {
        this.practiceLogService = practiceLogService;
    }

    @ApiOperation(value = "日志列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", required = true, paramType = "header")
    })
    @NeedLogin(permission = PermissionType.ADMIN)
    @GetMapping(value = "/queryLogList", produces = "application/json;charset=utf-8")
    public ResponseBodyInfo<PageImpl<PracticeLog>> queryLogList(Pageable pageable,
                                                                @RequestParam(value = "keywords", required = false) String keywords,
                                                                @RequestParam(value = "logType", required = false) String logType) {
        return practiceLogService.queryLogList(pageable, keywords, logType);
    }

    @ApiOperation(value = "查询日志分类")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", required = true, paramType = "header")
    })
    @NeedLogin(permission = PermissionType.ADMIN)
    @GetMapping(value = "/queryLogTypeList")
    public ResponseBodyInfo<List<LogType>> queryLogTypeList() {
        LogType[] enums = LogType.class.getEnumConstants();
        List<LogType> logTypeList = new ArrayList<>(Arrays.asList(enums));
        return ResponseBodyInfo.success(logTypeList);
    }
}
