package priv.simon.springboot.service;

import priv.simon.springboot.pojo.BackendUser;

public interface BackendUserService {
    /**
     * 用户登录操作
     * @param user
     * @return
     */
    BackendUser findUser(BackendUser user);
}
