package per.jxnflzc.practice.controller.test;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import per.jxnflzc.practice.model.ResponseBodyInfo;
import per.jxnflzc.practice.model.test.TestInfo;

@Api(tags = {"配置功能测试接口"})
@RestController
@RequestMapping("/test/v1/conf")
public class ConfigTestController {
    private static final Logger logger = LoggerFactory.getLogger(ConfigTestController.class);

    @Value("${test.hello:Hello}")
    private String hello;

    @ApiOperation(value = "获取配置'hello'信息")
    @GetMapping(value = "/hello")
    public ResponseBodyInfo hello() {
        return ResponseBodyInfo.success(hello);
    }
}
