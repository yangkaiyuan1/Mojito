package cn.com.yky.ssm.controller;

import cn.com.yky.ssm.service.BaseService;
import cn.com.yky.ssm.service.OrdersService;
import cn.com.yky.ssm.utils.QiniuUploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  基础控制器层
 * @param <T>
 *  由于此时的T泛型还未明确，所以此时的类无法实例化，则不需要加上实例化注解
 *  也不要加访问路径的注解
 */
public class BaseController<T> {

    //依赖注入基础公共业务层对象  T为User  此时的baseService对象为UserService对象
    @Autowired
    protected BaseService<T> baseService;

    //依赖注入订单业务层对象
    @Autowired
    protected OrdersService orderService;


    /**
     *   根据条件分页查询数据
     * @param page  当前页,此参数名字只能是page
     * @param limit  每一页显示的数据条数,此参数名字只能是limit
     * @param  t 查询的条件
     * @return  分页查询的页面路径
     */
    @RequestMapping("/loadPageByPramas")
    public @ResponseBody Map<String,Object> loadPageByPramas(Integer page, Integer limit, T t){
        //新建返回的数据的map集合
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            //执行业务层条件分页查询
            map =  baseService.findPageByPramas(page,limit,t);
            map.put("code",0);  //加载成功
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code",200);   //加载失败
            map.put("msg","数据加载异常");  //异常页面提示
        }
        return map;
    }
    /**
     *   比较传统的分页加载
     * @param page  当前页
     * @param limit  每一页数据条数
     * @param t 查询条件参数对象
     * @return  分页数据
     */
    @RequestMapping("/loadPageTByPramas")
    public @ResponseBody Map<String,Object> loadPageTByPramas(Integer page, Integer limit, T t){
        System.out.println("page="+page+"\t"+"limit="+limit);
        System.out.println(t);
        Map<String, Object> map = null;
        try {
            map = baseService.findPageByPramas(page,limit,t);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }



    /**
     *   加载所有数据
     * @return  所有数据集合
     */
    @RequestMapping("/loadAll")
    public @ResponseBody List<T> loadAll(){
        try {
            return baseService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     *
     * @param t 查询条件
     * @return 多条数据
     * @throws Exception
     */@RequestMapping("/loadManyByPramas")
    public @ResponseBody List<T> loadManyByPramas(T t){
        try {
            return baseService.findManyByPramas(t);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    /**
     *   根据条件查询数据条数
     * @param t 条件参数对象
     * @return 数据条数
     */
    @RequestMapping("/loadCountByPramas")
    public @ResponseBody Integer loadCountByPramas(T t){
        try {
            return baseService.findCountByPramas(t);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     *  根据主键id删除单个数据
     * @param id  主键id
     * @return  操作结果
     */
    @RequestMapping("/delByPrimaryKey")
    public @ResponseBody String delByPrimaryKey(Integer id){
        try {
            return baseService.removeByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    /**
     *   根据多个主键id批量删除数据
     * @param ids  主键id数组  ids=1001,1002,1003
     * @return  操作的结果
     */
    @RequestMapping("/delBatchByIds")
    public @ResponseBody String delBatchByIds(Integer[] ids){
        try {
            return baseService.removeBatchByIds(ids);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    /**
     *   添加数据
     * @param t  添加的数据对象
     * @return  添加结果
     */
    @RequestMapping("/saveT")
    public @ResponseBody String saveT(T t){
        try {
            return baseService.saveT(t);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    /**
     *   动态修改数据
     * @param t 要修改的的数据对象
     * @return  修改的操作结果
     */
    @RequestMapping("/updByPrimaryKeySelective")
    public @ResponseBody String updByPrimaryKeySelective(T t){
        try {
            return baseService.updByPrimaryKeySelective(t);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
@RequestMapping("/loadOneByPramas")
    public @ResponseBody T loadOneByPramas(T t){
    try {
        return baseService.findOneByPramas(t);
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}
@RequestMapping("/updBatchSelective")
public @ResponseBody String updBatchSelective(Integer [] ids,T t){

    try {
        return baseService.updBatchSelective(ids,t);
    } catch (Exception e) {
        e.printStackTrace();
        return "error";
    }
}

    //客房封面图上传
    @RequestMapping("/uploadRoomPic")
    public @ResponseBody Map<String,Object> uploadRoomPic(String path, MultipartFile myFile){
        try {
            return QiniuUploadUtils.upload(myFile);  //调用工具类执行上传
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }




}
