package priv.simon.springboot.service.impl;

import org.springframework.stereotype.Service;
import priv.simon.springboot.mapper.DevUserMapper;
import priv.simon.springboot.pojo.DevUser;
import priv.simon.springboot.service.DevUserService;

import javax.annotation.Resource;

@Service
public class DevUserServiceImpl implements DevUserService {
    @Resource
    private DevUserMapper devUserMapper;
    /**
     * 开发人员登录
     * @param devUser
     * @return
     */
    @Override
    public DevUser findDevUser(DevUser devUser) {
        return devUserMapper.selDevUser(devUser);
    }
}
