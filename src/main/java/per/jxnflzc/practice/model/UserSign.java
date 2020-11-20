package per.jxnflzc.practice.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.Size;

@ApiModel(description = "用户登录数据")
public class UserSign {
    @ApiModelProperty(value = "用户Id")
    @NotBlank(message = "用户Id不能为空")
    private String userId;

    @ApiModelProperty(value = "用户密码")
    @Size(message = "用户密码长度必须在4和16之间",min = 4, max = 16)
    private String userPassword;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }
}