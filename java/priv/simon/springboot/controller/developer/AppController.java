package priv.simon.springboot.controller.developer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import priv.simon.springboot.pojo.*;
import priv.simon.springboot.redis.DataUtill;
import priv.simon.springboot.redis.RedisUtils;
import priv.simon.springboot.service.AppInfoService;
import priv.simon.springboot.service.AppVersionService;
import priv.simon.springboot.service.DataDictionaryService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/dev/app")
public class AppController {
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
     * 跳转到增加页面
     *
     * @param model
     * @return
     */
    @RequestMapping("tosave")
    public String toSave(Model model) {
        List<DataDictionary> appStatus = dataDictionaryServiceImpl.findAppStatus();
        List<AppCategory> cate1Name = dataDictionaryServiceImpl.findCate1Name();
        List<DataDictionary> findflatform = dataDictionaryServiceImpl.findflatform();
        model.addAttribute("status", appStatus);
        model.addAttribute("cate1Name", cate1Name);
        model.addAttribute("flatform", findflatform);
        return "home/developer/insapp";
    }

    /**
     * 增加app信息
     *
     * @param appInfo
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("save")
    public String saveApp(AppInfo appInfo, Model model, HttpSession session) throws IOException {
        appInfo.setStatus(1);
        DevUser devUser = (DevUser) session.getAttribute("devUser");
        appInfo.setCreatedBy(devUser.getId());
        appInfo.setCreationDate(new Date());
        appInfo.setDevId(devUser.getId());
        int i = appInfoServiceImpl.addApp(appInfo);
        if (i > 0) {
            dataUtill.addRedis();
            model.addAttribute("saveapp", "ok");
            return "forward:toList";
        } else {
            model.addAttribute("saveapp", "no");
            return "forward:tosave";
        }

    }

    /**
     * 跳转到列表页面
     *
     * @param model
     * @return
     */
    @RequestMapping("/toList")
    public String toAppList(Model model) {
        List<DataDictionary> appStatus = dataDictionaryServiceImpl.findAppStatus();
        List<AppCategory> cate1Name = dataDictionaryServiceImpl.findCate1Name();
        List<DataDictionary> findflatform = dataDictionaryServiceImpl.findflatform();
        model.addAttribute("status", appStatus);
        model.addAttribute("cate1Name", cate1Name);
        model.addAttribute("flatform", findflatform);
        return "home/developer/appinfolist";
    }


    public Boolean reg(AppInfo appInfo){
        if (appInfo.getSoftwareName().equals("") && appInfo.getStatus() == 0
                && appInfo.getFlatformId() == 0 && appInfo.getCategoryLevel1() == 0){
            return true;
        }
        return false;
    }

    /**
     * 查询分页
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
        if(reg(appInfo)&&pageNo==1&&pageSize==5){
            Map<String,Object> map = dataUtill.showListApp();
            if (map==null){
                map = dataUtill.addRedis();
            }
            return map;
        }
        System.out.println("appInfo:" + appInfo);
        Map<String, Object> result = new HashMap<>();
        result.put("code", 0);
        Page page = new Page();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        try {
            appInfoServiceImpl.showAppList(appInfo, page);
            System.out.println("LOL" + page.getPageCount());
            result.put("count", page.getPageCount());
            result.put("data", page.getList().toArray());
            System.out.println(page.getList().toArray() + "\t" + page.getList().size());
            //model.addAttribute("pages",page);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 异步保存图片
     *
     * @param file
     * @param req
     * @return
     * @throws IOException
     */
    @RequestMapping("saveImg")
    @ResponseBody
    public Map<String, Object> saveImg(MultipartFile file, HttpServletRequest req) throws IOException {
        System.out.println("进来啦~~~~");
        Map<String, Object> map = new HashMap<>();
        //新图片名称
        String fileName = System.currentTimeMillis() + Math.random() * 1000 + ".jpg";
        String path = req.getServletContext().getRealPath("style/img");
        String sufix = FilenameUtils.getExtension(file.getOriginalFilename());
        int size = 50 * 1024;
        if (file.getSize() > size) {
            map.put("code", -1);
            map.put("msg", "文件大小不能超过50KB");
        } else if (sufix.equalsIgnoreCase("jpg") || sufix.equalsIgnoreCase("png")) {
            File files = new File(path, fileName);
            if (!files.exists()) { //如果不存在 则创建
                files.mkdirs();
            }
            file.transferTo(files);
            System.out.println("上传成功!");
            map.put("code", 0);
            map.put("picpath", "/style/img/" + fileName);
            map.put("locpath", path + "/" + fileName);
            System.out.println("路径" + path + "/" + fileName);
        } else {
            map.put("code", 1);
            map.put("msg", "图片只能是jpg或者png格式");
        }
        return map;
    }

