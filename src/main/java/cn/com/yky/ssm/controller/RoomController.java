package cn.com.yky.ssm.controller;

import cn.com.yky.ssm.entity.Rooms;
import cn.com.yky.ssm.utils.QiniuUploadUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/rooms")
public class RoomController extends BaseController<Rooms> {
    //客房封面图上传
    @RequestMapping("/uploadRoomPic")
    public @ResponseBody Map<String,Object> uploadRoomPic(String path, MultipartFile myFile){
        try {
          return   QiniuUploadUtils.upload(myFile);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }


    }



}
