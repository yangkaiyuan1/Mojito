package cn.com.yky.ssm.service.impl;

import cn.com.yky.ssm.mapper.BaseMapper;
import cn.com.yky.ssm.mapper.InRoomInfoMapper;
import cn.com.yky.ssm.mapper.RoomSaleMapper;
import cn.com.yky.ssm.mapper.RoomsMapper;
import cn.com.yky.ssm.service.BaseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseServiceImpl<T> implements BaseService<T> {
    //注入基础的BaseMapper代理对象  T为User
    @Autowired
   protected BaseMapper<T> baseMapper;
    //注入入住信息的mapper代理对象
    @Autowired
    protected InRoomInfoMapper inRoomInfoMapper;
    //注入客房信息的mapper代理对象
    @Autowired
    protected RoomsMapper roomsMapper;

    //依赖注入消费记录mapper代理对象
    @Autowired
    protected RoomSaleMapper roomSaleMapper;

    /**
     *   根据条件分页查询数据
     * @param page  当前页
     * @param limit  每一页的数据条数
     * @param t  查询的条件
     * @return  分页插件的对象数据
     * @throws Exception
     */
    @Override
    public Map<String, Object> findPageByPramas(Integer page, Integer limit, T t) throws Exception {
        //新建分页的map集合对象
        Map<String,Object> map = new HashMap<String, Object>();
        //开启分页
        PageHelper.startPage(page,limit);
        //进行分页查询
        PageInfo<T> pageInfo = new PageInfo<T>(baseMapper.selectPageByPramas(t));  //跟泛型User，此时的baseMapper为UserMapper代理对象
        //往map集合中装入相关数据
        map.put("count",pageInfo.getTotal());   //装总的数据条数  key值为："count"  千万不要改
        map.put("data",pageInfo.getList());   //装分页的对象数据   key值为："data"  千万不要改
        return map;
    }


    /**
     *   根据条件查询数据条数
     * @param t 条件参数对象
     * @return 数据条数
     */
    @Override
    public Integer findCountByPramas(T t) throws Exception {
        return baseMapper.selCountByPramas(t);
    }

    @Override
    public T findOneByPramas(T t) throws Exception {
        return baseMapper.selOneByPramas(t);
    }
    /**
     *
     * @param t 查询条件
     * @return 多条数据
     * @throws Exception
     */
    @Override
    public List<T> findManyByPramas(T t) throws Exception {
        return baseMapper.selManyByPramas(t);
    }

    /**
     *   查询所有数据
     * @return  返回查询的数据集合
     * @throws Exception
     */
    @Override
    public List<T> findAll() throws Exception {
        return baseMapper.selectAll();
    }

    /**
     *   根据主键id删除单个数据
     * @param id  主键id
     * @return  删除操作结果
     * @throws Exception
     */
    @Override
    public String removeByPrimaryKey(Integer id) throws Exception {
        if(baseMapper.deleteByPrimaryKey(id)>0){
            return "success";
        }else {
            return "fail";
        }
    }

    /**
     *   根据多个主键id批量删除数据
     * @param ids  多个主键id的数组
     * @return  操作的数据条数
     * @throws Exception
     */
    @Override
    public String removeBatchByIds(Integer[] ids) throws Exception {
        if(baseMapper.deleteBatchByIds(ids)>0){
            return "success";
        }else {
            return "fail";
        }
    }

    /**
     *   添加数据
     * @param t  要添加的数据
     * @return  添加的操作结果
     * @throws Exception
     */
    @Override
    public String saveT(T t) throws Exception {
        if(baseMapper.insert(t)>0){
            return "saveSuccess";
        }else {
            return "saveFail";
        }
    }

    /**
     *   动态添加（根据具体的字段有值才添加，无值则为null）
     * @param t  要添加的数据
     * @return  添加的操作结果
     * @throws Exception  T为User
     */
    @Override
    public String saveSelective(T t) throws Exception {
        if(baseMapper.insertSelective(t)>0){
            return "success";
        }else {
            return "fail";
        }
    }

    /**
     *   动态修改数据
     * @param t 要修改的的数据对象
     * @return  修改的操作结果
     */
    @Override
    public String updByPrimaryKeySelective(T t) throws Exception {
        if(baseMapper.updateByPrimaryKeySelective(t)>0){
            return "updSuccess";
        }else {
            return "updFail";
        }
    }

    /**
     *   修改
     * @param t 要修改的的数据对象
     * @return  修改的操作结果
     */
    @Override
    public String updateByPrimaryKey(T t) throws Exception {
        if(baseMapper.updateByPrimaryKey(t)>0){
            return "success";
        }else {
            return "fail";
        }
    }
    /**
     * 动态批量修改多个主键id数组
     * @param ids 多个主键id数组
     * @return 修改的结果
     * @param t 修改的内容
     *
     */
    @Override
    public String updBatchSelective(Integer[] ids, T t) throws Exception {
        if(baseMapper.updBatchSelective(ids,t)>0){
            return "updSuccess";
        }else {
            return "updFail";
        }
    }
}
