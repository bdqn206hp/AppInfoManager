package priv.simon.springboot.service;

import priv.simon.springboot.pojo.AppCategory;
import priv.simon.springboot.pojo.DataDictionary;

import java.util.List;

public interface DataDictionaryService {
    /**
     * 查询app状态
     * @return
     */
    List<DataDictionary> findAppStatus();

    /**
     * 查询app所属平台
     * @return
     */
    List<DataDictionary> findflatform();

    /**
     * 查询一级分类
     * @return
     */
    List<AppCategory> findCate1Name();

    /**
     * 查询低级分类
     * @param cateId
     * @return
     */
    List<AppCategory> findCateName(String cateId);
}
