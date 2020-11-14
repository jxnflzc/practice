package per.jxnflzc.practice.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "用户账号密码")
public class UserSign {
    @ApiModelProperty(value = "用户账号")
    private String userId;

    @ApiModelProperty(value = "用户密码")
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

    @Override
    public String toString() {
        return "UserSign{" +
                "userId='" + userId + '\'' +
                ", userPassword='" + userPassword + '\'' +
                '}';
    }
}