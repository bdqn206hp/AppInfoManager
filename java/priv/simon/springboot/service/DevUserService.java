package priv.simon.springboot.service;

import priv.simon.springboot.pojo.DevUser;

public interface DevUserService {
    /**
     * 开发人员登录
     * @param devUser
     * @return
     */
    DevUser findDevUser(DevUser devUser);
}
