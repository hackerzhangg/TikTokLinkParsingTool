package com.tiktoklinkparsingtool.controller;

import com.tiktoklinkparsingtool.model.Users;
import com.tiktoklinkparsingtool.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Author zgp
 * @Since 2021 -09 -11 01 :23
 * @Description 用户控制器
 */
@Controller
@RequestMapping("interface/index")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 到登录页面
     * @return
     */
    @RequestMapping("/toLoginPage")
    @ResponseBody
    public ModelAndView toLoginPage(){
        ModelAndView modelAndView=new ModelAndView("login");
        return modelAndView;
    }

    /**
     * 用户登录-到系统首页
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/userLogin")
    @ResponseBody
    public ModelAndView userLogin(String username, String password){
        ModelAndView modelAndView =new ModelAndView("login");
        //判断用户密码和用户名是否为空
        if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
            modelAndView.addObject("status","-9999");
            modelAndView.addObject("msg","亲 用户名或者密码不能为空！");
            return modelAndView;
        }
        List<Users> usersList=userService.userLogin(username,password);
        //判断是否存在该用户,如果不存在，继续回到登录页面
        if (usersList.size() == 0 || usersList == null){
            modelAndView.addObject("status","-9999");
            modelAndView.addObject("msg","亲 该用户不存在!");
            return modelAndView;
        }
        //用户存在，到后台首页系统
        return new ModelAndView("system/index");
    }

}
