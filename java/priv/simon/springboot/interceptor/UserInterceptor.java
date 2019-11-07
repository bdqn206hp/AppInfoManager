package priv.simon.springboot.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import priv.simon.springboot.pojo.BackendUser;
import priv.simon.springboot.pojo.DevUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserInterceptor extends HandlerInterceptorAdapter {
    /**
     * This implementation always returns {@code true}.
     * @param request
     * @param response
     * @param handler
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        BackendUser userSession = (BackendUser) request.getSession().getAttribute("userSession");
        DevUser devUser=(DevUser) request.getSession().getAttribute("devUser");
        if(userSession==null&&devUser==null){
            response.sendRedirect("/error.html");
            return false;
        }else{
            return true;
        }
    }
}
