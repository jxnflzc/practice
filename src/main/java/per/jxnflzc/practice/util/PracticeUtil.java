package per.jxnflzc.practice.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import per.jxnflzc.practice.model.CurrentUser;

public final class PracticeUtil {
    private PracticeUtil() {

    }

    public static PracticeUtil getInstance() {
        return new PracticeUtil();
    }

    public CurrentUser getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        if (principal instanceof  CurrentUser) {
            return (CurrentUser) principal;
        }
        return null;
    }

    public String getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        if (principal instanceof  CurrentUser) {
            return ((CurrentUser) principal).getUserId();
        }
        return authentication.getName();
    }

    public String getCurrentUserToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        if (principal instanceof  CurrentUser) {
            return ((CurrentUser) principal).getUid();
        }
        return null;
    }

    public String getCurrentUserPermission() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        if (principal instanceof  CurrentUser) {
            return ((CurrentUser) principal).getUserPermission();
        }
        return "V";
    }
}
