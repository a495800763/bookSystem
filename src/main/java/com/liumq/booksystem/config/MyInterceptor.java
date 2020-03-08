package com.liumq.booksystem.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class MyInterceptor  implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //System.out.println("==================开始拦截====================");
        return HandlerInterceptor.super.preHandle(request, response, handler);
//        System.out.println("==========登录状态拦截");
//        HttpSession session = request.getSession();
//        System.out.println("==========sessionId 为:" +session.getId());
//
//        Object userInfo = session.getAttribute("userInfo");
//        if(userInfo==null)
//        {
//            System.out.println("没有登录！！");
//            response.getWriter().write("Please Login In");
//            return  true;
//        }
//        else {
//            System.out.println("==========已经登录过了 用户信息是:" +userInfo);
//        }
//        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
