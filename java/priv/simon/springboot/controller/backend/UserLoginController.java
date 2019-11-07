package priv.simon.springboot.controller.backend;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import priv.simon.springboot.pojo.BackendUser;
import priv.simon.springboot.service.BackendUserService;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/back/user")
public class UserLoginController {
    private Logger logger=Logger.getLogger(UserLoginController.class);

    @Resource
    private BackendUserService backendUserService;
    @RequestMapping("login")
    public String login(){
        logger.debug("LoginController welcome AppInfoSystem backend==================");
        return "home/backlogin";
    }

    @RequestMapping(value="dologin")
    @ResponseBody
    public Map<String,Object> doLogin(BackendUser user, HttpServletRequest request, HttpSession session){
        logger.debug("doLogin====================================");
        Map<String,Object> map=new HashMap<>();
        //调用service方法，进行用户匹配
        BackendUser backendUser = null;
        try {
            backendUser = backendUserService.findUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(null != backendUser){//登录成功
            //放入session
            session.setAttribute("userSession", backendUser);
            map.put("code",0);
            map.put("msg","trs");
        }else{
            map.put("code",1);
            map.put("msg","falses");
        }
        return map;
    }

    @RequestMapping("toIndex")
    public String toIndex(){
        return "home/backend/index";
    }

    @RequestMapping("console")
    public String console(){
        return "home/backend/console";
    }


    @RequestMapping("logout")
    public String logOut(HttpSession session){
        session.removeAttribute("userSession");
        return "main";
    }
}