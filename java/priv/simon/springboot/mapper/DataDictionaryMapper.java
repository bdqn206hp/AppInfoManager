package priv.simon.springboot.mapper;

import priv.simon.springboot.pojo.AppCategory;
import priv.simon.springboot.pojo.DataDictionary;

import java.util.List;

public interface DataDictionaryMapper {
    /**
     * 查询app状态
     * @return
     */
    List<DataDictionary> selAppStatus();

    /**
     * 查询app所属平台
     * @return
     */
    List<DataDictionary> selflatform();

    /**
     * 查询一级分类
     * @return
     */
    List<AppCategory> selCate1Name();

    /**
     * 查询低级分类
     * @param cateId
     * @return
     */
    List<AppCategory> selCateName(String cateId);
}
