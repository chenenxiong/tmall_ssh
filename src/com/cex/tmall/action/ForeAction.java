package com.cex.tmall.action;

import com.cex.tmall.comparator.*;
import com.cex.tmall.pojo.*;
import com.cex.tmall.service.ProductImageService;
import com.cex.tmall.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.web.util.HtmlUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ForeAction extends Action4Parameter{

    @Action("foreaddCart")
    public String addCart(){
        //pid=264&num=4
        User user_session= (User) ServletActionContext.getRequest().getSession().getAttribute("user");
        boolean found = false;
        int id = product.getId();
        product = (Product) productService.get(id);
        List<OrderItem> orderItemList = orderItemService.list("user",user_session,"order",null);
        for(OrderItem oi :orderItemList ){
            if(oi.getProduct().getId()==id){
                oi.setNumber(oi.getNumber()+num);
                orderItemService.update(oi);
                found=true;
                break;
            }
        }
        if(!found){
            OrderItem orderItem = new OrderItem();
            orderItem.setUser(user_session);
            orderItem.setNumber(num);
            orderItem.setProduct(product);
            orderItemService.save(orderItem);
        }
        return "success.jsp";
    }

    @Action("forebuy")
    public String buy(){
        List<OrderItem> orderItemList = new ArrayList<OrderItem>();
        for(int oiid : oiids ){
            OrderItem orderItem  = (OrderItem) orderItemService.get(oiid);
            total+=orderItem.getProduct().getPromotePrice()*orderItem.getNumber();
            orderItemList.add(orderItem);
        }
        ServletActionContext.getRequest().getSession().setAttribute("orderItems",orderItemList);
        return "buy.jsp";
    }

    @Action("forebuyone")
    public String buyone(){
        User user_session = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
        List<OrderItem> orderItemList = orderItemService.list("user",user_session,"order",null);
        boolean found = false;
        for(OrderItem oi : orderItemList){
            if(oi.getProduct().getId()==product.getId()){
                oi.setNumber(oi.getNumber()+num);
                orderItemService.update(oi);
                oiid = oi.getId();
                found=true;
            }
        }
        if(!found){
            OrderItem orderItem = new OrderItem();
            int id = product.getId();
            product= (Product) productService.get(id);

            orderItem.setUser(user_session);
            orderItem.setNumber(num);
            orderItem.setProduct(product);
            orderItemService.save(orderItem);
            oiid=orderItem.getId();
        }
        return "buyPage";
    }

    @Action("foresearch")
    public String search(){
        products = productService.search(keyword,0,20);
        productService.setSaleAndReviewNumber(products);
        for(Product p: products){
            productImageService.setFirstProductImage(p);
        }
        return "searchResult.jsp";
    }

    @Action("forecategory")
    public String category(){
        int id = category.getId();
        category = (Category) categoryService.get(id);
        productService.fill(category);
        productService.setSaleAndReviewNumber(category.getProducts());

        if(null!=sort){
            switch (sort.toString()){
                case "review":
                    Collections.sort(category.getProducts(),new ProductReviewCommparator());
                    break;
                case "date":
                    Collections.sort(category.getProducts(),new ProductDateComparator());
                    break;
                case "saleCount":
                    Collections.sort(category.getProducts(),new ProductSaleCountComparator());
                    break;
                case "price":
                    Collections.sort(category.getProducts(),new ProductPriceComparator());
                    break;
                case "all":
                    Collections.sort(category.getProducts(),new ProductAllComparator());
                    break;
            }
        }
        return "category.jsp";
    }

    @Action("foreloginAjax")
    public String loginAjax(){
        user.setName(HtmlUtils.htmlEscape(user.getName()));
        User user_session = userService.get(user.getName(),user.getPassword());
        if(null == user_session) return "fail.jsp";
        ServletActionContext.getRequest().getSession().setAttribute("user",user_session);
        return "success.jsp";
    }

    @Action("forecheckLogin")
    public String checkLogin(){
        User u = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
        if(null==u) return "fail.jsp";
        else return "success.jsp";
    }

    @Action("foreproduct")
    public String product(){
        int id = product.getId();
        product = (Product) productService.get(id);
        productSingleImages = productImageService.list("product",product,"type",ProductImageService.type_single);
        productDetailImages = productImageService.list("product",product,"type",ProductImageService.type_detail);
        product.setProductSingleImages(productSingleImages);
        product.setProductDetailImages(productDetailImages);
        productImageService.setFirstProductImage(product);
        propertyValues = (List<PropertyValue>) propertyValueService.listByParent(product);
        productService.setSaleAndReviewNumber(product);
        return "product.jsp";
    }
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
