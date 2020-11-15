package per.jxnflzc.practice.controller.test;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import per.jxnflzc.practice.anno.NeedLogin;
import per.jxnflzc.practice.model.CurrentUser;
import per.jxnflzc.practice.model.ResponseBodyInfo;
import per.jxnflzc.practice.model.test.TestInfo;

@Api(tags = {"功能测试接口"})
@RestController
@RequestMapping("/test/v1")
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    private TestInfo testInfo;

    @Autowired
    public void setTestInfo(TestInfo testInfo) {
        this.testInfo = testInfo;
    }

    @ApiOperation(value = "获取测试信息")
    @NeedLogin
    @GetMapping(value = "/info")
    public ResponseBodyInfo info() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CurrentUser user = (CurrentUser) authentication.getPrincipal();
        logger.debug("user:{}",user.toString());

        return ResponseBodyInfo.success(testInfo);
    }
}
