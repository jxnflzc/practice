package per.jxnflzc.practice.interceptor;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import per.jxnflzc.practice.anno.NeedLogin;
import per.jxnflzc.practice.model.CurrentUser;
import per.jxnflzc.practice.model.ResponseBodyInfo;
import per.jxnflzc.practice.model.enums.ResponseCode;
import per.jxnflzc.practice.util.RedisUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

public class LoginInterceptor implements HandlerInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginInterceptor.class);

    private RedisUtil redisUtil;

    @Autowired
    public void setRedisUtil(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    //存放鉴权信息的Header名称，默认是Authorization
    private String httpHeaderName = "Authorization";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        String token = request.getHeader(httpHeaderName);

        if (StringUtils.hasText(token) && redisUtil.hasKey(token)) {
            redisUtil.del(token);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    private void returnInfo(HttpServletResponse response){
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try (PrintWriter writer = response.getWriter()) {
            ResponseBodyInfo result = ResponseBodyInfo.build(ResponseCode.NOT_LOGIN);
            writer.print(JSON.toJSONString(result));
        } catch (IOException e) {
            LOGGER.error("拦截器输出流异常:{}", e.toString());
        }
    }
}
