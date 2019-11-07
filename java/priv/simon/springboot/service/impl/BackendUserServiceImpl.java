package priv.simon.springboot.service.impl;

import org.springframework.stereotype.Service;
import priv.simon.springboot.mapper.BackendUserMapper;
import priv.simon.springboot.pojo.BackendUser;
import priv.simon.springboot.service.BackendUserService;

import javax.annotation.Resource;

@Service
public class BackendUserServiceImpl implements BackendUserService {
    @Resource
    private BackendUserMapper backendUserMapper;
    /**
     * 用户登录操作
     * @param user
     * @return
     */
    @Override
    public BackendUser findUser(BackendUser user) {
        return backendUserMapper.selUserLogin(user);
    }
}
