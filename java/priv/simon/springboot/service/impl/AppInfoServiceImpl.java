package priv.simon.springboot.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import priv.simon.springboot.mapper.AppInfoMapper;
import priv.simon.springboot.pojo.AppInfo;
import priv.simon.springboot.pojo.Page;
import priv.simon.springboot.redis.RedisUtils;
import priv.simon.springboot.service.AppInfoService;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;


@Service
public class AppInfoServiceImpl implements AppInfoService {
    @Resource
    private AppInfoMapper appInfoMapper;

    @Resource
    private RedisUtils redisUtils;
    /**
     * 添加App信息
     *
     * @param appInfo
     * @return
     */
    @Override
    public int addApp(AppInfo appInfo) {
        return appInfoMapper.insApp(appInfo);
    }

    /**
     * 分页按条件查询app信息
     * @param appInfo
     * @param page
     * @return
     */
    @Override
    public Page showAppList(AppInfo appInfo, Page page) throws IOException {
        //ObjectMapper mapper=new ObjectMapper();
        int appCount = appInfoMapper.selAppCount(appInfo);
        page.setPageCount(appCount);
        page.setPageIndex((page.getPageNo()-1)*page.getPageSize());
        int totle = appCount%page.getPageSize()==0?appCount/page.getPageSize():appCount/page.getPageSize()+1;
        page.setTotle(totle);
        System.out.println("page:"+page);
        //判断是否按条件查询 查询全部的话分页数据直接到redis缓存里面去查
        /*if (appInfo.getCategoryLevel1() == 0 && appInfo.getSoftwareName() == null && appInfo.getStatus() == 0 && appInfo.getFlatformId()==0){
            Object appList = redisUtils.get("appList" + page.getPageNo());
            //如果redis中有数据
            if(!"".equals(appList)&&appList!=null){
                //将数据反序列化 成List<AppInfo> 集合
                TypeReference<List<AppInfo>> typeReference = new TypeReference<List<AppInfo>>() {};
                List<AppInfo> list = mapper.readValue(appList.toString(), typeReference);
                page.setList(list);
                return page;
            }
        }*/
        //从数据库里查询
        List<AppInfo> appInfos = appInfoMapper.selAppInfoList(appInfo, page);
        for (AppInfo ai: appInfos) {
            String name=ai.getCate1Name();
            if(ai.getCate2Name()!=null&&ai.getCate2Name()!=""){
                name=name+" -> "+ai.getCate2Name();
            }
            if(ai.getCate3Name()!=null&&ai.getCate3Name()!=""){
                name=name+" -> "+ai.getCate3Name();
            }
            ai.setCate3Name(name);
        }
        //System.out.println(appInfos);
        //redisUtils.set("appList"+page.getPageNo(),mapper.writeValueAsString(appInfos));
        page.setList(appInfos);
        return page;
    }
    /**
     * 修改app信息
     * @param appInfo
     * @return
     */
    public int modAppInfo(AppInfo appInfo) {
        return appInfoMapper.updAppInfo(appInfo);
    }


    /**
     * 删除app信息
     * @param id
     * @return
     */
    public int rmvAppInfoById(String id) {
        return appInfoMapper.delAppInfoById(id);
    }

    /**
     * 查看app详情
     * @param apkName
     * @param id
     * @return
     */
    public AppInfo showAppInfo(String apkName, String id) {
        return appInfoMapper.selAppInfo(apkName, id);
    }

    /**
     * 删除app信息
     * @param id
     * @return
     */
    @Override
    public int rmvAppInfo(String id) {
        return appInfoMapper.delAppInfoById(id);
    }

    @Override
    public int modAppInfoStatus(String id, String status) {
        return appInfoMapper.updAppInfoStatus(id,status);
    }

    @Override
    public int mpdAppVersionId(long appId, long id) {
        return appInfoMapper.updAppVersionId(appId,id);
    }

    @Override
    public List<AppInfo> showAppListByStatus(String status) {
        return appInfoMapper.selAppListByStatus(status);
    }
    @Override
    public int showAppinfoCount(AppInfo appInfo){
        return appInfoMapper.selAppCount(appInfo);
    }
}
