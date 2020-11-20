package per.jxnflzc.practice.controller.test;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import per.jxnflzc.practice.anno.NeedLogin;
import per.jxnflzc.practice.model.CurrentUser;
import per.jxnflzc.practice.model.ResponseBodyInfo;
import per.jxnflzc.practice.model.test.TestInfo;
import per.jxnflzc.practice.util.PracticeUtil;

@Api(tags = {"功能测试接口"})
@RestController
@RequestMapping("/test/v1")
public class TestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    private TestInfo testInfo;

    @Autowired
    public void setTestInfo(TestInfo testInfo) {
        this.testInfo = testInfo;
    }

    @NeedLogin
    @ApiOperation(value = "获取测试信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", required = true, paramType = "header")
    })
    @GetMapping(value = "/info")
    public ResponseBodyInfo info() {
        CurrentUser user = PracticeUtil.getInstance().getCurrentUser();
        LOGGER.debug("user:{}",user);

        return ResponseBodyInfo.success(testInfo);
    }
}