    /**
     * 删除图片
     *
     * @param path
     */
    @RequestMapping("delImg")
    public void delImg(String path) {
        File file = new File(path);
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("删除成功");
            }
        }
    }

    /**
     * 查询子集分类
     *
     * @param cateid
     * @return
     */
    @RequestMapping("cate")
    @ResponseBody
    public List<AppCategory> cateNames(String cateid) {
        List<AppCategory> cateName = dataDictionaryServiceImpl.findCateName(cateid);
        return cateName;
    }

    /**
     * 跳转到app修改页面
     *
     * @param model
     * @param apkName
     * @param id
     * @return
     */
    @RequestMapping("tomodapp")
    public String toModApp(Model model, String apkName, String id) {
        AppInfo appInfo = appInfoServiceImpl.showAppInfo(apkName, id);
        System.out.println("appInfo什么鬼数据:" + appInfo);
        List<AppCategory> cate1Name = dataDictionaryServiceImpl.findCate1Name();
        List<DataDictionary> findflatform = dataDictionaryServiceImpl.findflatform();
        //根据一级分类查询所有二级分类
        List<AppCategory> cate2Name = dataDictionaryServiceImpl.findCateName(appInfo.getCategoryLevel1() + "");
        //根据二级分类查询所有三级分类
        List<AppCategory> cate3Name = dataDictionaryServiceImpl.findCateName(appInfo.getCategoryLevel2() + "");
        model.addAttribute("flatform", findflatform);
        model.addAttribute("appInfo", appInfo);
        model.addAttribute("cate1Name", cate1Name);
        model.addAttribute("cate2Name", cate2Name);
        model.addAttribute("cate3Name", cate3Name);
        return "home/developer/modapp";
    }


    /**
     * 修改app信息
     *
     * @param appInfo
     * @param data
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("updapp")
    public String modapp(AppInfo appInfo, String data, HttpSession session, Model model) throws IOException {
        if (data != null && data.equals("ts")) {
            appInfo.setStatus(1);
        }
        appInfo.setUpdateDate(new Date());
        DevUser devUser = (DevUser) session.getAttribute("devUser");
        appInfo.setModifyBy(devUser.getId());
        appInfo.setModifyDate(new Date());
        int i = appInfoServiceImpl.modAppInfo(appInfo);
        if (i > 0) {
            dataUtill.addRedis();
            model.addAttribute("upd", "tru");
        } else {
            model.addAttribute("upd", "fs");
            return "forward:tomodapp?id=" + appInfo.getId();
        }
        return "forward:toList";
    }


    /**
     * 跳转到查看app信息详情页面
     * @param apkName
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("toappmsg")
    public String toAppMsg(String apkName, String id, Model model) {
        AppInfo appInfo = appInfoServiceImpl.showAppInfo(apkName, id);
        System.out.println(appInfo);
        model.addAttribute("appInfo", appInfo);
        return "home/developer/showappmsg";
    }

    /**
     * 查看版本信息
     * @param id
     * @return
     */
    @RequestMapping("showVersion")
    @ResponseBody
    public Map<String, Object> showVersion(String id) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        List<AppVersion> version = appVersionServiceImpl.findVersion(id);
        map.put("data", version);
        return map;
    }


    /**
     * 删除app信息
     * @param id
     * @return
     */
    @RequestMapping("delApp")
    @ResponseBody
    public Map<String, Object> delAppInfo(String id) throws IOException {
        //删除版本信息
        appVersionServiceImpl.rmvVersion(id);
        //删除app信息
        int info = appInfoServiceImpl.rmvAppInfo(id);
        Map<String, Object> map = new HashMap<>();
        if (info > 0) {
            dataUtill.addRedis();
            map.put("msg", "trus");
        } else {
            map.put("msg", "fls");
        }
        return map;
    }

    /**
     * 修改状态信息
     * @param id
     * @param status
     * @param model
     * @return
     */
    @RequestMapping("modStatus")
    public String modStatus(String id, String status, Model model) throws IOException {
        int infoStatus = appInfoServiceImpl.modAppInfoStatus(id, status);
        if (infoStatus > 0) {
            dataUtill.addRedis();
            model.addAttribute("modStatus", "yes");
        } else {
            model.addAttribute("modStatus", "no");
        }
        return "forward:toList";
    }

    /**
     * 跳到修改版本页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("modAppVersion")
    public String modAppVersion(String id, Model model) {
        AppInfo appInfo = appInfoServiceImpl.showAppInfo(null, id);
        System.out.println(appInfo);
        AppVersion version = appVersionServiceImpl.findVersionNew(appInfo.getVersionId() + "");
        System.out.println("version:" + version);
        model.addAttribute("version", version);
        return "home/developer/modversion";
    }

    /**
     * 修改版本信息
     * @param model
     * @param appVersion
     * @param session
     * @return
     */
    @RequestMapping("modVerssion")
    public String modVersion(Model model, AppVersion appVersion, HttpSession session) {
        appVersion.setModifyDate(new Date());
        DevUser devUser = (DevUser) session.getAttribute("devUser");
        appVersion.setModifyBy(devUser.getId());
        int version = appVersionServiceImpl.modAppVersion(appVersion);
        if (version > 0) {
            model.addAttribute("modversion", "yes");
        } else {
            model.addAttribute("modversion", "no");
        }
        return "forward:toList";
    }

    /**
     * 跳转到增加版本页面
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("toInsVersion")
    public String toInsVersion(Model model, String id) {
        AppInfo appInfo = appInfoServiceImpl.showAppInfo(null, id);
        AppVersion version = new AppVersion();
        version.setAppId(appInfo.getId());
        model.addAttribute("version", version);
        return "home/developer/insversion";
    }

    /**
     * 增加版本信息
     * @param session
     * @param model
     * @param appVersion
     * @return
     */
    @RequestMapping("insVersion")
    public String insVersion(HttpSession session, Model model, AppVersion appVersion) throws IOException {
        DevUser devUser = (DevUser) session.getAttribute("devUser");
        appVersion.setCreatedBy(devUser.getId());
        appVersion.setCreationDate(new Date());
        appVersion.setModifyDate(new Date());
        appVersionServiceImpl.addVersion(appVersion);
        System.out.println("增加后的版本id:" + appVersion.getId());
        int i = appInfoServiceImpl.mpdAppVersionId(appVersion.getAppId(), appVersion.getId());
        if (i > 0) {
            dataUtill.addRedis();
            model.addAttribute("addVersion", "yes");
        } else {
            model.addAttribute("addVersion", "no");
        }
        return "forward:toList";
    }


    /**
     * 上传apk文件
     *
     * @param file
     * @param req
     * @return
     * @throws IOException
     */
    @RequestMapping("upApk")
    @ResponseBody
    public Map<String, Object> uploadApk(MultipartFile file, HttpServletRequest req) throws IOException {
        Map<String, Object> map = new HashMap<>();
        //新apk名称
        String fileName = System.currentTimeMillis() + Math.random() * 1000 + ".apk";
        String path = req.getServletContext().getRealPath("AppInfoSystem/statics/uploadfiles");
        String sufix = FilenameUtils.getExtension(file.getOriginalFilename());
        int size = 500 * 1024;
        if (file.getSize() > size) {
            map.put("code", -1);
            map.put("msg", "文件大小不能超过5M");
        } else if (sufix.equalsIgnoreCase("apk")) {
            File apkFiles = new File(path, fileName);
            if (!apkFiles.exists()) { //如果不存在 则创建
                apkFiles.mkdirs();
            }
            file.transferTo(apkFiles);
            map.put("code", 0);
            map.put("load", "/AppInfoSystem/statics/uploadfiles/" + fileName);
            map.put("loc", path + "/" + fileName);
            map.put("file", fileName);
        } else {
            map.put("code", 1);
            map.put("msg", "上传文件只能是apk文件");
        }
        return map;
    }

    @RequestMapping("apkCount")
    @ResponseBody
    public Map<String,Object> showApkCount(String apkName){
        Map<String,Object> map=new HashMap<>();
        int count = appVersionServiceImpl.findApkCount(apkName);
        if(count>0){
            map.put("code",-1);
        }else{
            map.put("code",0);
        }
        return map;
    }


}