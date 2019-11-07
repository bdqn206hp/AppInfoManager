package priv.simon.springboot.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import priv.simon.springboot.pojo.AppInfo;
import priv.simon.springboot.pojo.Page;
import priv.simon.springboot.service.AppInfoService;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class DataUtill {
    @Resource
    private AppInfoService appInfoServiceImpl;
    @Resource
    private RedisUtils redisUtils;

    public Map<String,Object> addRedis() throws IOException {
        Map<String,Object> map=new HashMap<>();
        AppInfo appInfo=new AppInfo();
        int count = appInfoServiceImpl.showAppinfoCount(appInfo);
        System.out.println("count"+count);
        Page page=new Page();
        page.setPageNo(1);
        page.setPageSize(5);
        appInfoServiceImpl.showAppList(appInfo,page);
        map.put("code",0);
        map.put("count",count);
        map.put("data",page.getList());
        ObjectMapper objectMapper=new ObjectMapper();
        String value = objectMapper.writeValueAsString(map);
        redisUtils.hset("ht","app",value);
        return map;
    }

    public Map<String,Object> showListApp() throws IOException {
        Object app = redisUtils.hget("ht", "app");
        ObjectMapper objectMapper =new ObjectMapper();
        Map<String,Object> map = objectMapper.readValue(app + "", HashMap.class);
        System.out.println(map.values());
        return map;
    }
}
