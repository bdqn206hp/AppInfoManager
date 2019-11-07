package priv.simon.springboot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import priv.simon.springboot.pojo.AppInfo;
import priv.simon.springboot.pojo.DevUser;
import priv.simon.springboot.pojo.Page;
import priv.simon.springboot.redis.RedisUtils;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Logger;

@Controller
public class TestController {
    @Autowired
    private RedisUtils redisUtils;
    Logger logger=Logger.getLogger(TestController.class.getName());
    @RequestMapping("/t1")
    @ResponseBody
    public void test01() throws IOException {
        ObjectMapper mapper=new ObjectMapper();
        Page page=new Page();
        page.setPageNo(1);
        redisUtils.set("test",mapper.writeValueAsString(page));
        //redisUtils.set("nama","张三");
        System.out.println("输出"+redisUtils.get("test"));
        String str=""+redisUtils.get("test");
        Page page1 = mapper.readValue(str,Page.class);

        System.out.println("page1"+page1.getPageNo());
        logger.info(redisUtils.get("nama").toString());
    }

    @RequestMapping("/t2")
    public String test02(HttpSession session){
        DevUser devUser =new DevUser();
        devUser.setDevCode("zzzz");
        session.setAttribute("test",devUser);
        return "index";
    }


}