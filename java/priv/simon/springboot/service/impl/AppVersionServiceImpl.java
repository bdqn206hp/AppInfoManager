package priv.simon.springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import priv.simon.springboot.mapper.AppInfoMapper;
import priv.simon.springboot.mapper.AppVersionMapper;
import priv.simon.springboot.pojo.AppVersion;
import priv.simon.springboot.service.AppVersionService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AppVersionServiceImpl implements AppVersionService {
    @Resource
    private AppVersionMapper appVersionMapper;
    @Autowired
    private AppInfoMapper appInfoMapper;

    /**
     * 查询版本信息
     *
     * @param appId
     * @return
     */
    @Override
    public List<AppVersion> findVersion(String appId) {
        return appVersionMapper.selVersion(appId);
    }

    /**
     * 删除app版本信息
     * @param appId
     * @return
     */
    @Override
    public int rmvVersion(String appId) {
        return appVersionMapper.delVersion(appId);
    }

    @Override
    public AppVersion findVersionNew(String id) {
        return appVersionMapper.selVersionNew(id);
    }

    @Override
    public int modAppVersion(AppVersion appVersion) {
        return appVersionMapper.updAppVersion(appVersion);
    }

    @Override
    public int addVersion(AppVersion appVersion) {
        return appVersionMapper.insVersion(appVersion);
    }

    @Override
    public int findApkCount(String apkName) {
        return appInfoMapper.selApkCount(apkName);
    }
}
