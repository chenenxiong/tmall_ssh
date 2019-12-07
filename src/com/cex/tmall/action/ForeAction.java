package com.cex.tmall.action;

import com.cex.tmall.pojo.User;
import com.cex.tmall.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.web.util.HtmlUtils;

public class ForeAction extends Action4Parameter{
    @Action("forelogout")
    public String logout(){
        ServletActionContext.getRequest().getSession().removeAttribute("user");
        return "homePage";
    }

    @Action("forelogin")
    public String login(){
        user.setName(HtmlUtils.htmlEscape(user.getName()));
        User user_session = userService.get(user.getName(),user.getPassword());
        if(null == user_session){
            msg="密码错误或账号不存在";
            return "login.jsp";
        }
        //ActionContext.getContext().getSession().put("user",user_session);
        ServletActionContext.getRequest().getSession().setAttribute("user",user_session);
        return "homePage";
    }


    @Action("foreregister")
    public String register(){
        user.setName(HtmlUtils.htmlEscape(user.getName()));
        boolean exist = userService.isExist(user.getName());
        if(exist){
            msg="用户已存在";
            return "register.jsp";
        }
        userService.save(user);
        return "registerSuccessPage";
    }

    @Action("forehome")
    public String home(){
        categorys = categoryService.list();
        productService.fill(categorys);
        productService.fillByRow(categorys);
        return "home.jsp";
    }

}
