package priv.simon.springboot.mapper;

import org.apache.ibatis.annotations.Param;
import priv.simon.springboot.pojo.AppInfo;
import priv.simon.springboot.pojo.Page;

import java.util.List;

public interface AppInfoMapper {
    /**
     * 添加App信息
     *
     * @param appInfo
     * @return
     */
    int insApp(AppInfo appInfo);

    /**
     * 查询App信息
     * @param appInfo
     * @param page
     * @return
     */
    List<AppInfo> selAppInfoList(@Param("appInfo") AppInfo appInfo, @Param("pager") Page page);

    int selAppCount(AppInfo appInfo);

    /**
     * 修改app信息
     * @param appInfo
     * @return
     */
    int updAppInfo(AppInfo appInfo);

    /**
     * 删除app信息
     * @param id
     * @return
     */
    int delAppInfoById(String id);

    /**
     * 查看app详情
     * @param apkName
     * @param id
     * @return
     */
    AppInfo selAppInfo(@Param("apkName") String apkName,@Param("id") String id);

    /**
     * 修改app信息
     * @param id
     * @param status
     * @return
     */
    int updAppInfoStatus(@Param("id") String id,@Param("status") String status);

    /**
     * 修改app版本号id
     * @param appId
     * @param id
     * @return
     */
    int updAppVersionId(@Param("appId") long appId,@Param("id") long id);

    /**
     * 查询apk名称是否重复
     * @param apkName
     * @return
     */
    int selApkCount(String apkName);

    /**
     * 查询所有待审核的数据
     * @param status
     * @return
     */
    List<AppInfo> selAppListByStatus(String status);
}
