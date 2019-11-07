package priv.simon.springboot.mapper;

import priv.simon.springboot.pojo.BackendUser;

public interface BackendUserMapper {
    /**
     * 用户登录
     * @return
     */
    BackendUser selUserLogin(BackendUser user);
}
