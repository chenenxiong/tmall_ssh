package com.cex.tmall.action;

import com.cex.tmall.util.Page;
import org.apache.struts2.convention.annotation.Action;

public class UserAction extends Action4Result{
    @Action("admin_user_list")
    public String list(){
        if(page==null) page=new Page();
        users = userService.listByPage(page);
        return "listUser";
    }
}
