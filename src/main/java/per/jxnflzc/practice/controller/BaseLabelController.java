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
import per.jxnflzc.practice.service.BaseLabelService;

@Api(tags = {"基础标签相关"})
@RestController
@RequestMapping("/label")
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
    public ResponseBodyInfo queryLabelList(Pageable pageable) {
        return baseLabelService.queryLabelList(pageable);
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
}
