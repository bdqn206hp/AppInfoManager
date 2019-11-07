package priv.simon.springboot.service;

import org.springframework.stereotype.Service;
import priv.simon.springboot.pojo.AppVersion;

import java.util.List;

@Service
public interface AppVersionService {
    /**
     * 查询版本信息
     * @param appId
     * @return
     */
    List<AppVersion> findVersion(String appId);

    /**
     * 删除app版本信息
     * @param appId
     * @return
     */
    int rmvVersion(String appId);

    /**
     * 查询出最新版本信息
     * @param id
     * @return
     */
    AppVersion findVersionNew(String id);

    /**
     * 修改最新版本信息
     * @param appVersion
     * @return
     */
    int modAppVersion(AppVersion appVersion);

    /**
     * 添加最新版本
     * @param appVersion
     * @return
     */
    int addVersion(AppVersion appVersion);

    /**
     * 查询apk数量
     * @param apkName
     * @return
     */
    int findApkCount(String apkName);
}
