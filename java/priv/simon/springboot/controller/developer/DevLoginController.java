package priv.simon.springboot.controller.developer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import priv.simon.springboot.pojo.DevUser;
import priv.simon.springboot.service.DevUserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/dev")
@Controller
public class DevLoginController {
    @Resource
    private DevUserService devUserService;
    @RequestMapping("login")
    public String login(){
        return "home/devlogin";
    }

    @RequestMapping("toIndex")
    public String index(){
        return "index";
    }

    @RequestMapping("dologin")
    @ResponseBody
    public Map<String,Object> doLogin(DevUser devUser, HttpServletRequest req, Model model){
        Map<String,Object> map=new HashMap<>();
        devUser=devUserService.findDevUser(devUser);
        System.out.println(devUser);
        if(null!=devUser){
            req.getSession().setAttribute("devUser",devUser);
            System.out.println("设值成功");
            map.put("code",0);
            map.put("msg","trues");
        }else{
            map.put("code",1);
            map.put("msg","falses");
        }
        return map;
    }

    /**
     * 暂时不用
     * @param session
     * @return
     */
    @Deprecated
    @RequestMapping("main")
    public String main(HttpSession session){
        if(session.getAttribute("devUser")==null){
            return "redirect:/dev/login";
        }
        return "home/devlogin";
    }

    @RequestMapping("logout")
    public String loginOut(HttpSession session,Model model){
        session.removeAttribute("devUser");
        model.addAttribute("logout","yes");
        return "main";
    }
}
