package cn.com.yky.ssm.utils;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class QiniuUploadUtils {

    //设置好账号的ACCESS_KEY和SECRET_KEY;这两个登录七牛账号里面可以找到 
    static String ACCESS_KEY = "Ad524p-5O0b3B_nUKaurUR1ETLOWkz2I4_HjuLNj";
    static String SECRET_KEY = "hVaQeIbydnDnrLK8s0seMKVqYRQ3k8CBQGdH7HON";
    //要上传的空间;对应到七牛上（自己建文件夹 注意设置公开）  //http://qgbu6ffn6.hn-bkt.clouddn.com
    static String bucketname = "k0502-yky";
    //上传到七牛后保存的文件名  
    static String key = UUID.randomUUID().toString().replace("-", "");
    //上传文件的路径 ;本地要上传文件路径  
   // String FilePath = "C:\\Users\\Administrator\\Pictures\\goodHeader\\g101301.jpg";
    //密钥配置  
    static Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    //创建上传对象,Zone.zone2()为华南地区的服务器
    static UploadManager uploadManager = new UploadManager(new Configuration(Zone.zone2()));
    //简单上传，使用默认策略，只需要设置上传的空间名就可以了  
    public static String getUpToken(){
        return auth.uploadToken(bucketname);  
    }
  
    //普通上传
    public static Map<String,Object> upload(MultipartFile myFile) throws IOException{
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            //调用put方法上传
            Response res = uploadManager.put(myFile.getBytes(), key+myFile.getOriginalFilename(), getUpToken());
            //打印返回的信息
            map.put("code",200);
            map.put("newFileName",res.jsonToMap().get("key"));
        } catch (QiniuException e) {  
            Response r = e.response;
            map.put("code",0);
        }
        return map;
    }  

}  
	

