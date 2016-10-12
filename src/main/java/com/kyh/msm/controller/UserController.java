package com.kyh.msm.controller;

import com.google.common.collect.Lists;
import com.kyh.msm.entity.Email;
import com.kyh.msm.entity.User;
import com.kyh.msm.service.UserService;
import com.kyh.msm.vo.Json;
import com.kyh.msm.vo.UserInfoCustom;
import com.kyh.msm.vo.UserQueryVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.UUID;

/**
 * Created by kongyunhui on 16/9/13.
 *
 * springMVC中，一般Controller、service、DAO层的scope均是singleton
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserService userService;

    // springMvc是单例开发,每一个handler都是单例,因此requestCount共享。
    // springMvc的每个请求都是单独的线程,因此多个请求同时访问一个Controller类中的同一个方法不会堵塞
    // 问题来了: 并发访问,共享资源窜数据!! 因此不建议使用实例变量。 (和Struts2的根本性区别,Struts2是类级别的,每次都会创建Handler,因此实例变量不是共享的)
    private int requestCount = 0;

    // ModelView返回
    @RequestMapping(value="/getUser1.do", method= RequestMethod.GET)
    public ModelAndView getUser1(int id){
        User user = userService.getUser(id);
        LOG.info("-user-->" + user.toString());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("userInfo");
        return modelAndView;
    }

    // Model返回
    @RequestMapping(value="/getUser2.do", method= RequestMethod.GET)
    public String getUser2(Model model, int id){
        User user = userService.getUser(id);
        LOG.info("-user-->" + user.toString());

        model.addAttribute("user", user);
        return "userInfo";
    }

    // HttpServletRequest返回
    @RequestMapping(value="/getUser3.do", method= RequestMethod.GET)
    public String getUser3(HttpServletRequest request, int id){
        User user = userService.getUser(id);
        LOG.info("-user-->" + user.toString());

        request.setAttribute("user", user);
        return "userInfo";
    }

    // object可以省略key, 但是list必须指定key!!! (建议统一指定key)
    @RequestMapping(value="/getUsers.do", method = RequestMethod.GET)
    public String getUsers(Model model){
        List<User> users = userService.getUserAll();
        LOG.info("-users-->" + users.toString());

        model.addAttribute("users", users);
        return "userInfo";
    }

    @RequestMapping("/getUserByName")
    public String getUserByName(Model model, String name){
        List<User> users = userService.getUserByName(name);
        LOG.info("-users-->" + users);

        model.addAttribute("users", users);
        return "userInfo";
    }

    @RequestMapping("/getUserInfo")
    public String getUserInfo(Model model , UserQueryVo userQueryVo){
        List<UserInfoCustom> users = userService.getUserInfo(userQueryVo);
        LOG.info("-userInfoCustomList-->" + users);

        model.addAttribute("users", users);
        return "userInfo";
    }

    @RequestMapping("/getUserInfo2")
    public String getUserInfo2(Model model){
        List<Email> emails = userService.getUserInfo2();
        LOG.info("-emails-->" + emails);

        model.addAttribute("emails", emails);
        return "userInfo";
    }

    @RequestMapping("/getUserInfo3")
    public String getUserInfo3(Model model){
        List<User> users = userService.getUserInfo3();
        LOG.info("-users-->" + users);

        model.addAttribute("users", users);
        return "userInfo";
    }

    @RequestMapping("/getEmailInfo")
    public String getEmailInfo(Model model){
        List<Email> emails = userService.getEmailInfo();
        LOG.info("-emails-->" + emails);

        model.addAttribute("emails", emails);
//        for(Email email : emails){
//            User user = email.getUser();
//            LOG.info("-user-->" + user);
//        }
        return "userInfo";
    }

    @RequestMapping("/getUsersByIds")
    public String getUsersByIds(Model model){
        UserQueryVo userQueryVo = new UserQueryVo();
        userQueryVo.setIds(Lists.newArrayList(2,3));

        List<User> users = userService.getUsersByIds(userQueryVo);
        LOG.info("-users-->" + users);

        model.addAttribute("users", users);
        return "userInfo";
    }

    // pojo绑定 + 文件上传
    @RequestMapping("/saveUser")
    public String saveUser(Model model, User user, MultipartFile user_pic) throws Exception{
        // 获得原始图片名称
        String originalFilename = user_pic.getOriginalFilename();
        // 上传图片
        if(user_pic!=null && StringUtils.isNotBlank(originalFilename)){
            // 存储图片的物理路径
            String pic_path = "/Users/kongyunhui/Pictures/";
            // 新的图片名称
            String newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
            // 新的图片
            File newFile = new File(pic_path + newFileName);
            // 将内存中的数据写入磁盘
            user_pic.transferTo(newFile);
            // 将图片名称写入数据库 (/pic是物理路径映射的虚拟路径)
            user.setPicUrl("/pic/" + newFileName);
        }
        System.out.println("-user-->" + user);
        int i = userService.saveUser(user);
        if(i>0)
            model.addAttribute("uploadSuccess", true);
        return "userInfo";
    }

    // 返回json数据
    @RequestMapping("/saveUser2")
    public @ResponseBody Json saveUser2(User user){
        Json json = new Json();
        try {
            int i = userService.saveUser(user);
            if(i>0){
                json.setSuccess(true);
                json.setMsg("保存成功!");
            }
        }catch(Exception e){
            e.printStackTrace();
            json.setMsg("保存失败!");
        }
        return json;
    }

    // 集合-数组绑定
    @RequestMapping("/deleteUsers")
    public String deleteUsers(Integer[] user_id){
        List<Integer> integers = Lists.newArrayList(user_id);
        System.out.println("-integers-->" + integers);
        return "userInfo";
    }

    // 集合-list绑定 (需要借助包装类型)
    @RequestMapping("/deleteUsers1")
    public String deleteUsers1(UserQueryVo userQueryVo){
        System.out.println("-ids-->" + userQueryVo.getIds());
        return "userInfo";
    }

    /**
     * RESTful api
     *
     * GET（SELECT）：从服务器取出资源（一项或多项）。
     * DELETE（DELETE）：从服务器删除资源。
     * POST（CREATE）：在服务器新建一个资源。
     * PUT（UPDATE）：在服务器更新资源（客户端提供改变后的完整资源）。
     * PATCH（UPDATE）：在服务器更新资源（客户端提供改变的属性）。
     *
     * \\d+: \ 转义符 加\d 是一个正字表达式，标识所有数字及0-9
     */
    @RequestMapping(value="/user/{id:\\d+}", method = RequestMethod.GET)
    public String getUser(@PathVariable int id){
        System.out.println("-getUser-->" + id);
        return "userInfo";
    }

    @RequestMapping(value="/user/{id:\\d+}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable int id){
        System.out.println("-deleteUser-->" + id);
        return "userInfo";
    }

    @RequestMapping(value="/user", method = RequestMethod.POST)
    public String insertUser(User user){
        System.out.println("-insertUser-->" + user);
        return "userInfo";
    }

    @RequestMapping(value="/user", method=RequestMethod.PUT)
    public String updateUser(User user){
        System.out.println("-updateUser-->" + user);
        return "userInfo";
    }

    @RequestMapping(value="/user", method = RequestMethod.PATCH)
    public String listUser(@RequestParam(value="name", required = false, defaultValue = "") String name){
        System.out.println("-查询人员name like-->" + name);
        return "userInfo";
    }

    // 登录页面
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
}
