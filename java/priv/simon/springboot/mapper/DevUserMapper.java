package priv.simon.springboot.mapper;

import priv.simon.springboot.pojo.DevUser;

public interface DevUserMapper {
    /**
     * 查询测试用户
     * @param devUser
     * @return
     */
    DevUser selDevUser(DevUser devUser);
}
