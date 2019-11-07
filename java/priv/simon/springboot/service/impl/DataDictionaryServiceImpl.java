package priv.simon.springboot.service.impl;

import org.springframework.stereotype.Service;
import priv.simon.springboot.mapper.DataDictionaryMapper;
import priv.simon.springboot.pojo.AppCategory;
import priv.simon.springboot.pojo.DataDictionary;
import priv.simon.springboot.service.DataDictionaryService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DataDictionaryServiceImpl implements DataDictionaryService {
    @Resource
    private DataDictionaryMapper dataDictionaryMapper;
    /**
     * 查询app状态
     *
     * @return
     */
    @Override
    public List<DataDictionary> findAppStatus() {
        return dataDictionaryMapper.selAppStatus();
    }

    /**
     * 查询app所属平台
     *
     * @return
     */
    @Override
    public List<DataDictionary> findflatform() {
        return dataDictionaryMapper.selflatform();
    }

    /**
     * 查询一级分类
     *
     * @return
     */
    @Override
    public List<AppCategory> findCate1Name() {
        return dataDictionaryMapper.selCate1Name();
    }

    /**
     * 查询低级分类
     * @param cateId
     * @return
     */
    @Override
    public List<AppCategory> findCateName(String cateId) {
        return dataDictionaryMapper.selCateName(cateId);
    }
}
