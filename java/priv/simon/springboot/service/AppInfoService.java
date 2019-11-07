package priv.simon.springboot.service;

import priv.simon.springboot.pojo.AppInfo;
import priv.simon.springboot.pojo.Page;

import java.io.IOException;
import java.util.List;

public interface AppInfoService {
    /**
     * 添加App信息
     * @param appInfo
     * @return
     */
    int addApp(AppInfo appInfo);

    /**
     * 分页按条件查询app信息
     * @param appInfo
     * @param page
     * @return
     */
    Page showAppList(AppInfo appInfo, Page page) throws IOException;

    /**
     * 修改app信息
     * @param appInfo
     * @return
     */
    int modAppInfo(AppInfo appInfo);

    /**
     * 删除app信息
     * @param id
     * @return
     */
    int rmvAppInfoById(String id);

    /**
     * 查看app详情
     * @param apkName
     * @param id
     * @return
     */
    AppInfo showAppInfo(String apkName, String id);

    /**
     * 删除app信息
     * @param id
     * @return
     */
    int rmvAppInfo(String id);

    /**
     * 修改app状态
     * @param id
     * @param status
     * @return
     */
    int modAppInfoStatus(String id, String status);

    /**
     * 修改app最新版本id
     * @param appId
     * @param id
     * @return
     */
    int mpdAppVersionId(long appId, long id);

    /**
     * 查询所有待审核的app
     * @param status
     * @return
     */
    List<AppInfo> showAppListByStatus(String status);

    /**
     * 查询app数量
     * @param appInfo
     * @return
     */
    int showAppinfoCount(AppInfo appInfo);
}
