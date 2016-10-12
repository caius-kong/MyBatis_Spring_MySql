package com.kyh.msm.interceptor;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableList;
import com.kyh.msm.entity.User;
import com.kyh.msm.vo.Json;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by kongyunhui on 16/10/8.
 */
public class SecurityIntercept implements HandlerInterceptor {
    private static final ImmutableList<String> excludeUrls = ImmutableList.of(
            "/user/login",
            "/user/logout");

    // 执行handler前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        // 获取url
        String requestURI = request.getRequestURI();
        String path = request.getContextPath();
        String url = requestURI.substring(path.length());
        System.out.println("-url-->" + url);
        // 放行公开方法
        if(excludeUrls.contains(url)) return true;
        // 登录或登录超时检查
        User user = (User) request.getSession().getAttribute("user");
        if(user == null) {
            // 判断请求类型: ajax请求、普通请求
            // 如果是ajax请求响应头会有，x-requested-with
            if("XMLHttpRequest".equalsIgnoreCase(request.getHeader("x-requested-with"))){
                response.setHeader("sessionstatus", "timeout");// 在响应头设置session状态
            } else {
                request.setAttribute("msg", "您还没有登录或登录已超时，请重新登录!");
                request.getRequestDispatcher("/WEB-INF/error/noSession.jsp").forward(request, response);
            }
            return false;
        }
        // 权限检查
        // user.getResourceList().contains(url)
        return false;
    }

    // 执行handler,返回ModelAndView前
    // 应用场景从modelAndView出发：将公用的模型数据(比如菜单导航)在这里传到视图，也可以在这里统一指定视图
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    // 执行handler之后
    // 应用场景：统一异常处理，统一日志处理
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    private void writeJson(HttpServletResponse response, Object object) throws Exception{

        String jsonString = JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd HH:mm:ss");

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(jsonString);
        out.flush();
        out.close();
    }
}
