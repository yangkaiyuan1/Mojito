package cn.com.yky.ssm.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *   基础Mapper接口
 * @param <T> 实体封装类的泛型
 */
public interface BaseMapper<T> {


    //根据主键id删除单个数据
    int deleteByPrimaryKey(Integer id) throws Exception;

    //添加（添加所有字段数据，用的多）
    int insert(T t) throws Exception;

    //动态添加（根据具体的字段有值才添加，无值则为null）
    int insertSelective(T t) throws Exception;

    //根据主键id查询单个数据
    T selectByPrimaryKey(Integer id) throws Exception;

    //查询所有数据
    List<T> selectAll() throws Exception;

    //根据条件查询单个数据
    T selOneByPramas( T t)throws Exception;

    //根据条件查询多条数据
    List<T> selManyByPramas(T t)throws Exception;


    //动态修改（根据具体的字段有值才修改，无值则不修改，用的多）
    int updateByPrimaryKeySelective(T t) throws Exception;

    //修改（修改所有字段数据）
    int updateByPrimaryKey(T t) throws Exception;

    /**
     *   根据条件分页查询数据
     * @param t  查询的条件
     * @return  员工部门的分页数据
     * @throws Exception
     */
    List<T> selectPageByPramas(T t) throws Exception;

    /**
     *   根据多个主键id批量删除数据
     * @param ids  多个主键id的数组
     * @return  操作的数据条数
     * @throws Exception
     */
    Integer deleteBatchByIds(@Param("ids") Integer[] ids) throws Exception;

    /**
     *   根据多个主键动态批量修改字段数据
     * @param ids 多个主键数组
     * @param t  修改的对象数据
     * @return  修改的结果
     */
    Integer updBatchSelective(@Param("ids") Integer[] ids,@Param("t") T t) throws Exception;

    /**
     *   根据条件获取数据条数
     * @param t  查询的条件
     * @return  数据条数
     */
    Integer selCountByPramas(T t) throws Exception;


}
