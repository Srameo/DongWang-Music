package com.music.controller;

import com.music.domain.UserInfo;
import com.music.domain.UserStyleInfo;
import com.music.redis.RedisClient;
import com.music.service.UserService;
import com.music.thrift.ServiceProvider;
import com.music.thrift.message.MessageService;
import com.music.util.messagedigest.MD;
import com.music.util.randoncode.RC;
import com.music.util.response.LoginResponse;
import com.music.util.response.Response;
import com.music.util.token.Token;
import org.apache.thrift.TException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Resource
    private ServiceProvider serviceProvider;

    @Resource
    private RedisClient redisClient;

    @Resource
    private UserService userService;

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    public Response getUserNameById(int id) {
        return new LoginResponse(userService.getUserNameById(id), "0");
    }

    /**
     * 登录功能
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Response login(@RequestParam("username") String username, @RequestParam("password") String password){
        System.out.println("开始登陆。。。");
        UserInfo userInfo = userService.getUserByName(username);
        if (userInfo == null){
            return Response.USERNAME_PASSWORD_INVALID;
        }
        if (!userInfo.getPassword().equalsIgnoreCase(MD.md5(password))){
            return Response.USERNAME_PASSWORD_INVALID;
        }

        String token = Token.genToken();
        System.out.println(token);
        redisClient.set(token, userInfo, 3000);

        return new LoginResponse(token, "0");
    }

    /**
     * 发送验证码
     * @param email
     * @return
     */
    @RequestMapping(value = "/sendVerifyCode", method = RequestMethod.POST)
    @ResponseBody
    public Response sendVerifyCode(@RequestParam("email") String email){
        System.out.println("开始发送验证码。。。");
        String message = "VerifyCode is : ";
        String code = RC.randomCode("0123456789", 6);
        MessageService.Iface messageService = serviceProvider.getMessageService();
        boolean result = false;
        try {

            result = messageService.sendEmailMessage(email, message + code);
            redisClient.set(email, code);

            if (!result){
                return Response.SEND_VERIFYCODE_FAILED;
            }
        }catch (TException e){
            e.printStackTrace();
            return Response.exception(e);
        }

        return Response.SUCCESS;

    }

    /**
     * 注册功能
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Response register(@RequestParam("username") String username,
                             @RequestParam("password") String password,
                             @RequestParam("email") String email,
                             @RequestParam("age") int age,
                             @RequestParam("gender") int gender,
                             @RequestParam("style") List<Integer> style,
                             @RequestParam("verifyCode") String verifyCode){

        System.out.println("开始注册。。。");
        UserInfo userInfoGot = userService.getUserByName(username);
        if (userInfoGot != null){
            return Response.USERNAME_EXIST;
        }

        String redisCode = redisClient.get(email);
        if (!verifyCode.equals(redisCode)){
            return Response.VERIFY_CODE_INVALID;
        }

        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(username);
        userInfo.setPassword(MD.md5(password));
        userInfo.setEmail(email);
        userInfo.setAge(age);
        userInfo.setGender(gender);

        userService.registerUser(userInfo);

        userInfoGot = userService.getUserByName(username);
        UserStyleInfo userStyleInfo = new UserStyleInfo();
        userStyleInfo.setUid(userInfoGot.getId());
        for (Integer i : style){
            userStyleInfo.setSid(i);
            userService.setUserStyle(userStyleInfo);
        }

        return Response.SUCCESS;

    }

    /**
     * 验证是否已经登录
     */
    @RequestMapping(value = "/authentication", method = RequestMethod.POST)
    @ResponseBody
    public UserInfo authentication(@RequestParam("token") String token){
        return redisClient.get(token);
    }

}
