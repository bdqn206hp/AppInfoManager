package priv.simon.springboot.mapper;

import priv.simon.springboot.pojo.AppVersion;

import java.util.List;

public interface AppVersionMapper {
    /**
     * 查看版本信息
     * @param appId
     * @return
     */
    List<AppVersion> selVersion(String appId);

    /**
     * 删除app的版本信息
     * @param appId
     * @return
     */
    int delVersion(String appId);

    /**
     * 查询最新app版本信息
     * @param id
     * @return
     */
    AppVersion selVersionNew(String id);

    /**
     * 修改最新app版本信息
     * @param appVersion
     * @return
     */
    int updAppVersion(AppVersion appVersion);

    /**
     * 添加最新版本
     * @param appVersion
     * @return
     */
    int insVersion(AppVersion appVersion);
}
