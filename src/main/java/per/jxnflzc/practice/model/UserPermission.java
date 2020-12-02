package per.jxnflzc.practice.model;

public class UserPermission {
    private String userId;

    private String permission;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission == null ? null : permission.trim();
    }

    @Override
    public String toString() {
        return "UserPermission{" +
                "userId='" + userId + '\'' +
                ", permission='" + permission + '\'' +
                '}';
    }
}
