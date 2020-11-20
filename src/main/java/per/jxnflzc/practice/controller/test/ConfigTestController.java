package per.jxnflzc.practice.controller.test;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import per.jxnflzc.practice.model.ResponseBodyInfo;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Api(tags = {"配置功能测试接口"})
@RestController
@RequestMapping("/test/v1/conf")
public class ConfigTestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigTestController.class);

    private static final String PATH_PRE = "/properties/";

    private static final String CLASS_PATH_PRE = "classpath:properties/";

    @Value("${test.hello:Hello}")
    private String hello;

    @ApiOperation(value = "获取配置'hello'信息")
    @GetMapping(value = "/hello")
    public ResponseBodyInfo hello() {
        return ResponseBodyInfo.success(hello);
    }

    @ApiOperation(value = "获取配置文件")
    @GetMapping(value = "/file")
    public ResponseBodyInfo file(HttpServletResponse response) {
        String filename = "test.properties";
        String filepath = PATH_PRE + filename;
        byte[] buffer = new byte[1024];
        InputStream fis = null;
        BufferedInputStream bis = null;
        try {
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, StandardCharsets.UTF_8));

            fis = this.getClass().getResourceAsStream(filepath);
            bis = new BufferedInputStream(fis);
            OutputStream os = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
        } catch (Exception e) {
            LOGGER.debug("下载失败:{}", e.toString());
            return ResponseBodyInfo.success("下载失败");
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return ResponseBodyInfo.success("下载成功");
    }
}
