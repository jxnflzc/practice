package per.jxnflzc.practice.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import per.jxnflzc.practice.anno.NeedLogin;
import per.jxnflzc.practice.model.BaseLabel;
import per.jxnflzc.practice.model.ResponseBodyInfo;
import per.jxnflzc.practice.model.enums.LabelType;
import per.jxnflzc.practice.service.BaseLabelService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Api(tags = {"基础标签相关"})
@RestController
@RequestMapping("/practice/label")
public class BaseLabelController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseLabelController.class);

    private BaseLabelService baseLabelService;

    @Autowired
    public void setBaseLabelService(BaseLabelService baseLabelService) {
        this.baseLabelService = baseLabelService;
    }

    @ApiOperation(value = "基础标签列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", required = true, paramType = "header")
    })
    @NeedLogin
    @GetMapping(value = "/queryLabelList", produces = "application/json;charset=utf-8")
    public ResponseBodyInfo queryLabelList(Pageable pageable,
                                           @RequestParam(value = "keywords", required = false) String keywords,
                                           @RequestParam(value = "labelType", required = false) String labelType) {
        return baseLabelService.queryLabelList(pageable, keywords, labelType);
    }

    @ApiOperation(value = "添加基础标签")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", required = true, paramType = "header")
    })
    @NeedLogin
    @PostMapping(value = "/saveLabel")
    public ResponseBodyInfo saveLabel(@RequestBody BaseLabel baseLabel) {
        return baseLabelService.saveLabel(baseLabel);
    }

    @ApiOperation(value = "查询基础标签分类")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", required = true, paramType = "header")
    })
    @NeedLogin
    @GetMapping(value = "/queryLabelTypeList")
    public ResponseBodyInfo queryLabelTypeList() {
        LabelType[] enums = LabelType.class.getEnumConstants();
        List<LabelType> labelTypeList = new ArrayList<>(Arrays.asList(enums));
        return ResponseBodyInfo.success(labelTypeList);
    }

    @ApiOperation(value = "查询基础标签")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", required = true, paramType = "header")
    })
    @NeedLogin
    @GetMapping(value = "/queryLabel")
    public ResponseBodyInfo queryLabel(@RequestParam("labelId") String labelId) {
        return baseLabelService.queryLabel(labelId);
    }

    @ApiOperation(value = "删除基础标签")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", required = true, paramType = "header")
    })
    @NeedLogin
    @GetMapping(value = "/deleteLabel")
    public ResponseBodyInfo deleteLabel(@RequestParam("labelId") String labelId) {
        return baseLabelService.deleteLabel(labelId);
    }
}
