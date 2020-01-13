package com.example.controller;

import com.example.common.GenericController;
import com.example.entity.User;
import com.example.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping(value="/user")
public class UserController extends GenericController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    //返回jsp视图展示
    @RequestMapping(value="/getUserModel",method= RequestMethod.GET)
    public ModelAndView getUsers(@RequestParam Integer userId){
        ModelAndView modelAndView = new ModelAndView();
        //调用service方法得到用户列表
        List<User> userList = userService.getUsers(userId);
        //将得到的用户列表内容添加到ModelAndView中
        modelAndView.addObject("users",userList);
        //设置相应的jsp视图
        modelAndView.setViewName("getUser");
        LOGGER.info("=============query successfully!");

        return modelAndView;
    }

    //返回json格式数据，形式1
    @RequestMapping(value="/getUserJson1",method=RequestMethod.GET)
    @ResponseBody
    public List getUserDataByJson(@RequestParam Integer userId, HttpServletRequest request,
                                  HttpServletResponse response){
        //调用service方法得到用户列表
        List<User> users = userService.getUsers(userId);
        LOGGER.info("===============getUserDataByJson query sucessfully!");
        return users;
    }

    //返回json格式数据，形式2（自定义了返回的格式）
    @RequestMapping(value="/getUserJson2",method=RequestMethod.GET)
    public void getUserDataByJson2(@RequestParam Integer userId,HttpServletRequest request,
                                   HttpServletResponse response){
        //调用service方法得到用户列表
        List<User> users = userService.getUsers(userId);
        LOGGER.info("================getUserDataByJson2 query successfully");
        renderSuccessString(response,users);
    }

}
