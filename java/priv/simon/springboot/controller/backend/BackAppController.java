package priv.simon.springboot.controller.backend;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import priv.simon.springboot.pojo.*;
import priv.simon.springboot.redis.DataUtill;
import priv.simon.springboot.redis.RedisUtils;
import priv.simon.springboot.service.AppInfoService;
import priv.simon.springboot.service.AppVersionService;
import priv.simon.springboot.service.DataDictionaryService;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/back/app")
public class BackAppController {

    @Resource
    RedisUtils redisUtils;
    @Resource
    private AppInfoService appInfoServiceImpl;
    @Resource
    private DataDictionaryService dataDictionaryServiceImpl;
    @Resource
    private AppVersionService appVersionServiceImpl;
    @Resource
    private DataUtill dataUtill;

    /**
     * 跳转到列表页面
     *
     * @param model
     * @return
     */
    @RequestMapping("/toList")
    public String toAppList(Model model) {
        List<AppCategory> cate1Name = dataDictionaryServiceImpl.findCate1Name();
        List<DataDictionary> findflatform = dataDictionaryServiceImpl.findflatform();
        model.addAttribute("cate1Name", cate1Name);
        model.addAttribute("flatform", findflatform);
        return "home/backend/backlist";
    }

    public Boolean reg(AppInfo appInfo) {
        if (appInfo.getSoftwareName().equals("") && appInfo.getFlatformId() == 0
                && appInfo.getCategoryLevel1() == 0) {
            return true;
        }
        return false;
    }

    /**
     * 查询分页
     *
     * @param appInfo
     * @param pageNo
     * @param pageSize
     * @param model
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> findApp(AppInfo appInfo,
                                       @RequestParam(value = "page", defaultValue = "1") int pageNo,
                                       @RequestParam(value = "limit", defaultValue = "5") int pageSize,
                                       Model model) throws IOException {
        /*if (pageNo == 1 && reg(appInfo) && pageSize == 5) {
            Map<String, Object> map = showListApp();
            if (map == null) {
                map = addRedis();
            }
            return map;
        }*/

        Map<String, Object> result = new HashMap<>();
        result.put("code", 0);
        Page page = new Page();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        try {
            appInfoServiceImpl.showAppList(appInfo, page);
            result.put("count", page.getPageCount());
            result.put("data", page.getList().toArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("toAudit")
    public String toAudit(Model model, String apkName, String id) {
        AppInfo appInfo = appInfoServiceImpl.showAppInfo(apkName, id);
        AppVersion versionNew = appVersionServiceImpl.findVersionNew(appInfo.getVersionId() + "");
        model.addAttribute("appInfo", appInfo);
        model.addAttribute("version", versionNew);
        return "home/backend/showaudit";
    }

    /**
     * 提交审核
     * @param model
     * @param status
     * @param id
     * @return
     */
    @RequestMapping("upStatus")
    public String upStatus(Model model, String status, String id) throws IOException {
        System.out.println("status:" + status + "id:" + id);
        int i = appInfoServiceImpl.modAppInfoStatus(id, status);
        if (i > 0) {
            dataUtill.addRedis();
            model.addAttribute("upstatus", "yes");
        } else {
            model.addAttribute("upstatus", "no");
        }
        return "forward:toList";
    }

    public Map<String, Object> addRedis() throws JsonProcessingException {
        Map<String, Object> map = new HashMap<>();
        AppInfo appInfo = new AppInfo();
        appInfo.setStatus(1);
        int count = appInfoServiceImpl.showAppinfoCount(appInfo);
        List<AppInfo> list = appInfoServiceImpl.showAppListByStatus("1");
        map.put("code", 0);
        map.put("count", count);
        map.put("data", list);
        ObjectMapper objectMapper = new ObjectMapper();
        String value = objectMapper.writeValueAsString(map);
        redisUtils.hset("ht", "status1", value);
        return map;
    }

    public Map<String, Object> showListApp() throws IOException {
        Object status1 = redisUtils.hget("ht", "status1");
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = objectMapper.readValue(status1 + "", HashMap.class);
        return map;
    }

    /*@RequestMapping("apptest")
    @ResponseBody
    public Map<String,Object> test() throws Exception {
        addRedis();
        Map<String,Object> map = showListApp();
        System.out.println(map.values());
        return map;
    }*/
}
