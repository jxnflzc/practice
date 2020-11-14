package per.jxnflzc.practice.model.test;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "test.info")
@PropertySource(value ={"classpath:properties/test.properties"})
@ApiModel(description = "测试信息")
public class TestInfo {
    @ApiModelProperty(value = "测试名称")
    private String name;

    @ApiModelProperty(value = "测试版本")
    private String version;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "TestInfo{" +
                "name='" + name + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
