package cn.com.yky.ssm.controller;

import cn.com.yky.ssm.entity.User;
import cn.com.yky.ssm.utils.MD5;
import cn.com.yky.ssm.utils.VerifyCodeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/user")
public class userController extends BaseController<User> {

    //获取随机验证码 并响应到页面
    @RequestMapping("/getVerifyCode")
    public void getVerifyCode(HttpServletResponse response, HttpSession session){
        try {
            //1产生随机验证码
            String verifyCode = VerifyCodeUtils.generateVerifyCode(5);
            //2.将生成的随机验证码（转为小写）放在session容器中保存起来
            session.setAttribute("verifyCode",verifyCode.toLowerCase());
            //3将随机验证码输出到页面中
            VerifyCodeUtils.outputImage(180,30,response.getOutputStream(),verifyCode);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @RequestMapping("/verifyCheck")
    public @ResponseBody String verifyCheck(String userVerifyCode,HttpSession session ){
        //1.取出之前的存入到session中的验证码（已经转为小写）
        String verifyCode = (String) session.getAttribute("verifyCode");
        //2.将用户输入的验证码与生成的进行比较
        if(userVerifyCode.toLowerCase().equals(verifyCode)){  //将用户输入的转为小写与之前生成的进行比较
            return "success";  //验证码验证成功
        }else {
            return "fail";
        }

    }


    @RequestMapping("/login")
    public @ResponseBody String login(User user, HttpSession session){
        //将页面传送来的登录密码进行MD5加密，防止用户密码被泄漏
        user.setPwd(MD5.md5crypt(user.getPwd()));  //123456---->e10adc3949ba59abbe56e057f20f883e
        try {
            User loginUser = baseService.findOneByPramas(user);
            if (loginUser!=null){

                //将登录查询出的用户数据放入到session容器中
                session.setAttribute("loginUser",loginUser);
                return "success";
            }else {
                return "fail";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }

    }
    //用户退出
@RequestMapping("/exitUser")
    public @ResponseBody String exitUser(HttpSession session){
    try {
        //删除用户数据
        session.removeAttribute("loginUser");
        return "exitSuccess";
    } catch (Exception e) {
        e.printStackTrace();
        return "exitFail";
    }

}

}
